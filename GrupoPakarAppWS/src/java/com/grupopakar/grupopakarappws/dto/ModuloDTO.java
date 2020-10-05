package com.grupopakar.grupopakarappws.dto;

import java.io.Serializable;

/**
 *
 * @author carlos.juarez
 */
public class ModuloDTO implements Serializable {

    private transient int idUsuario;
    private transient int idPerfil;
    private int idModulo;
    private String nombreModulo;
    private String icono;

    public int getIdModulo() {
        return idModulo;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

}
