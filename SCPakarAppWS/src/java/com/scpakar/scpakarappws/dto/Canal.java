package com.scpakar.scpakarappws.dto;

/**
 *
 * @author pablo.martinez
 */
public enum Canal {
    APP(0, "SC Pakar APP"),
    WEB(1, "Catálogo digital WEB");

    private int idCanal;
    private String nombreCanal;

    private Canal(int idCanal, String nombreCanal) {
        this.idCanal = idCanal;
        this.nombreCanal = nombreCanal;
    }

    public int getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(int idCanal) {
        this.idCanal = idCanal;
    }

    public String getNombreCanal() {
        return nombreCanal;
    }

    public void setNombreCanal(String nombreCanal) {
        this.nombreCanal = nombreCanal;
    }

}
