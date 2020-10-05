package com.grupopakar.grupopakarappws.dto;

import java.io.Serializable;

/**
 *
 * @author alberto.quirino
 */
public class TareaDTO implements Serializable{

    private int idTarea;
    private String nombreTarea;
    private boolean estatusTarea;

    public TareaDTO() {
        this.idTarea = 0;
        this.nombreTarea = "";
        this.estatusTarea = false;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public boolean isEstatusTarea() {
        return estatusTarea;
    }

    public void setEstatusTarea(boolean estatusTarea) {
        this.estatusTarea = estatusTarea;
    }
     
}
