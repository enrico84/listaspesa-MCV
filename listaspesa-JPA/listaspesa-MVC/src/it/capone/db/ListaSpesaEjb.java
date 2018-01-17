package it.capone.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import it.capone.bean.ListaSpesaBean;
import it.capone.entity.Spesa;

/**
 * Session Bean implementation class ListaSpesaEjb
 */
@Stateless
@LocalBean
public class ListaSpesaEjb implements ListaSpesaEjbRemote, ListaSpesaEjbLocal {

	LogicaJPA logica = new LogicaJPA("listaspesa-MVC");
	
    /**
     * Default constructor. 
     */
    public ListaSpesaEjb() {}
    
    /**
     * Legge dal database l'intera lista della spesa (in ordine), e la 
     * memorizza nel parametro <code>spesa</code>
     * 
     * @param spesa un {@link ListaSpesaBean} che agisce come Transfer Object 
     * e viene riempito con il contenutod della lista dal database. Il contenuto
     * precedente della lista viene cancellato.
     */
    @Override
    public List<Spesa> fillListaSpesa() {
        
        List<Spesa> listaspesa = null;
        
        try {
        	listaspesa = logica.readNameQuery("Spesa.findAllOrd").getResultList();
        	logica.closeLogicaJPA();
    	}
    	catch(NoResultException nores) {
    		listaspesa = new ArrayList<Spesa>();
    	}
        
        return listaspesa;
    } 
  

    /**
     * Aggiunge una nuova voce alla lista della spesa nel database (in fondo all'elenco)
     * 
     * @param voce la stringa da aggiungere
     */
    @Override
    public void aggiungi(Spesa spesa) {

    	logica.create(spesa);
    	//logica.closeLogicaJPA();
    }

    /**
     * Ritorna il massimo numero d'ordine presente nella lista della spesa
     * 
     * @return il valore massimo del numero d'ordine, se vi sono elementi memorizzati.
     * Se invece il database non contiene ancora elementi, ritorna <code>-1</code>
     */
    public int getMaxOrd() {
    	Integer maxOrd = (Integer) logica.readNameQuery("Spesa.findMaxOrd").getSingleResult();
        if(maxOrd == null) { 
        	maxOrd = -1;		// in case of empty table => return (-1), so that first element will be #0
        }
        	
    	return maxOrd;

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

    	boolean fatto=false;
        int ord = getMaxOrd();

        if (posVoce < 0 || posVoce > ord) {
            return false;
            // index out of allowed range!
        }
        
            /* now delete item */
        Query q = logica.readSimpleQuery("SELECT idlista from Spesa s WHERE s.ord = :ordine");
        q.setParameter("ordine", posVoce);
            
        Integer idVoce = (Integer) q.getSingleResult();
        Spesa spesa = (Spesa) logica.find(new Spesa(), idVoce);
        fatto=logica.delete(spesa);
            

           /* now decrease all other indexes by 1 */
        Query q1 = logica.readSimpleQuery("SELECT s from Spesa s WHERE s.ord > :ordine");
        q1.setParameter("ordine", posVoce);
            
        List<Spesa> listaProd = (List<Spesa>) q1.getResultList();
        for(Spesa prodotto : listaProd) {
        	//Spesa prodotto = (Spesa) logica.find(new Spesa(), id);
        	prodotto.setOrd(posVoce);
        	fatto=logica.update(prodotto);
        	if(fatto==false)
        		continue;
        	posVoce++;
        }
        
        logica.closeLogicaJPA();
        
        //String sqldecrease = "update spesa set ord=ord-1 where ord>" + posVoce;

        return fatto;
      
    }

    /**
     * Azzera completamente il contenuto della lista della spesa nel database
     */
    public void pulisci() {
        //int q = logica.readSimpleQuery("TRUNCATE TABLE Spesa").executeUpdate();
        logica.truncate("Spesa");
        logica.closeLogicaJPA();
        
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
    	
    	Query q = logica.readSimpleQuery("SELECT voce from Spesa s WHERE s.ord = :ordine");
    	q.setParameter("ordine", posVoce);
    	
        String msg = (String) q.getSingleResult();
    
        return msg;
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

    	boolean modificato=false;
        int ord = getMaxOrd();
        
        if (posVoce < 0 || posVoce > ord) {
            return modificato;
            // FALSE, index out of allowed range!
        }
       
        Spesa spesa = new Spesa();
        Spesa s = new Spesa();
        Query q = logica.readSimpleQuery("SELECT idlista from Spesa s WHERE s.ord = :ordine");
        q.setParameter("ordine", posVoce);
        
        Integer idVoce = (Integer) q.getSingleResult();
        spesa = (Spesa) logica.find(s, idVoce);
        
        if(spesa != null) {
        	 spesa.setVoce(msg);   
             modificato = logica.update(spesa);
        }
        
        logica.closeLogicaJPA();
        
        return modificato;
             
    }
    
    
    @Override
    public void closeLogicaJPA() {
    	logica.closeLogicaJPA();
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
    private boolean forzaOrd(int vecchioOrd, int nuovoOrd) throws SQLException {
    	
    	boolean fatto = false;
    	Query q = logica.readSimpleQuery("SELECT idlista from Spesa s WHERE s.ord = :ordine");
        q.setParameter("ordine", vecchioOrd);
         
        Integer idVoce = (Integer) q.getSingleResult();
    	Spesa voce = (Spesa) logica.find(new Spesa(), idVoce);
    	voce.setOrd(nuovoOrd);
    	fatto = logica.update(voce);
    	
    	return fatto;
    	
        
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
    	boolean ordinato = false;
    	
        int ord = getMaxOrd();

        if (posVoce < 1 || posVoce > ord) {
            return false;
            // index out of allowed range!
        }
        
       try { 
	       ordinato = forzaOrd(posVoce, 9999);
	       ordinato = forzaOrd(posVoce - 1, posVoce);
	       ordinato = forzaOrd(9999, posVoce - 1);
       }
	   catch (SQLException ex) {
	       Logger.getLogger(ListaSpesaEjb.class.getName()).log(Level.SEVERE, null, ex);
	       throw new RuntimeException("database error in " + this.getClass().getSimpleName(), ex);          
		} 

       return ordinato;
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
