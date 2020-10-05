package com.scpakar.scpakarweb.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pablo.martinez
 */
public class CorridaDTO implements Serializable {

    private String llave;
    private String corrida;
    private double precio;

    public CorridaDTO() {
        this.llave = "";
        this.corrida = "";
        this.precio = 0d;
    }

    public CorridaDTO(String llave, String corrida, double precio) {
        this.llave = llave;
        this.corrida = corrida;
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getCorrida() {
        return corrida;
    }

    public void setCorrida(String corrida) {
        this.corrida = corrida;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.llave);
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
        final CorridaDTO other = (CorridaDTO) obj;
        if (!Objects.equals(this.llave, other.llave)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CorridaDTO{" + "llave=" + llave + ", corrida=" + corrida + ", precio=" + precio + '}';
    }

}
