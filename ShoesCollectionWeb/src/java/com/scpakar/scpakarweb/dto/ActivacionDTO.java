package com.scpakar.scpakarweb.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pablo.martinez
 */
public class ActivacionDTO implements Serializable {

    private String idSocio;
    private String correo;
    private String serie;
    private String codigo;
    private String password;
    private String passwordConfirma;
    private int origen;
    private boolean sobreEscribe;

    public ActivacionDTO() {
        this.idSocio = "";
        this.correo = "";
        this.serie = "";
        this.codigo = "";
        this.password = "";
        this.passwordConfirma = "";
        this.origen = AppOrigen.WEB.getIdOrigen();
        this.sobreEscribe = false;
    }

    public boolean isPasswordIgual() {
        return password.equals(passwordConfirma);
    }

    public boolean isValido() {
        if (idSocio.isEmpty()) {
            return false;
        }
        if (correo.isEmpty()) {
            return false;
        }
        if (serie.isEmpty()) {
            return false;
        }
        if (codigo.isEmpty()) {
            return false;
        }
        if (!isPasswordIgual()) {
            return false;
        }
        return true;
    }

    public boolean isSobreEscribe() {
        return sobreEscribe;
    }

    public void setSobreEscribe(boolean sobreEscribe) {
        this.sobreEscribe = sobreEscribe;
    }

    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public String getPasswordConfirma() {
        return passwordConfirma;
    }

    public void setPasswordConfirma(String passwordConfirma) {
        this.passwordConfirma = passwordConfirma;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.idSocio);
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
        final ActivacionDTO other = (ActivacionDTO) obj;
        if (!Objects.equals(this.idSocio, other.idSocio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ActivacionDTO{" + "idSocio=" + idSocio + ", correo=" + correo + ", serie=" + serie + ", codigo=" + codigo + ", password=" + password + ", passwordConfirma=" + passwordConfirma + ", origen=" + origen + ", sobreEscribe=" + sobreEscribe + '}';
    }

}
