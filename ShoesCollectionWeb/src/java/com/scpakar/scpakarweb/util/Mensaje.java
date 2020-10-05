package com.scpakar.scpakarweb.util;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Lalo
 */
public class Mensaje implements Serializable {

    FacesContext facesContext;
    FacesMessage mensaje;

    public Mensaje() {
        facesContext = FacesContext.getCurrentInstance();
        this.mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, getClass().getName(), getClass().toString());
    }

    public Mensaje(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public void enviaMensaje(String titulo, String mensaje) {
        this.mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje);
        facesContext.addMessage(null, this.mensaje);
    }

    public void enviaAlerta(String titulo, String mensaje) {
        this.mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensaje);
        facesContext.addMessage(null, this.mensaje);
    }

    public void enviaError(String titulo, String mensaje) {
        this.mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensaje);
        facesContext.addMessage(null, this.mensaje);
    }

    public void enviaFatal(String titulo, String mensaje) {
        this.mensaje = new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensaje);
        facesContext.addMessage(null, this.mensaje);
    }

    public void enviaMensaje(String idComponente, String titulo, String mensaje) {
        this.mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje);
        facesContext.addMessage(idComponente, this.mensaje);
    }

    public void enviaAlerta(String idComponente, String titulo, String mensaje) {
        this.mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensaje);
        facesContext.addMessage(idComponente, this.mensaje);
    }

    public void enviaError(String idComponente, String titulo, String mensaje) {
        this.mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensaje);
        facesContext.addMessage(idComponente, this.mensaje);
    }

    public void enviaFatal(String idComponente, String titulo, String mensaje) {
        this.mensaje = new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensaje);
        facesContext.addMessage(idComponente, this.mensaje);
    }
}
