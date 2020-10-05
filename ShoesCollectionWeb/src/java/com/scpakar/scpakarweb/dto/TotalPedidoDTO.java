package com.scpakar.scpakarweb.dto;

import java.io.Serializable;

/**
 *
 * @author pablo.martinez
 */
public class TotalPedidoDTO implements Serializable {

    private int totalPares;
    private double totalImporte;
    private int totalTiempo;
    private double totalCliente;

    public TotalPedidoDTO() {
        this.totalPares = 0;
        this.totalImporte = 0d;
        this.totalTiempo = 0;
        this.totalCliente = 0d;
    }

    public TotalPedidoDTO(int totalPares, double totalImporte, int totalTiempo, double totalCliente) {
        this.totalPares = totalPares;
        this.totalImporte = totalImporte;
        this.totalTiempo = totalTiempo;
        this.totalCliente = totalCliente;
    }

    public double getTotalGanancia() {
        return (totalCliente > totalImporte) ? totalCliente - totalImporte : 0d;
    }

    public int getTotalPares() {
        return totalPares;
    }

    public void setTotalPares(int totalPares) {
        this.totalPares = totalPares;
    }

    public double getTotalImporte() {
        return totalImporte;
    }

    public void setTotalImporte(double totalImporte) {
        this.totalImporte = totalImporte;
    }

    public int getTotalTiempo() {
        return totalTiempo;
    }

    public void setTotalTiempo(int totalTiempo) {
        this.totalTiempo = totalTiempo;
    }

    public double getTotalCliente() {
        return totalCliente;
    }

    public void setTotalCliente(double totalCliente) {
        this.totalCliente = totalCliente;
    }

    @Override
    public String toString() {
        return "TotalPedidoDTO{" + "totalPares=" + totalPares + ", totalImporte=" + totalImporte + ", totalTiempo=" + totalTiempo + ", totalCliente=" + totalCliente + '}';
    }

}
