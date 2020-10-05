package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.PreguntaDAO;
import com.scpakar.scpakarappws.dao.PropiedadDAO;
import com.scpakar.scpakarappws.dto.DatosPreguntasDTO;
import com.scpakar.scpakarappws.dto.PropiedadCDW;
import java.sql.Connection;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
public class PreguntaDelegate {

    private final PreguntaDAO preguntaDAO;
    private final PropiedadDAO propiedadDAO;

    public PreguntaDelegate() {
        this.propiedadDAO = new PropiedadDAO();
        this.preguntaDAO = new PreguntaDAO();
    }

    public DatosPreguntasDTO getDatosPreguntas(Connection con) {
        DatosPreguntasDTO datos = new DatosPreguntasDTO();
        datos.setCostoJuego(getCostoJuego(con));
        datos.setDevCalzado(getDevCalzado(con));
        datos.setDevAccesorios(getDevAccesorios(con));
        datos.setCdwAcceso(getCdwAcceso(con));
        datos.setCdwAfiliacion(getCdwAfiliacion(con));
        datos.setAfiliacionMin(getAfiliacionMin(con));
        datos.setListaCatalogos(getListaCatalogos(con));
        datos.setNoCatalogos(getNoCatalogos(con));
        datos.setCostoUnitario(getCostoUnitario(con));

        return datos;
    }

    private double getCostoJuego(Connection con) {
        double valor = 0;
        try {
            valor = Double.parseDouble(propiedadDAO.selectPropiedad(PropiedadCDW.COSTO_JUEGO.getIdPropiedad(), con));
        } catch (NumberFormatException ex) {
            Util.registraError(ex);
        }
        return valor;
    }

    private int getNoCatalogos(Connection con) {
        int valor = 0;
        try {
            valor = Integer.parseInt(propiedadDAO.selectPropiedad(PropiedadCDW.NO_CATALOGOS.getIdPropiedad(), con));
        } catch (NumberFormatException ex) {
            Util.registraError(ex);
        }
        return valor;
    }

    private int getDevCalzado(Connection con) {
        int valor = 0;
        try {
            valor = Integer.parseInt(propiedadDAO.selectPropiedad(PropiedadCDW.DEV_CALZADO.getIdPropiedad(), con));
        } catch (NumberFormatException ex) {
            Util.registraError(ex);
        }
        return valor;
    }

    private int getDevAccesorios(Connection con) {
        int valor = 0;
        try {
            valor = Integer.parseInt(propiedadDAO.selectPropiedad(PropiedadCDW.DEV_ACCESORIOS.getIdPropiedad(), con));
        } catch (NumberFormatException ex) {
            Util.registraError(ex);
        }
        return valor;
    }

    private double getCdwAcceso(Connection con) {
        double valor = 0;
        try {
            valor = Double.parseDouble(propiedadDAO.selectPropiedad(PropiedadCDW.CDW_ACCESO.getIdPropiedad(), con));
        } catch (NumberFormatException ex) {
            Util.registraError(ex);
        }
        return valor;
    }

    private double getCdwAfiliacion(Connection con) {
        double valor = 0;
        try {
            valor = Double.parseDouble(propiedadDAO.selectPropiedad(PropiedadCDW.CDW_AFILIACION.getIdPropiedad(), con));
        } catch (NumberFormatException ex) {
            Util.registraError(ex);
        }
        return valor;
    }

    private double getAfiliacionMin(Connection con) {
        double valor = 0;
        try {
            valor = Double.parseDouble(propiedadDAO.selectPropiedad(PropiedadCDW.AFILIACION_MIN.getIdPropiedad(), con));
        } catch (NumberFormatException ex) {
            Util.registraError(ex);
        }
        return valor;
    }

    private double getCostoUnitario(Connection con) {
        double valor = 0;
        try {
            valor = Double.parseDouble(propiedadDAO.selectPropiedad(PropiedadCDW.COSTO_UNITARIO.getIdPropiedad(), con));
        } catch (NumberFormatException ex) {
            Util.registraError(ex);
        }
        return valor;
    }

    private String getTemporada(Connection con) {
        return propiedadDAO.selectPropiedad(PropiedadCDW.TEMPORADA.getIdPropiedad(), con);
    }

    private String getListaCatalogos(Connection con) {
        return preguntaDAO.getListaCatalogos(getTemporada(con), con);
    }
}
