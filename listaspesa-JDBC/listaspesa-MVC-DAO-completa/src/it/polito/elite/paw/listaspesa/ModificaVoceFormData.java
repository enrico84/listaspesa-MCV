/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polito.elite.paw.listaspesa;

/**
 *
 * @author Fulvio
 */
public class ModificaVoceFormData {
    private int posVoce ;
    private Voce voce ;

    public ModificaVoceFormData() {
        posVoce = -1 ;
        voce = new Voce("") ;
    }

    public ModificaVoceFormData(int posVoce, Voce voce) {
        this.posVoce = posVoce;
        this.voce = voce;
    }

    public int getPosVoce() {
        return posVoce;
    }

    public void setPosVoce(int posVoce) {
        this.posVoce = posVoce;
    }

    public Voce getVoce() {
        return voce;
    }

    public void setVoce(Voce voce) {
        this.voce = voce;
    }
    
    public void setMsg(String msg) {
        this.voce.setMsg(msg);
    }
    
    public String getMsg() {
        return this.voce.getMsg() ;
    }
}
