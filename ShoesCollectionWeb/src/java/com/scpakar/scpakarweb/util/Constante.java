package com.scpakar.scpakarweb.util;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author pablo.martinez
 */
public class Constante {

    /**
     * Código para verificar permisos.
     */
    public static final int PERMISSION_CODE = 1234;

    /**
     * IP de servidor.
     */
    //public static final String BASE_URL = "http://scpakarmx.com/SCPakarAppWS/ws/";
    public static final String BASE_URL = "http://194.168.1.71/SCPakarAppWS/ws/";

    /**
     * Tiempo de espera de respuesta Htpp en milisegundos.
     */
    public static final int TIME_OUT_CONNECT_HTTP = 15000;

    /**
     * Tiempo de espera de respuesta Htpp en milisegundos.
     */
    public static final int TIME_OUT_READ_HTTP = 30000;

    /**
     * Método GET para consultas al Web Service.
     */
    public static final String REQUEST_METHOD_GET = "GET";

    /**
     * Constante de inicio en Google Sign In.
     */
    public static final int RC_SIGN_IN = 1001;

    /**
     * Constante registro origen.
     */
    public static final int ORIGEN_ANDROID = 1;

    /**
     * KEYS de datos para agregarProducto pedidos.
     */
    public static final String KEY_TALLA = "talla";
    public static final String KEY_ID_CLIENTE = "idCliente";
    public static final String KEY_ALMACEN = "almacen";
    public static final String KEY_LLAVE = "llave";
    public static final String KEY_CODIGO = "codigo";
    public static final String KEY_CANTIDAD = "cantidad";

    /**
     * KEYS de pedido.
     */
    public static final String KEY_ID_PEDIDO = "idPedido";
    public static final String KEY_ULTIMO_ID_CLIENTE = "ultimoIdCliente";
    public static final String KEY_ULTIMO_NAME_CLIENTE = "ultimoNameCliente";
    public static final String KEY_ULTIMO_PORCENTAJE_CLIENTE = "ultimoPorcentajeCliente";

    /**
     * Constante de tiempo.
     */
    public static final long TIEMPO_PERIOD = TimeUnit.SECONDS.toMillis(1);
    public static final long TIEMPO_PEDIDO = TimeUnit.MINUTES.toMillis(15);

    /**
     * Extensión archivo .pdf
     */
    public static String EXTENSION_PDF = ".pdf";
}
