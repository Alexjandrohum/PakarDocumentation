package com.scpakar.scpakarweb.dto;

import java.io.Serializable;

/**
 *
 * @author pablo.martinez
 */
public class DatosPreguntasDTO implements Serializable {

    private double costoJuego;
    private double costoUnitario;
    private int devCalzado;
    private int devAccesorios;
    private int noCatalogos;
    private double cdwAcceso;
    private double cdwAfiliacion;
    private double afiliacionMin;
    private String listaCatalogos;

    public DatosPreguntasDTO() {
        this.listaCatalogos = "";
    }

    public double getCostoJuego() {
        return costoJuego;
    }

    public void setCostoJuego(double costoJuego) {
        this.costoJuego = costoJuego;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public int getDevCalzado() {
        return devCalzado;
    }

    public void setDevCalzado(int devCalzado) {
        this.devCalzado = devCalzado;
    }

    public int getDevAccesorios() {
        return devAccesorios;
    }

    public void setDevAccesorios(int devAccesorios) {
        this.devAccesorios = devAccesorios;
    }

    public int getNoCatalogos() {
        return noCatalogos;
    }

    public void setNoCatalogos(int noCatalogos) {
        this.noCatalogos = noCatalogos;
    }

    public double getCdwAcceso() {
        return cdwAcceso;
    }

    public void setCdwAcceso(double cdwAcceso) {
        this.cdwAcceso = cdwAcceso;
    }

    public double getCdwAfiliacion() {
        return cdwAfiliacion;
    }

    public void setCdwAfiliacion(double cdwAfiliacion) {
        this.cdwAfiliacion = cdwAfiliacion;
    }

    public double getAfiliacionMin() {
        return afiliacionMin;
    }

    public void setAfiliacionMin(double afiliacionMin) {
        this.afiliacionMin = afiliacionMin;
    }

    public String getListaCatalogos() {
        return listaCatalogos;
    }

    public void setListaCatalogos(String listaCatalogos) {
        this.listaCatalogos = listaCatalogos;
    }

}
