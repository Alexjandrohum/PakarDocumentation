package com.scpakar.scpakarweb.dto;

import java.io.Serializable;

/**
 *
 * @author pablo.martinez
 */
public enum Pais implements Serializable {
    MX("MX"),
    US("US");
    String idPais;

    private Pais(String idPais) {
        this.idPais = idPais;
    }

    public String getIdPais() {
        return idPais;
    }

    public static Pais getMX() {
        return MX;
    }

    public static Pais getUS() {
        return US;
    }

}
