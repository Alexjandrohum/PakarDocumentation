package com.scpakar.scpakarappws.dto;

import java.io.Serializable;

/**
 *
 * @author pablo.martinez
 */
public class CatalogoDTO implements Serializable {
    private int idCatalogo;
    private String nombre;
    private String url;
    private String temporada;
    private int paginas;
    private String pdfUrl;
    private String color;
    private boolean premium;
    private boolean pdfPrecios;
    private int numeroArticulos;
    
    public CatalogoDTO() {
        this.nombre = "";
        this.url = "";
        this.temporada = "";
        this.pdfUrl = "";
        this.color = "";
    }

    public boolean isPdfPrecios() {
        return pdfPrecios;
    }

    public void setPdfPrecios(boolean pdfPrecios) {
        this.pdfPrecios = pdfPrecios;
    }
    
    public CatalogoDTO(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroArticulos() {
        return numeroArticulos;
    }

    public void setNumeroArticulos(int numeroArticulos) {
        this.numeroArticulos = numeroArticulos;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.idCatalogo;
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
        final CatalogoDTO other = (CatalogoDTO) obj;
        if (this.idCatalogo != other.idCatalogo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CatalogoDTO{" + "idCatalogo=" + idCatalogo + ", nombre=" + nombre + ", url=" + url + ", temporada=" + temporada + ", paginas=" + paginas + '}';
    }

}
