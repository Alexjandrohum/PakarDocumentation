package com.scpakar.scpakarweb.dto;

import java.io.Serializable;

/**
 *
 * @author antonio.ruiz
 */
public class SocioDTO implements Serializable {

    private String serie;
    private String codigo;
    private String idSocio;
    private String nombre;
    private String correo;
    private boolean bloqueado;
    private boolean temporadaActual;

    public SocioDTO() {
        this.serie = "";
        this.codigo = "";
        this.idSocio = "";
        this.nombre = "";
        this.correo = "";
    }

    public boolean isValido() {
        return !idSocio.isEmpty() && !nombre.isEmpty() && !bloqueado;
    }

    public boolean isTemporadaActual() {
        return temporadaActual;
    }

    public void setTemporadaActual(boolean temporadaActual) {
        this.temporadaActual = temporadaActual;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    @Override
    public String toString() {
        return "SocioDTO{" + "serie=" + serie + ", codigo=" + codigo + ", idSocio=" + idSocio + ", nombre=" + nombre + ", correo=" + correo + ", bloqueado=" + bloqueado + ", temporadaActual=" + temporadaActual + '}';
    }

}
