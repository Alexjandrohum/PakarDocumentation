package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.CuentaDAO;
import com.scpakar.scpakarappws.dto.CabeceraCuponDTO;
import com.scpakar.scpakarappws.dto.PaqueteriaDTO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author antonio.ruiz
 */
public class CuentaDelegate {

    private final CuentaDAO cuentaDAO;

    public CuentaDelegate() {
        cuentaDAO = new CuentaDAO();
    }

    public List<CabeceraCuponDTO> getCupones(String idSocio, Connection connection) {
        return cuentaDAO.getCupones(idSocio, connection);
    }

    public List<PaqueteriaDTO> validaCodigoPostal(String cp, Connection con) {
        return cuentaDAO.validaCodigoPostal(cp, con);
    }

    public boolean registraCP(Connection con, String idSocio, String cp) {
        return cuentaDAO.registraCP(con, idSocio, cp);
    }
}
