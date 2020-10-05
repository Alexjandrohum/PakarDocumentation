package com.scpakar.scpakarweb.dto;

import java.io.Serializable;

/**
 *
 * @author pablo.martinez
 */
public class DestacadoDTO implements Serializable {

    private String urlImagen;
    private ProductoDTO producto;

    public DestacadoDTO() {
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
