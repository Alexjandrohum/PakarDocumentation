package com.scpakar.scpakarappws.dto;

import java.io.Serializable;

/**
 *
 * @author antonio.ruiz
 */
public class PaqueteriaDTO implements Serializable {

    private String destinatario;
    private String telefono;
    private String idAlmacen;
    private String colonia;
    private String ciudad;
    private String estado;
    private String calle;
    private String numero;
    private String cp;
    private String referencia;
    private int diasHabiles;
    private boolean ocurre;
    private boolean reexpedicion;

    public PaqueteriaDTO() {
        this.destinatario = "";
        this.telefono = "";
        this.idAlmacen = "";
        this.colonia = "";
        this.ciudad = "";
        this.estado = "";
        this.calle = "";
        this.numero = "";
        this.cp = "";
        this.referencia = "";
    }

    public String getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(String idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getReferencia() {
        return "|Ref:" + referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return calle + '|' + numero + '|' + colonia + '|' + cp + '|' + ciudad + '|' + estado;
    }

    public int getDiasHabiles() {
        return diasHabiles;
    }

    public void setDiasHabiles(int diasHabiles) {
        this.diasHabiles = diasHabiles;
    }

    public boolean isOcurre() {
        return ocurre;
    }

    public void setOcurre(boolean ocurre) {
        this.ocurre = ocurre;
    }

    public boolean isReexpedicion() {
        return reexpedicion;
    }

    public void setReexpedicion(boolean reexpedicion) {
        this.reexpedicion = reexpedicion;
    }
}
