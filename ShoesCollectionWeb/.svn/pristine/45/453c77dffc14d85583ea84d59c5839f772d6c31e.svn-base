package com.scpakar.scpakarweb.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pablo.martinez
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

    public SucursalDTO() {
        this.idAlmacen = "";
        this.nombrePublico = "";
        this.whatsapp = "";
        this.telefono = "";
        this.telSinCosto = "";
        this.horarioAtencion = "";
        this.tipo = "";
        this.direccion = "";
    }

    public String getNombreDireccion() {
        return nombrePublico + " / " + direccion;
    }

    public String getIdAlmacen() {
        return idAlmacen;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.idAlmacen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SucursalDTO other = (SucursalDTO) obj;
        if (!Objects.equals(this.idAlmacen, other.idAlmacen)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SucursalDTO{" + "idAlmacen=" + idAlmacen + ", nombrePublico=" + nombrePublico + ", latitud=" + latitud + ", longitud=" + longitud + ", whatsapp=" + whatsapp + ", telefono=" + telefono + ", telSinCosto=" + telSinCosto + ", horarioAtencion=" + horarioAtencion + ", tipo=" + tipo + ", direccion=" + direccion + '}';
    }

}
