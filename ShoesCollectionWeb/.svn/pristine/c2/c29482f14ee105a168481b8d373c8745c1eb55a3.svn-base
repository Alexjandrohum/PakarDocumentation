package com.scpakar.scpakarweb.dto;

import java.io.Serializable;

/**
 *
 * @author pablo.martinez
 */
public enum Estatus implements Serializable {

    NUEVO(1, "Nuevo", "#333333", false),
    PEDIDO(2, "Pedido", "#333333", false),
    SURTIDO(3, "Surtido", "#3366FF", false),
    ENVIADO(4, "Enviado", "#3366FF", false),
    RECIBIDO(5, "Recibido", "#006600", true),
    REVISADO(6, "Revisado", "#006600", true),
    VENDIDO(7, "Vendido", "#006600", true),
    DOMICILIO(8, "Domicilio", "#006600", false),
    ABANDONADO(9, "Abandonado", "#800000", true),
    NEGADO_INVENTARIO(10, "Negado inventario", "#800000", false),
    NEGADO_OPERACION(11, "Negado operación", "#800000", false),
    CANCELADO(12, "Cancelado", "#FF0000", false),
    CANCELADO_OPERACION(13, "Cancelado Operación", "#800000", false),
    OPCION(14, "Opcion", "#E8B500", false); //valor para SurtidoPEM

    private final int idEstatus;
    private final String nombre;
    private final String color;
    private final boolean paqueteria;

    private Estatus(int idEstatus, String nombre, String color, boolean paqueteria) {
        this.idEstatus = idEstatus;
        this.nombre = nombre;
        this.color = color;
        this.paqueteria = paqueteria;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public String getColor() {
        return color;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isPaqueteria() {
        return paqueteria;
    }

    public static Estatus getEstatus(int idEstatus) {
        for (Estatus e : Estatus.values()) {
            if (e.getIdEstatus() == idEstatus) {
                return e;
            }
        }
        return NUEVO;
    }
}
