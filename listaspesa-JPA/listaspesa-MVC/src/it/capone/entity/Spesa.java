package it.capone.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the spesa database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Spesa.findAll", query="SELECT s FROM Spesa s"),
	@NamedQuery(name="Spesa.findAllOrd", query="SELECT s FROM Spesa s ORDER BY s.ord"),
	@NamedQuery(name="Spesa.findMaxOrd", query="SELECT MAX(s.ord) FROM Spesa s")
})

public class Spesa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idlista;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private int ord;

	private String voce;

	public Spesa() {
	}

	public int getIdlista() {
		return this.idlista;
	}

	public void setIdlista(int idlista) {
		this.idlista = idlista;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getOrd() {
		return this.ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}

	public String getVoce() {
		return this.voce;
	}

	public void setVoce(String voce) {
		this.voce = voce;
	}

}