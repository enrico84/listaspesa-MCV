package it.capone.test;

import it.capone.service.CGestioneSpesa;

import java.util.List;

import it.capone.bean.ListaSpesaBean;
import it.capone.db.ListaSpesaEjb;
import it.capone.bean.Voce;

public class Main {

	public static void main(String[] args) {
		
		CGestioneSpesa cGestSpesa = new CGestioneSpesa();

	    ListaSpesaBean listaSpesaFirst = new ListaSpesaBean();
	    
	    // interroga il database per ottenere l'elenco delle voci
	    cGestSpesa.fillListaSpesa(listaSpesaFirst);
	    
	    List<Voce> lista = listaSpesaFirst.getSpesa();
	    
//	    Voce[] lista = (Voce[]) listaSpesaFirst.getSpesa().
//	    		toArray(new Voce[listaSpesaFirst.getDimensione()] ); 
	    stampaSpesa(lista);
	    
	  //OPERAZIONE DA EFFETTUARE SEMPRE ALLA FINE PER CHIUDERE L'ENTITY MANAGER
	    cGestSpesa.closeLogica();

	    
	}
	
	public static void stampaSpesa(List<Voce> listaSpesa) {
		
		if(!listaSpesa.isEmpty()) {
			for(Voce v : listaSpesa) {
				System.out.println("Prodotto: " +v.getMsg()+ ", ord - " +v.getOrd());
			}
		}
		else
			System.out.println("Lista vuota");
			
	}

}
