package com.scpakar.scpakarappws.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
public class Formato {

    /**
     *
     * Convierte un numero en texto con formato de moneda
     *
     * @param numero Numero a convertir
     * @return Texto formateado
     */
    public static String formatoNumero(double numero) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format(numero);
    }

    public static String formatoMoneda(double numero) {
        DecimalFormat decimalFormat = new DecimalFormat("¤ #.00");
        return decimalFormat.format(numero);
    }

    public static String formatoNumeroVenta(double numero) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumFractionDigits(0);
        numberFormat.setMaximumFractionDigits(0);
        return numberFormat.format(numero);
    }

    public static String formatoPorcentaje(double numero) {
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format(numero);
    }

    public static String formatoFechaDiagonal(String timeStamp) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        String fecha = "";
        try {
            date = form.parse(timeStamp);
            SimpleDateFormat postFormater = new SimpleDateFormat("MM/dd/yyyy");
            fecha = postFormater.format(date).toUpperCase();
        } catch (ParseException ex) {
            Util.registraError(ex);
            return fecha;
        }
        return fecha;
    }
}
