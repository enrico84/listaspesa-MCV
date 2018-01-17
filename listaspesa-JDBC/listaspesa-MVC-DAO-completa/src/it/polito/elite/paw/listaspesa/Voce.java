/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polito.elite.paw.listaspesa;

import java.util.GregorianCalendar;

/**
 * 
 * @author Fulvio
 */
public class Voce {
    
    private String msg;
    private int ord;
    private GregorianCalendar date;

	public Voce() {
    	
    }
    
    public Voce(String msg) {
        this.msg = msg ;
    }
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public int getOrd(){
    	return this.ord;
    }
    
    public void setOrd(int ord){
    	this.ord = ord;
    }
    
    public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
    
}
