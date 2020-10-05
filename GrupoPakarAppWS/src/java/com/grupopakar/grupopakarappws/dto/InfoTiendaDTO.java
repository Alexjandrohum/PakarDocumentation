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
public class InfoTiendaDTO implements Serializable {

    private int idInformacionTienda;
    private String nombreConcepto;
    private String valorConcepto;
    private boolean editable;

    public InfoTiendaDTO() {
        this.idInformacionTienda = 0;
        this.nombreConcepto = "";
        this.valorConcepto = "";
        this.editable = false;
    }

    public int getIdInformacionTienda() {
        return idInformacionTienda;
    }

    public void setIdInformacionTienda(int idTiendaInfo) {
        this.idInformacionTienda = idTiendaInfo;
    }

    public String getValorConcepto() {
        return valorConcepto;
    }

    public void setValorConcepto(String valorConcepto) {
        this.valorConcepto = valorConcepto;
    }

    public String getNombreConcepto() {
        return nombreConcepto;
    }

    public void setNombreConcepto(String nombreConcepto) {
        this.nombreConcepto = nombreConcepto;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public String toString() {
        return "InfoTiendaDTO{" + "idInformacionTienda=" + idInformacionTienda + ", nombreConcepto=" + nombreConcepto + ", valorConcepto=" + valorConcepto + ", editable=" + editable + '}';
    }

}
