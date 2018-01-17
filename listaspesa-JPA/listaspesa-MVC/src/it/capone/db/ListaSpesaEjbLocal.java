package it.capone.db;

import java.util.List;

import javax.ejb.Local;

import it.capone.bean.ListaSpesaBean;
import it.capone.entity.Spesa;

@Local
public interface ListaSpesaEjbLocal {

	List<Spesa> fillListaSpesa();
	
	void aggiungi(Spesa spesa);
	
	int getMaxOrd();
	
	String getMsg(int posVoce);
	
	boolean modifica(int posVoce, String msg);
	
	boolean elimina(int posVoce);
	
	boolean spostaSu(int posVoce);
	
	boolean spostaGiu(int posVoce);
	
	void pulisci();
	
	void closeLogicaJPA();
	
}
