package com.scpakar.scpakarappws.dto;

import java.io.Serializable;

/**
 *
 * @author antonio.ruiz
 */
public class SucursalDTO implements Serializable {

    private String idAlmacen;
    private String nombrePublico;
    private float latitud;
    private float longitud;
    private String whatsapp;
    private String telefono;
    private String telSinCosto;
    private String horarioAtencion;
    private String tipo;
    private String direccion;
    private String nombreEstado;
    
    public SucursalDTO() {
        this.idAlmacen = "";
        this.nombrePublico = "";
        this.whatsapp = "";
        this.telefono = "";
        this.telSinCosto = "";
        this.horarioAtencion = "";
        this.tipo = "";
        this.direccion = "";
        this.nombreEstado = "";
    }

    public String getIdAlmacen() {
        return idAlmacen;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public void setIdAlmacen(String idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getNombrePublico() {
        return nombrePublico;
    }

    public void setNombrePublico(String nombrePublico) {
        this.nombrePublico = nombrePublico;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelSinCosto() {
        return telSinCosto;
    }

    public void setTelSinCosto(String telSinCosto) {
        this.telSinCosto = telSinCosto;
    }

    public String getHorarioAtencion() {
        return horarioAtencion;
    }

    public void setHorarioAtencion(String horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
