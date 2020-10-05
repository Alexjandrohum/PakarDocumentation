package com.scpakar.scpakarweb.dto;

import java.io.Serializable;

/**
 *
 * @author antonio.ruiz
 */
public class ErrorDTO implements Serializable {

    private boolean errorSerie;
    private boolean errorCodigo;
    private boolean errorSocio;
    private boolean errorBloqueoSocio;
    private boolean errorActivado;
    private boolean errorTemporada;
    private boolean errorIdCliente;
    private boolean exito;
    private boolean reEmail;
    private String nuevoCliente;
    private boolean existePassword;

    public ErrorDTO() {
        this.nuevoCliente = "";
    }

    public boolean isExistePassword() {
        return existePassword;
    }

    public void setExistePassword(boolean existePassword) {
        this.existePassword = existePassword;
    }

    public String getNuevoCliente() {
        return nuevoCliente;
    }

    public void setNuevoCliente(String nuevoCliente) {
        this.nuevoCliente = nuevoCliente;
    }

    public boolean isReEmail() {
        return reEmail;
    }

    public void setReEmail(boolean reEmail) {
        this.reEmail = reEmail;
    }

    public boolean isErrorIdCliente() {
        return errorIdCliente;
    }

    public void setErrorIdCliente(boolean errorIdCliente) {
        this.errorIdCliente = errorIdCliente;
    }

    public boolean isErrorTemporada() {
        return errorTemporada;
    }

    public void setErrorTemporada(boolean errorTemporada) {
        this.errorTemporada = errorTemporada;
    }

    public boolean isErrorSerie() {
        return errorSerie;
    }

    public void setErrorSerie(boolean errorSerie) {
        this.errorSerie = errorSerie;
    }

    public boolean isErrorCodigo() {
        return errorCodigo;
    }

    public void setErrorCodigo(boolean errorCodigo) {
        this.errorCodigo = errorCodigo;
    }

    public boolean isErrorSocio() {
        return errorSocio;
    }

    public void setErrorSocio(boolean errorSocio) {
        this.errorSocio = errorSocio;
    }

    public boolean isErrorActivado() {
        return errorActivado;
    }

    public void setErrorActivado(boolean errorActivado) {
        this.errorActivado = errorActivado;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public boolean isErrorBloqueoSocio() {
        return errorBloqueoSocio;
    }

    public void setErrorBloqueoSocio(boolean errorBloqueoSocio) {
        this.errorBloqueoSocio = errorBloqueoSocio;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" + "errorSerie=" + errorSerie + ", errorCodigo=" + errorCodigo + ", errorSocio=" + errorSocio + ", errorBloqueoSocio=" + errorBloqueoSocio + ", errorActivado=" + errorActivado + ", errorTemporada=" + errorTemporada + ", errorIdCliente=" + errorIdCliente + ", exito=" + exito + ", reEmail=" + reEmail + ", nuevoCliente=" + nuevoCliente + '}';
    }

}
