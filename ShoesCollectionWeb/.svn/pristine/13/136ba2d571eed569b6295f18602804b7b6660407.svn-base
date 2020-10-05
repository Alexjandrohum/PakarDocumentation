package com.scpakar.scpakarweb.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pablo.martinez
 */
public class ProductoDTO implements Serializable {

    private String codigoVisible;
    private String codigo;
    private String llave;
    private String talla;
    private String marca;
    private String nombreMarca;
    private String modelo;
    private String nombreColor;
    private String color;
    private String corridaVisible;
    private String corrida;
    private String material;
    private double precio;
    private int pagina;
    private String url;
    private String catalogo;
    private int cantidad;

    public ProductoDTO() {
        this.codigoVisible = "";
        this.codigo = "";
        this.llave = "";
        this.talla = "";
        this.marca = "";
        this.nombreMarca = "";
        this.modelo = "";
        this.color = "";
        this.nombreColor = "";
        this.url = "";
        this.corrida = "";
        this.corridaVisible = "";
        this.material = "";
        this.url = "";
        this.catalogo = "";
        this.cantidad = 1;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMaterial() {
        return (material == null || material.equals("0")) ? "" : material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCodigoVisible() {
        return codigoVisible;
    }

    public void setCodigoVisible(String codigoVisible) {
        this.codigoVisible = codigoVisible;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return marca + " " + modelo + " " + color;
    }

    public String getDescripcionCompleta() {
        return marca + " " + modelo + " " + color + " - " + talla;
    }

    public String getDescripcionNombreMarca() {
        return nombreMarca + " " + modelo + " " + nombreColor;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getCorrida() {
        return corrida;
    }

    public void setCorrida(String corrida) {
        this.corrida = corrida;
    }

    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }

    public String getNombreColor() {
        return nombreColor;
    }

    public void setNombreColor(String nombreColor) {
        this.nombreColor = nombreColor;
    }

    public String getCorridaVisible() {
        return corridaVisible;
    }

    public void setCorridaVisible(String corridaVisible) {
        this.corridaVisible = corridaVisible;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.llave);
        hash = 97 * hash + Objects.hashCode(this.talla);
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
        final ProductoDTO other = (ProductoDTO) obj;
        if (!Objects.equals(this.llave, other.llave)) {
            return false;
        }
        if (!Objects.equals(this.talla, other.talla)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" + "codigoVisible=" + codigoVisible + ", codigo=" + codigo + ", llave=" + llave + ", talla=" + talla + ", marca=" + marca + ", nombreMarca=" + nombreMarca + ", modelo=" + modelo + ", nombreColor=" + nombreColor + ", color=" + color + ", corridaVisible=" + corridaVisible + ", corrida=" + corrida + ", material=" + material + ", precio=" + precio + ", pagina=" + pagina + ", url=" + url + ", catalogo=" + catalogo + ", cantidad=" + cantidad + '}';
    }

}
