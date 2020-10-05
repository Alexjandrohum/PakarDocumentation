package com.scpakar.scpakarweb.delegate;

import com.scpakar.scpakarweb.dao.RecuperaDAO;
import com.scpakar.scpakarweb.dto.ErrorDTO;

/**
 *
 * @author pablo.martinez
 */
public class RecuperaDelegate {

    public RecuperaDAO recuperaDao;

    public RecuperaDelegate() {
        this.recuperaDao = new RecuperaDAO();
    }

    public ErrorDTO recuperaPassword(String idSocio, String email) {
        return recuperaDao.recuperaPassword(idSocio, email);
    }

    public boolean actualizaPassword(String idSocio, String password, String passwordActual) {
        return recuperaDao.actualizaPassword(idSocio, password, passwordActual);
    }
}
