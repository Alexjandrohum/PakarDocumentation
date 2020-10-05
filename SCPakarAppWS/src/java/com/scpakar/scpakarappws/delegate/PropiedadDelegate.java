package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.PropiedadDAO;
import java.sql.Connection;

/**
 *
 * @author pablo.martinez
 */
public class PropiedadDelegate {

    private final PropiedadDAO propiedadDao;

    public PropiedadDelegate() {
        this.propiedadDao = new PropiedadDAO();
    }

    public String selectPropiedad(String clavePropiedad, Connection con) {
        return propiedadDao.selectPropiedad(clavePropiedad, con);
    }

    public boolean updatePropiedad(String clave, String valor, Connection con) {
        return propiedadDao.updatePropiedad(clave, valor, con);
    }
}
