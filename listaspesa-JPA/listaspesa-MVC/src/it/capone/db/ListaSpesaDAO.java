/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.capone.db;

import it.capone.bean.ListaSpesaBean;
import it.capone.bean.Voce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object per la gestione della lista della spesa
 * 
 * @author Fulvio
 */
public class ListaSpesaDAO {

    /**
     * Legge dal database l'intera lista della spesa (in ordine), e la 
     * memorizza nel parametro <code>spesa</code>
     * 
     * @param spesa un {@link ListaSpesaBean} che agisce come Transfer Object 
     * e viene riempito con il contenutod della lista dal database. Il contenuto
     * precedente della lista viene cancellato.
     */
    public void fillListaSpesa(ListaSpesaBean spesa) {
        Connection conn = DBConnect.getConnection();

        try {
            Statement st = conn.createStatement();

            String sql = "SELECT voce, data, ord FROM listaspesa.spesa ORDER BY ord";

            ResultSet res = st.executeQuery(sql);
            while (res.next()) {
            	//CAPONE
            	Voce voce = new Voce();
            	String prodotto = res.getString("voce");
            	Integer ord = res.getInt("ord");
            	
            	voce.setMsg(prodotto);
            	voce.setOrd(ord);
                spesa.aggiungiVoce(voce);
            	//spesa.aggiungi(res.getString("voce"));
            }

            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("database error in " + this.getClass().getSimpleName(), ex);
        }
    }

    /**
     * Aggiunge una nuova voce alla lista della spesa nel database (in fondo all'elenco)
     * 
     * @param voce la stringa da aggiungere
     */
    public void aggiungi(String voce) {

        int maxOrd = getMaxOrd();
        maxOrd++;

        Connection conn = DBConnect.getConnection();

        try {

            String sql = "INSERT INTO SPESA (voce, data, ord) "
                    + "VALUES ( ?, NOW(), ? )";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, voce);
            st.setInt(2, maxOrd);

            //String sql = "INSERT INTO SPESA (voce, data, ord) "
            //        + "VALUES ( '" + voce + "', NOW(), " + maxOrd + " )";

            /* nota: potresti anche fare:
             * insert into lista(voce, data, ord) select "pippo", now(), max(ord)+1 from spesa;
             * evitando una chiamata a getMaxOrd()
             */

            int res = st.executeUpdate();

            // TODO controllare che res==1

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("database error in " + this.getClass().getSimpleName(), ex);
        }
    }

    /**
     * Ritorna il massimo numero d'ordine presente nella lista della spesa
     * 
     * @return il valore massimo del numero d'ordine, se vi sono elementi memorizzati.
     * Se invece il database non contiene ancora elementi, ritorna <code>-1</code>
     */
    public int getMaxOrd() {
        Connection conn = DBConnect.getConnection();

        try {
            Statement st = conn.createStatement();

            String sql = "SELECT MAX(ord) FROM spesa";

            ResultSet res = st.executeQuery(sql);

            int ord;
            if (res.next()) {
                ord = res.getInt(1);
                if (res.wasNull()) {    // in case of empty table
                    ord = -1;
                    // no elements => return (-1), so that first element will be #0
                }
            } else {
                ord = -1;
            }

            st.close();
            conn.close();

            return ord;
        } catch (SQLException ex) {
            Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("database error in " + this.getClass().getSimpleName(), ex);
        }
    }

    /**
     * Cancella dall'elenco sul database la voce specificata.
     * 
     * @param posVoce il numero d'ordine (compreso tra 0 e getMaxOrd(), estremi compresi)
     * dell'elemento da cancellare
     * 
     * @return <code>true</code> se la cancellazione è avvenuta con successo,
     * <code>false</code> altrimenti
     */
    public boolean elimina(int posVoce) {

        int ord = getMaxOrd();

        Connection conn = DBConnect.getConnection();

        if (posVoce < 0 || posVoce > ord) {
            return false;
            // index out of allowed range!
        }
        try {
            /* now delete item */
            Statement st = conn.createStatement();

            String sqldelete = "DELETE FROM spesa WHERE ord=" + posVoce;

            int res = st.executeUpdate(sqldelete);

            if (res != 1) {
                Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE, "DELETE did not return 1");
                throw new RuntimeException("database error in " + this.getClass().getSimpleName());
            }

            /* now decrease all other indexes by 1 */
            String sqldecrease = "update spesa set ord=ord-1 where ord>" + posVoce;

            res = st.executeUpdate(sqldecrease);
            st.close();
            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("database error in " + this.getClass().getSimpleName(), ex);
        }
    }

    /**
     * Azzera completamente il contenuto della lista della spesa nel database
     */
    public void pulisci() {
        try {
            Connection conn = DBConnect.getConnection();
            Statement st = conn.createStatement();
            String sqlWipeAll = "truncate spesa";
            st.execute(sqlWipeAll);
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("database error in " + this.getClass().getSimpleName(), ex);
        }
    }

    /**
     * Ritorna la stringa corrispondente ad una specifica voce della spesa
     * 
     * @param posVoce il numero d'ordine (compreso tra 0 e getMaxOrd(), estremi compresi)
     * dell'elemento da leggere
     * 
     * @return il messaggio corrispondente alla posizione <code>posVoce</code>,
     * oppure <code>null</code> se il numero d'ordine è errato
     */
    public String getMsg(int posVoce) {
        String msg;

        try {
            Connection conn = DBConnect.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT voce FROM spesa WHERE ord=" + posVoce;
            ResultSet res = st.executeQuery(sql);

            if (res.next()) {
                msg = res.getString("voce");
                st.close();
                conn.close();
                return msg;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("database error in " + this.getClass().getSimpleName(), ex);
        }
        return null;
    }

    /**
     * Modifica il testo di una voce della lista della spesa.
     * 
     * @param posVoce la posizione dell'elemento da modificare
     * 
     * @param msg il nuovo testo da memorizzare
     * 
     * @return <code>true</code> se la modifica è avvenuta con successo,
     * <code>false</code> altrimenti
     */
    public boolean modifica(int posVoce, String msg) {

        int ord = getMaxOrd();

        Connection conn = DBConnect.getConnection();

        if (posVoce < 0 || posVoce > ord) {
            return false;
            // index out of allowed range!
        }
        try {
            /* now delete item */

            String sql = "UPDATE spesa SET voce=? WHERE ord=?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, msg);
            st.setInt(2, posVoce);

            // String sql = "UPDATE spesa SET voce=\"" + msg + "\" WHERE ord=" + posVoce;

            int res = st.executeUpdate();

            if (res != 1) {
                Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE, "Update did not return 1");
                throw new RuntimeException("database error in " + this.getClass().getSimpleName());
            }
            st.close();
            conn.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("database error in " + this.getClass().getSimpleName(), ex);
        }
    }

    /**
     * Modifica il numero d'ordine di una voce esistente
     * 
     * @param conn la connessione con il database da utilizzare
     * @param vecchioOrd valore del numero d'ordine dell'elemento da modificare
     * @param nuovoOrd nuovo numero d'ordine da associare a tale elemento
     * @return <code>true</code> se la modifica è avvenuta con successo, <code>false</code>
     * altrimenti
     * @throws SQLException eventuale eccezione generata dalla query. In particolare
     * può verificarsi a causa di violazioni del vincolo di unicità sulla colonna ORD,
     * nel caso in cui <code>nuovoOrd</code> fosse uguale ad un valore già presente
     * nella tabella.
     */
    private boolean forzaOrd(Connection conn, int vecchioOrd, int nuovoOrd) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "UPDATE spesa SET ord=" + nuovoOrd + " WHERE ord=" + vecchioOrd;
        int res = st.executeUpdate(sql);
        st.close();
        return (res == 1);
    }

    /**
     * Data una voce dell'elenco, la scambia di posto con la voce immediatamente
     * precedente
     * @param posVoce la posizione della voce da spostare, deve essere compreso
     * tra 1 e maxOrd. Attenzione: il valore minimo è 1, non 0
     * @return  <code>true</code> se lo scambio è avvenuto con successo, <code>false</code>
     * altrimenti
     */
    public boolean spostaSu(int posVoce) {
        int ord = getMaxOrd();

        Connection conn = DBConnect.getConnection();

        if (posVoce < 1 || posVoce > ord) {
            return false;
            // index out of allowed range!
        }
        try {
            forzaOrd(conn, posVoce, 9999);
            forzaOrd(conn, posVoce - 1, posVoce);
            forzaOrd(conn, 9999, posVoce - 1);
            conn.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("database error in " + this.getClass().getSimpleName(), ex);
        }
    }

    /**
     * Data una voce dell'elenco, la scambia di posto con la voce immediatamente
     * successiva
     * 
     * @param posVoce la posizione della voce da spostare, deve essere compreso
     * tra 0 e maxOrd-1
     * @return <code>true</code> se lo scambio è avvenuto con successo, <code>false</code>
     * altrimenti
     */
    public boolean spostaGiu(int posVoce) {
        return spostaSu(posVoce + 1);
    }
}
