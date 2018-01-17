package it.capone.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.capone.bean.ListaSpesaBean;
import it.capone.bean.Voce;
import it.capone.db.DBConnect;
import it.capone.db.ListaSpesaDAO;
import it.capone.db.ListaSpesaEjb;
import it.capone.db.ListaSpesaEjbRemote;
import it.capone.entity.Spesa;

/**
 * 
 * @author Enrico
 *	STRATO SERVICE
 */
public class CGestioneSpesa {

	private ListaSpesaEjbRemote spesaEjb = new ListaSpesaEjb();
	
	public void fillListaSpesa(ListaSpesaBean spesa) {
		
		List<Spesa> listaspesa = spesaEjb.fillListaSpesa();
		System.out.println("Lista EJB");
		if(!listaspesa.isEmpty()){
			
			for(Spesa s : listaspesa){
				Voce singolaVoce = new Voce();
				singolaVoce.setMsg(s.getVoce());
				singolaVoce.setOrd(s.getOrd());
				spesa.aggiungiVoce(singolaVoce);
			}
			
		}
	}
	
	
	 public void aggiungi(String voce) {
		 System.out.println("Aggiunta EJB");
		 
		 int ord = spesaEjb.getMaxOrd();
		 ord++;
		 GregorianCalendar dataAttuale = new GregorianCalendar();
		 GregorianCalendar dataInserita = new GregorianCalendar();
		 int anno = dataAttuale.get(GregorianCalendar.YEAR);
		 int mese = dataAttuale.get(GregorianCalendar.MONTH) + 1; //i mesi partono da 0
		 int giorno = dataAttuale.get(GregorianCalendar.DATE);
		 int ore = dataAttuale.get(GregorianCalendar.HOUR);
		 int minuti = dataAttuale.get(GregorianCalendar.MINUTE);
		 int secondi = dataAttuale.get(GregorianCalendar.SECOND);
		 
		 dataInserita.set(anno, mese, giorno, ore, minuti, secondi);
		 Date data = dataInserita.getTime();
		 
		 Spesa spesa = new Spesa();
		 spesa.setVoce(voce);
		 spesa.setData(data);
		 spesa.setOrd(ord);

		 spesaEjb.aggiungi(spesa);
		 
	 }
	 
	 public String getMsg(int posizione) {
		 
		 String voce = spesaEjb.getMsg(posizione);
		 return voce;
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
	    	
	    	boolean modificato = false;
	    	
	    	modificato = spesaEjb.modifica(posVoce, msg);
	    	
	    	return modificato;
	       
	    }
	 
	 
	    /**
	     * 
	     * @param posVoce -> posizione del prodotto da eliminare
	     * @return true se l'eliminazione va a buon fine, false altrimenti
	     * Elimina il singolo elemento della lista
	     */
	    public boolean elimina(int posVoce) {
	    	return spesaEjb.elimina(posVoce);
	    }
	    
	    
	    /**
	     * Svuota tutta la lista
	     */
	    public void pulisci() {
	    	spesaEjb.pulisci();
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
	        
	    	return spesaEjb.spostaSu(posVoce);
	   
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
	 
	 
	 public void closeLogica() {
		 spesaEjb.closeLogicaJPA();
	 }
	
}
