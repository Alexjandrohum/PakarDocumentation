package com.scpakar.scpakarappws.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonio.ruiz on 2019-08-05
 */
public class ListaCategoriaDTO implements Serializable {
    private int idCategoria;
    private int idLista;
    private String nombre;
    private String url;
    private List<ListaCategoriaDTO> hijos;

    public ListaCategoriaDTO() {
        this.idCategoria = 0;
        this.idLista = 0;
        this.nombre = "";
        this.url = "";
        this.hijos = new ArrayList<>();
    }

    public List<ListaCategoriaDTO> getHijos() {
        return hijos;
    }

    public void setHijos(List<ListaCategoriaDTO> hijos) {
        this.hijos = hijos;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
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

    @Override
    public String toString() {
        return "ListaCategoriaDTO{" +
                "idCategoria=" + idCategoria +
                ", idLista=" + idLista +
                ", nombre='" + nombre + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
