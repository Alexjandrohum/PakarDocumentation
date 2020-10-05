package com.scpakar.scpakarweb.dto;

import java.io.Serializable;

/**
 *
 * @author pablo.martinez
 */
public enum TipoAlmacen implements Serializable{
    CT("CT"),
    CA("CA"),
    BD("BD");
    private String tipo;

    private TipoAlmacen(String tipo) {
        this.tipo = tipo;
    }

    public static TipoAlmacen getCT() {
        return CT;
    }

    public static TipoAlmacen getCA() {
        return CA;
    }

    public static TipoAlmacen getBD() {
        return BD;
    }

    public String getTipo() {
        return tipo;
    }

}
