package com.grupopakar.grupopakarappws.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.DateFormat;

/**
 *
 * @author carlos.juarez
 */
public class GsonUtil {

    public static Gson gson = null;
    private static final GsonBuilder gb = new GsonBuilder();

    static {
        gb.setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT);
        gson = gb.create();
    }

}
