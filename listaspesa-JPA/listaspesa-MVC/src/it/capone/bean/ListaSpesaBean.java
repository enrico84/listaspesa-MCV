/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.capone.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe per rappresentare il contenuto della lista della spesa.
 * Contiene tutti i metodi per manipolare l'elenco di {@link Voce}.
 * 
 * @author Fulvio
 */
public class ListaSpesaBean {

    private List<Voce> spesa;
    

    /*
     * Costruttore di default -- crea una lista della spesa
     * vuota, con zero voci.
     */
    public ListaSpesaBean() {
        this.spesa = new ArrayList<Voce>();
    }

    /**
     * Aggiunge una nuova voce all'elenco
     * 
     * @param testo la stringa di testo corrispondente alla voce da aggiungere
     */
    public void aggiungi(String testo) {
        Voce voce = new Voce(testo);
        spesa.add(voce);
    }
    
    //CAPONE
    public void aggiungiVoce(Voce v) {
    	spesa.add(v);
    }
    

    public boolean elimina(int posVoce) {
        if (posVoce >= 0 && posVoce < spesa.size()) {
            spesa.remove(posVoce);
            return true;
        }

        return false;
    }

    /**
     * Modifica il testo presente in una certa voce dell'elenco.
     * 
     * @param posVoce Posizione della voce da modificare. Deve essere compreso
     * tra 0 e size-1
     * @param nuovoTesto Nuova stringa da sostituire alla precedente
     * @return <code>true</code> se la sostituzione ha avuto successo, <code>false</code> altrimenti.
     * 
     */
    public boolean modifica(int posVoce, String nuovoTesto) {
        if (posVoce >= 0 && posVoce < spesa.size()) {

            Voce nuova = new Voce(nuovoTesto);
            spesa.set(posVoce, nuova);

            return true;
        }

        return false;
    }

    /**
     * Sposta in alto la voce selezionata nella lista.
     * 
     * @param posVoce l'indice della voce da spostare in alto (compreso tra 1 e size-1)
     * @return <code>true</code> se lo spostamento ha avuto successo, <code>false</code> altrimenti.
     */
    public boolean spostaSu(int posVoce) {
        if (posVoce < 1 || posVoce > spesa.size() - 1) {
            return false;
        } else {
            Voce temp = spesa.get(posVoce - 1);
            spesa.set(posVoce - 1, spesa.get(posVoce));
            spesa.set(posVoce, temp);
            return true;
        }
    }
    
    /**
     * Sposta in basso la voce selezionata nella lista.
     * 
     * @param posVoce l'indice della voce da spostare in basso (compreso tra 0 e size-2)
     * @return <code>true</code> se lo spostamento ha avuto successo, <code>false</code> altrimenti.
     */
    public boolean spostaGiu(int posVoce) {
        if (posVoce < 0 || posVoce > spesa.size() - 2) {
            return false;
        } else {
            Voce temp = spesa.get(posVoce + 1);
            spesa.set(posVoce + 1, spesa.get(posVoce));
            spesa.set(posVoce, temp);
            return true;
        }
    }
    
    /**
     * Elimina il contenuto della lista della spesa
     */
    public void pulisci() {
        spesa.clear();
    }

    /**
     * Restituisce l'elenco di tutte le voci memorizzate
     * @return una collection di stringhe con le voci della lista
     */
    public List<Voce> getSpesa() {
        return spesa;
    }
    
    /**
     * 
     * @return la dimensione della lista
     */
    public int getDimensione(){
		int i=0;
		for(Voce v : this.spesa) {
			i++;
		}
		return i;
	}
}
