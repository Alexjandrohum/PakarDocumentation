package com.scpakar.scpakarweb.delegate;

import com.scpakar.scpakarweb.dao.AccesoDAO;
import com.scpakar.scpakarweb.dto.ActivacionDTO;
import com.scpakar.scpakarweb.dto.ErrorDTO;
import com.scpakar.scpakarweb.dto.SocioDTO;

/**
 *
 * @author pablo.martinez
 */
public class ActivacionDelegate {

    private final AccesoDAO accesoDao;

    public ActivacionDelegate() {
        this.accesoDao = new AccesoDAO();
    }

    public ErrorDTO activaSocio(ActivacionDTO activacion) {
        return accesoDao.activaSocio(activacion);
    }

    public SocioDTO validaCorreo(String correo) {
        return accesoDao.validaCorreo(correo);
    }
}
