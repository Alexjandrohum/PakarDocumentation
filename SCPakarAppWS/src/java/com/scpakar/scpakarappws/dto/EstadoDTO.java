package com.scpakar.scpakarappws.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author antonio.ruiz
 */
public class EstadoDTO implements Serializable {
    private String nombreEstado;
    private List<SucursalDTO> sucursales;

    public EstadoDTO() {
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
   
}
