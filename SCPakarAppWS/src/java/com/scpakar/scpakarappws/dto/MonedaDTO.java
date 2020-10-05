package com.scpakar.scpakarappws.dto;

import java.io.Serializable;

/**
 *
 * @author pablo.martinez
 */
public class MonedaDTO implements Serializable {

    private String abreviatura;
    private String nombre;

    public MonedaDTO() {
        this.abreviatura = "";
        this.nombre = "";
    }

    public MonedaDTO(String abreviatura, String nombre) {
        this.abreviatura = abreviatura;
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.abreviatura != null ? this.abreviatura.hashCode() : 0);
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
        final MonedaDTO other = (MonedaDTO) obj;
        if ((this.abreviatura == null) ? (other.abreviatura != null) : !this.abreviatura.equals(other.abreviatura)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MonedaDTO{" + "abreviatura=" + abreviatura + ", nombre=" + nombre + '}';
    }

}
