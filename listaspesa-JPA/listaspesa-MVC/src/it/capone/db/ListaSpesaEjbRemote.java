package it.capone.db;

import java.util.List;

import javax.ejb.Remote;

import it.capone.bean.ListaSpesaBean;
import it.capone.entity.Spesa;


@Remote
public interface ListaSpesaEjbRemote {

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
