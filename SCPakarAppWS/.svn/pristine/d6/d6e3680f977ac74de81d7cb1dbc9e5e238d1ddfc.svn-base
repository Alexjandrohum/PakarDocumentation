package com.scpakar.scpakarappws.dto;

import java.io.Serializable;

/**
 * Created by antonio.ruiz on 04/04/20
 */
public enum TipoEnvio implements Serializable {
    NINGUNO(-1),
    TODOS(0),
    DOMICILIO(1),
    TIENDA(2);

    private int id;

    TipoEnvio(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TipoEnvio getTipoEnvio(int id) {
        for (TipoEnvio e : TipoEnvio.values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return NINGUNO;
    }
}
