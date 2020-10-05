package com.grupopakar.grupopakarappws.dto;

import java.io.Serializable;

/**
 *
 * @author alberto.quirino
 */
public class InfoTareaDTO implements Serializable {
    
    private int idTarea;
    private int idTienda;
    private boolean valorEstatusTarea;

    public InfoTareaDTO() {
        this.idTarea = 0;
        this.idTienda = 0;
        this.valorEstatusTarea = false;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public boolean isValorEstatusTarea() {
        return valorEstatusTarea;
    }

    public void setValorEstatusTarea(boolean valorEstatusTarea) {
        this.valorEstatusTarea = valorEstatusTarea;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    @Override
    public String toString() {
        return "InfoTareaDTO{" + "idTarea=" + idTarea + ", idTienda=" + idTienda + ", valorEstatusTarea=" + valorEstatusTarea + '}';
    }
    
}
