package com.scpakar.scpakarappws.dto;

import java.io.Serializable;

/**
 *
 * @author pablo.martinez
 */
public enum TipoMovimientoPedido implements Serializable {
    PRORROGA(1, "Prorroga"),
    ABANDONADO(2, "Abandonado"),
    REVISADO(3, "Revisado"),
    REVERTIR_REVISADO(4, "Revertir Revisado");
    private int idTipo;
    private String nombre;

    private TipoMovimientoPedido(int idTipo, String nombre) {
        this.idTipo = idTipo;
        this.nombre = nombre;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
