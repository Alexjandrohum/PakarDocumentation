package com.scpakar.scpakarweb.dto;

import java.io.Serializable;

public enum ChatOrigen implements Serializable {
    PAKAR(1, "Pakar"),
    SOCIO(2, "Socio");

    private final int idOrigen;
    private final String nombre;

    ChatOrigen(int idOrigen, String nombre) {
        this.idOrigen = idOrigen;
        this.nombre = nombre;
    }

    public int getIdOrigen() {
        return idOrigen;
    }

    public String getNombre() {
        return nombre;
    }

    public static ChatOrigen getChatOrigen(int idOrigen) {
        for (ChatOrigen e : ChatOrigen.values()) {
            if (e.getIdOrigen() == idOrigen) {
                return e;
            }
        }
        return PAKAR;
    }
}
