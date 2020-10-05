package com.scpakar.scpakarweb.util;

import static java.io.File.separator;

/**
 * Clase que contiene los parámetros de configuración de la aplicación como:
 * Identificador de la aplicación, identificador del pool de conexiones JNDI
 * declarado en el Context de tomcat, Identificador de la página de inicio por
 * default de la aplicación.
 *
 * @author alberto.quirino
 */
public class Configuracion {

    /**
     * Identicicador de la aplicación
     */
    private static final String ID = "scweb";

    /**
     * Obtiene el identificador de la aplicación.
     *
     * @return Cadena de texto que contiene el identificador de la aplicación.
     */
    public static String getId() {
        return ID;
    }

    /**
     * Obtiene el Identificador del pool de conexiones.
     *
     * @return Cadena de texto con el valor JNDI del pool de conexiones tomcat.
     */
    public static String getJndi() {
        return "jdbc/" + ID;
    }

    /**
     * Obtiene la página de inicio por default.
     *
     * @return Cadena de texto con la página de inicio por default.
     */
    public static String getInicio() {
        return ID + "_inicio.jsf";
    }

    /**
     * Obtiene la ruta de imagen predeterminada de la aplicación.
     *
     * @return Cadena de texto con la ruta de imagen de la aplicación.
     */
    public static String getImagen() {
        return separator + "ROOT" + separator + ID + ".jpg";
    }

}
