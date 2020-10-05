package com.grupopakar.grupopakarappws.util;

import static java.io.File.separator;

/**
 *
 * @author antonio.ruiz
 */
public class Configuracion {

    private static final String ID = "aps";

    public static String getId() {
        return ID;
    }

    public static String getJndi() {
        return "jdbc/" + ID;
    }

    public static String getImagen() {
        return separator + "ROOT" + separator + ID + ".jpg";
    }

}
