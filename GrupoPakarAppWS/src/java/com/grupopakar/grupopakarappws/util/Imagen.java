package com.grupopakar.grupopakarappws.util;

import java.io.Serializable;

/**
 *
 * @author pablo.martinez
 */
public enum Imagen implements Serializable {
    CONEXION("conexion.png"),
    FOTO_EMPLEADO("foto.png"),
    FOTO_TIENDA("fachada.png");

    @SuppressWarnings("FieldMayBeFinal")
    private String valor;

    private Imagen(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
