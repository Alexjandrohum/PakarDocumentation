package com.scpakar.scpakarweb.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author nicolas.canaan
 */
public class ConfirmaDTO implements Serializable{
    
    private String idSocio;
    private String nombre;
    private int referencia;
    private Date fecha;
    private String comprobante;
    private String temporada;

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

}
