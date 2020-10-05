package com.scpakar.scpakarweb.delegate;

import com.scpakar.scpakarweb.dao.AccesoDAO;
import com.scpakar.scpakarweb.dto.SocioDTO;

/**
 *
 * @author pablo.martinez
 */
public class AccesoSocioDelegate {
    private final AccesoDAO accesoDao;

    public AccesoSocioDelegate() {
        this.accesoDao = new AccesoDAO();
    }
    
    public SocioDTO validaLogin(String idUsuario, String password) {
        return accesoDao.validaLogin(idUsuario, password);
    }
}
