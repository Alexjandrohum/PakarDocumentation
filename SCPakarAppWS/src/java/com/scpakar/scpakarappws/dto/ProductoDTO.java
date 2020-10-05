package com.scpakar.scpakarappws.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author pablo.martinez
 */
public class ProductoDTO implements Serializable {

    private int idCatalogo;
    private String idSeccion;
    private String codigoVisible;
    private String codigo;
    private String llave;
    private String talla;
    private String marca;
    private String nombreMarca;
    private String modelo;
    private String nombreColor;
    private String color;
    private String hexColor;
    private String corridaVisible;
    private String corrida;
    private String material;
    private double precio;
    private int pagina;
    private String url;
    private String catalogo;
    private String departamento;
    private String tacon;
    private double descuento;
    private boolean oferta;
    private double precioOferta;
    private String dimensiones;
    private String promocionTexto;
    private List<TallaDTO> tallasDisponibles;
    private String descuentoDoble;
    private String descuentoTexto;

    public ProductoDTO() {
        this.idCatalogo = 0;
        this.idSeccion = "";
        this.codigoVisible = "";
        this.codigo = "";
        this.llave = "";
        this.talla = "";
        this.marca = "";
        this.nombreMarca = "";
        this.modelo = "";
        this.nombreColor = "";
        this.color = "";
        this.hexColor = "";
        this.corridaVisible = "";
        this.corrida = "";
        this.material = "";
        this.precio = 0;
        this.pagina = 0;
        this.url = "";
        this.catalogo = "";
        this.departamento = "";
        this.tacon = "";
        this.dimensiones = "";
        this.promocionTexto = "";
        this.descuento = 0;
        this.oferta = false;
        this.precioOferta = 0;
        this.descuentoDoble = "";
        this.descuentoTexto = "";
        this.tallasDisponibles = new ArrayList<>();
    }

    public String getDescuentoTexto() {
        return descuentoTexto;
    }

    public void setDescuentoTexto(String descuentoTexto) {
        this.descuentoTexto = descuentoTexto;
    }

    public String getDescuentoDoble() {
        return descuentoDoble;
    }

    public void setDescuentoDoble(String descuentoDoble) {
        this.descuentoDoble = descuentoDoble;
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public String getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(String idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getPromocionTexto() {
        return promocionTexto;
    }

    public void setPromocionTexto(String promocionTexto) {
        this.promocionTexto = promocionTexto;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public List<TallaDTO> getTallasDisponibles() {
        return tallasDisponibles;
    }

    public void setTallasDisponibles(List<TallaDTO> tallasDisponibles) {
        this.tallasDisponibles = tallasDisponibles;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public boolean isOferta() {
        return oferta;
    }

    public void setOferta(boolean oferta) {
        this.oferta = oferta;
    }

    public double getPrecioOferta() {
        return precioOferta;
    }

    public void setPrecioOferta(double precioOferta) {
        this.precioOferta = precioOferta;
    }

    public String getTacon() {
        return tacon;
    }

    public void setTacon(String tacon) {
        this.tacon = tacon;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public String getMaterial() {
        return material;
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
        return "ProductoDTO{" + "codigoVisible=" + codigoVisible + ", codigo=" + codigo + ", llave=" + llave + ", talla=" + talla + ", marca=" + marca + ", nombreMarca=" + nombreMarca + ", modelo=" + modelo + ", color=" + color + ", corrida=" + corrida + ", precio=" + precio + ", pagina=" + pagina + ", url=" + url + ", catalogo=" + catalogo + '}';
    }

}
