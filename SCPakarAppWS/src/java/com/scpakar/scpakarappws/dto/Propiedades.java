package com.scpakar.scpakarappws.dto;

import java.io.Serializable;

/**
 *
 * @author pablo.martinez
 */
public enum Propiedades implements Serializable{

    DIAS_PRODUCTO_DUPLICADO("Dias item duplicado"),
    HORAS_PRORROGA("Horas Prorroga App"),
    EMPTY(""),
    ENVIO_DISPONIBLE_APP("Envio Disponible App");

    private final String clave;

    private Propiedades(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

}
