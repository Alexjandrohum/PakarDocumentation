package com.scpakar.scpakarappws.dto;

import java.io.Serializable;

/**
 *
 * @author pablo.martinez
 */
public enum CanalPedido implements Serializable {
    CANAL0(0, "SC Pakar APP", "canal_0.png"),
    CANAL1(1, "Catálogo digital WEB", "canal_1.png"),
    CANAL2(2, "Teléfono casa", "canal_2.png"),
    CANAL3(3, "Celular", "canal_3.png"),
    CANAL4(4, "WhatsApp", "canal_4.png"),
    CANAL5(5, "eMail", "canal_5.png"),
    CANAL6(6, "Fax", "canal_6.png"),
    CANAL7(7, "Tel Oficina", "canal_7.png"),
    CANAL8(8, "Facebook", "canal_8.png"),
    CANAL9(9, "Sms", "canal_9.png"),
    CANAL10(10, "Pakar Shoes", "canal_10.png"),
    CANAL20(20, "Centro de Atención", "canal_20.png"),
    CANAL21(21, "Mostrador", "canal_21.png"),
    CANAL22(22, "Tablet", "canal_22.png"),
    CANAL23(23, "Telegram", "canal_23.png"),
    CANAL24(24, "Sin Confirmación", "canal_24.png"),
    CANAL25(25, "Paquetería", "canal_25.png");

    private final int idCanal;
    private final String nombre;
    private final String nombreImagen;

    private CanalPedido(int idCanal, String nombre, String nombreImagen) {
        this.idCanal = idCanal;
        this.nombre = nombre;
        this.nombreImagen = nombreImagen;
    }

    public int getIdCanal() {
        return idCanal;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public static CanalPedido getCanal(int idCanal) {
        CanalPedido cn = null;
        for (CanalPedido c : CanalPedido.values()) {
            if (c.getIdCanal() == idCanal) {
                cn = c;
                break;
            }
        }
        return cn;
    }

    public static CanalPedido getCanal(String nombreCanal) {
        CanalPedido cn = null;
        for (CanalPedido c : CanalPedido.values()) {
            if (c.getNombre().equals(nombreCanal)) {
                cn = c;
                break;
            }
        }
        return cn;
    }
}
