package com.scpakar.scpakarweb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author pablo.martinez
 */
public class EstadoDTO implements Serializable {

    private String nombreEstado;
    private List<SucursalDTO> sucursales;

    public EstadoDTO() {
        this.nombreEstado = "";
        sucursales = new ArrayList<>();
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public List<SucursalDTO> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<SucursalDTO> sucursales) {
        this.sucursales = sucursales;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nombreEstado);
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
        final EstadoDTO other = (EstadoDTO) obj;
        if (!Objects.equals(this.nombreEstado, other.nombreEstado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EstadoDTO{" + "nombreEstado=" + nombreEstado + ", sucursales=" + sucursales + '}';
    }

}
