/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupopakar.grupopakarappws.dto;

import java.io.Serializable;

/**
 *
 * @author rosa.zalas
 */
public class NotificacionDTO implements Serializable {
    private int idNotificacion;
    private int idModulo;
    private int idTienda;
    private String fechaNotificacion;
    private String cuerpo;
    
    public NotificacionDTO(){
        this.idNotificacion = 0;
        this.idModulo = 0;
        this.idTienda = 0;
        this.fechaNotificacion = "";
        this.cuerpo = "";
    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public String getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(String fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
    
    
}
