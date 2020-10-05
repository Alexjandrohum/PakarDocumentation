package com.scpakar.scpakarweb.delegate;

import com.scpakar.scpakarweb.dao.AfiliacionDAO;
import com.scpakar.scpakarweb.dao.AlmacenDAO;
import com.scpakar.scpakarweb.dto.AfiliacionDTO;
import com.scpakar.scpakarweb.dto.EstadoDTO;
import com.scpakar.scpakarweb.dto.SucursalDTO;
import java.util.List;

/**
 *
 * @author nicolas.canaan
 */
public class AfiliateDelegate {

    private final AfiliacionDAO afiliacionDao;
    private final AlmacenDAO almacenDAO;

    public AfiliateDelegate() {
        this.almacenDAO = new AlmacenDAO();
        this.afiliacionDao = new AfiliacionDAO();
    }

    public List<EstadoDTO> selectEstadosMX() {
        return almacenDAO.selectEstadosMX();
    }

    public List<SucursalDTO> selectAlmacenEstado(String IdEstado, List<EstadoDTO> estados) {
        return almacenDAO.selectAlmacenEstado(IdEstado, estados);
    }

    public boolean guardaAfiliacion(AfiliacionDTO afiliacion) {
        return afiliacionDao.guardaAfiliacion(afiliacion);
    }
}
