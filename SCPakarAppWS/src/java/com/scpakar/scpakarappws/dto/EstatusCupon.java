package com.scpakar.scpakarappws.dto;

import java.io.Serializable;

/**
 * Created by antonio.ruiz on 2019-06-21
 */
public enum EstatusCupon implements Serializable {
    VIGENTE("VIGENTES", "ic_vigente_bar"),
    CANJEADO("CANJEADOS", "ic_canjeado_bar"),
    VENCIDO("VENCIDOS", "ic_vencido_bar"),
    HISTORIAL("HISTORIAL", "ic_hourglass_bar");

    private final String nombre;
    private final String icono;

    EstatusCupon(String nombre, String icono) {
        this.nombre = nombre;
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIcono() {
        return icono;
    }
}
