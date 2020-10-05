package com.scpakar.scpakarappws.dto;

import java.io.Serializable;

public enum ChatEstatus implements Serializable {
    ENVIADO(1, "Enviado"),
    RECIBIDO(2, "Recibido");

    private final int idEstatus;
    private final String nombre;

    ChatEstatus(int idEstatus, String nombre) {
        this.idEstatus = idEstatus;
        this.nombre = nombre;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public String getNombre() {
        return nombre;
    }

    public static ChatEstatus getChatEstatus(int idEstatus) {
        for (ChatEstatus e : ChatEstatus.values()) {
            if (e.getIdEstatus() == idEstatus) {
                return e;
            }
        }
        return ENVIADO;
    }
}
