package com.scpakar.scpakarweb.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pablo.martinez
 */
public class TallaDTO implements Serializable {

    private String talla;
    private String tallaVisible;
    private String llave;
    private double precio;

    public TallaDTO() {
        this.talla = "";
        this.tallaVisible = "";
        this.llave = "";
        this.precio = 0;
    }

    public TallaDTO(String talla, String tallaVisible) {
        this.talla = talla;
        this.tallaVisible = tallaVisible;
        this.llave = "";
        this.precio = 0;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getTallaVisible() {
        return tallaVisible;
    }

    public void setTallaVisible(String tallaVisible) {
        this.tallaVisible = tallaVisible;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.talla);
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
        final TallaDTO other = (TallaDTO) obj;
        if (!Objects.equals(this.talla, other.talla)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TallaDTO{" + "talla=" + talla + ", tallaVisible=" + tallaVisible + ", llave=" + llave + ", precio=" + precio + '}';
    }

}
