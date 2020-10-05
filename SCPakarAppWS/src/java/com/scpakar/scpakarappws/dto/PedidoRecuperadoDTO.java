package com.scpakar.scpakarappws.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author pablo.martinez
 */
public class PedidoRecuperadoDTO implements Serializable {

    private String idPedido;
    private Date fechaPedido;

    public PedidoRecuperadoDTO() {
        this.idPedido = "";
        this.fechaPedido = new Date();
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idPedido);
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
        final PedidoRecuperadoDTO other = (PedidoRecuperadoDTO) obj;
        if (!Objects.equals(this.idPedido, other.idPedido)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PedidoRecuperadoDTO{" + "idPedido=" + idPedido + ", fechaPedido=" + fechaPedido + '}';
    }

}
