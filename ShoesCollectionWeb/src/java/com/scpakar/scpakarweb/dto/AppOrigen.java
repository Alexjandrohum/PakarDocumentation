package com.scpakar.scpakarweb.dto;

import java.io.Serializable;

/**
 *
 * @author pablo.martinez
 */
public enum AppOrigen implements Serializable {
    NO_ACTIVO(0, "No Activo"),
    WEB(1, "Web"),
    ANDROID(2, "Android"),
    IOS(3, "iOS");
    private int idOrigen;
    private String nombre;

    private AppOrigen(int idOrigen, String nombre) {
        this.idOrigen = idOrigen;
        this.nombre = nombre;
    }

    public int getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static AppOrigen getOrigen(int idOrigen) {
        AppOrigen appOrigen = AppOrigen.NO_ACTIVO;
        for (AppOrigen app : AppOrigen.values()) {
            if (app.getIdOrigen() == idOrigen) {
                appOrigen = app;
                break;
            }
        }
        return appOrigen;
    }
}
