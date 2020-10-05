package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.SocioDAO;
import com.scpakar.scpakarappws.dto.AppOrigen;
import com.scpakar.scpakarappws.dto.ClienteDTO;
import com.scpakar.scpakarappws.dto.ErrorDTO;
import com.scpakar.scpakarappws.dto.SocioDTO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author antonio.ruiz
 */
public class SocioDelegate {

    private final SocioDAO socioDAO;

    public SocioDelegate() {
        this.socioDAO = new SocioDAO();
    }

    public ErrorDTO nuevoCliente(String nombre, int porcentaje, String idSocio, String telefono, Connection con) {
        return socioDAO.nuevoCliente(nombre, porcentaje, idSocio, telefono, con);
    }

    public List<ClienteDTO> getClientesSocio(String idSocio, Connection con) {
        return socioDAO.getClientesSocio(idSocio, con);
    }

    public boolean registraTokenSocio(String idSocio, String token, Connection con) {
        return socioDAO.registraTokenSocio(idSocio, token, con);
    }

    public SocioDTO validaSocio(String idSocio, Connection con) {
        return socioDAO.validaSocio(idSocio, con);
    }

    public ErrorDTO modificarCliente(String idCliente, String nombre,
            int porcentaje, String idSocio, String telefono, boolean activo, Connection con) {
        return socioDAO.modificarCliente(idCliente, nombre, porcentaje, idSocio, telefono, activo, con);
    }

    public boolean validaVersion(String version, AppOrigen origen, Connection con) {
        return socioDAO.validaVersion(version, origen, con);
    }

    public SocioDTO buscaSocioActivo(String idSocio, Connection con) {
        return socioDAO.buscaSocioActivo(idSocio, con);
    }

    public List<String> getOpcionesCancelar(Connection con) {
        return socioDAO.getOpcionesCancelar(con);
    }
}
