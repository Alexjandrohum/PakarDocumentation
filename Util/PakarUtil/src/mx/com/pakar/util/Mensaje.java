package mx.com.pakar.util;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Permite el envío de mensajes desde los <code>ManagedBean</code> hasta la
 * interfaz de usuario.
 *
 * @author Lalo
 */
public class Mensaje implements Serializable {

    /**
     * Contexto usado para el envío de mensajes.
     */
    FacesContext facesContext;
    /**
     * Mensaje a enviar.
     */
    FacesMessage mensaje;

    /**
     * Constructor de clase que inicializa sus atributos a un valor or default.
     */
    public Mensaje() {
        facesContext = FacesContext.getCurrentInstance();
        this.mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, getClass().getName(), getClass().toString());
    }

    /**
     * Contructor de clase que recibe un contexto al cual enviar el mensaje.
     *
     * @param facesContext Conexto de mensajes.
     */
    public Mensaje(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    /**
     * Envía mensaje con prioridad de información.
     *
     * @param titulo Título del mensaje.
     * @param mensaje Contenido del mensaje.
     */
    public void enviaMensaje(String titulo, String mensaje) {
        this.mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje);
        facesContext.addMessage(null, this.mensaje);
    }

    /**
     * Envía mensaje con prioridad de alerta.
     *
     * @param titulo Título del mensaje.
     * @param mensaje Contenido del mensaje.
     */
    public void enviaAlerta(String titulo, String mensaje) {
        this.mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensaje);
        facesContext.addMessage(null, this.mensaje);
    }

    /**
     * Envía mensaje con prioridad de error.
     *
     * @param titulo Título del mensaje.
     * @param mensaje Contenido del mensaje.
     */
    public void enviaError(String titulo, String mensaje) {
        this.mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensaje);
        facesContext.addMessage(null, this.mensaje);
    }

    /**
     * Envía mensaje con prioridad de error fatal.
     *
     * @param titulo Título del mensaje.
     * @param mensaje Contenido del mensaje.
     */
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
