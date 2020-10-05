package com.scpakar.scpakarappws.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author alberto.quirino
 */
public class ContactoDTO implements Serializable {

    private String nombre;
    private String correo;
    private String telefono;
    private String celular;
    private String mensaje;
    private Date fecha;

    public ContactoDTO() {
        this.nombre = "";
        this.correo = "";
        this.telefono = "";
        this.celular = "";
        this.mensaje = "";
        this.fecha = new Date();
    }

    public ContactoDTO(String nombre, String correo, String telefono, String celular, String mensaje) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.celular = celular;
        this.mensaje = mensaje;
        this.fecha = new Date();
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ContactoDTO{" + "nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono + ", celular=" + celular + ", mensaje=" + mensaje + ", fecha=" + fecha + '}';
    }

}
