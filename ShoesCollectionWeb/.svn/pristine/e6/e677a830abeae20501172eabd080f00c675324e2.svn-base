package com.scpakar.scpakarweb.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pablo.martinez
 */
public class BannerDTO implements Serializable {

    private String urlImagen;

    public BannerDTO() {
        this.urlImagen = "";
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.urlImagen);
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
        final BannerDTO other = (BannerDTO) obj;
        if (!Objects.equals(this.urlImagen, other.urlImagen)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return urlImagen;
    }

}
