/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scpakar.scpakarappws.dto;

import java.io.Serializable;

/**
 *
 * @author alexjandrohum
 */
public class ValesDTO implements Serializable{
    private boolean valido;
    private String resultado;
    
    public ValesDTO(){
        this.resultado = "";
    }
    

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    @Override
    public String toString() {
        return "ValesDTO{" + "valido=" + valido + ", resultado=" + resultado +'}';
    }
    
}
