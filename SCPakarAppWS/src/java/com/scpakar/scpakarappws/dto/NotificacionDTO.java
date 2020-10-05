package com.scpakar.scpakarappws.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pablo.martinez
 */
public class NotificacionDTO implements Serializable {

    private String idContacto;
    private String idSocio;
    private String titulo;
    private String notificacion;
    private String fecha;
    private boolean leido;
    private boolean oculto;
    private int idTipoNotificacion;

    public NotificacionDTO() {
        this.idContacto = "";
        this.idSocio = "";
        this.titulo = "";
        this.notificacion = "";
        this.fecha = "";
    }

    public int getIdTipoNotificacion() {
        return idTipoNotificacion;
    }

    public void setIdTipoNotificacion(int idTipoNotificacion) {
        this.idTipoNotificacion = idTipoNotificacion;
    }

    public String getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(String idContacto) {
        this.idContacto = idContacto;
    }

    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public boolean isOculto() {
        return oculto;
    }

    public void setOculto(boolean oculto) {
        this.oculto = oculto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.idContacto);
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
        final NotificacionDTO other = (NotificacionDTO) obj;
        if (!Objects.equals(this.idContacto, other.idContacto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NotificacionDTO{" + "idContacto=" + idContacto + ", idSocio=" + idSocio + ", titulo=" + titulo + ", notificacion=" + notificacion + ", fecha=" + fecha + ", leido=" + leido + ", oculto=" + oculto + '}';
    }

}
