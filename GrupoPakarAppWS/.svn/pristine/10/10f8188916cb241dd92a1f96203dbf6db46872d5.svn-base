/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupopakar.grupopakarappws.dto;

import com.grupopakar.grupopakarappws.util.Cifrado;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author carlos.juarez
 */
@XmlRootElement
public class CredencialesDTO implements Serializable {

    private String usuario;
    private String password;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CredencialesDTO{[" + new Cifrado().cryptText(usuario) + "] \t[" + new Cifrado().cryptText(password) + "] }";
    }
    
    

}
