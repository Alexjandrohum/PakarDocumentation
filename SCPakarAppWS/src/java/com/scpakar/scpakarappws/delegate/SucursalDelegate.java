package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.SucursalDAO;
import com.scpakar.scpakarappws.dto.EstadoDTO;
import com.scpakar.scpakarappws.dto.SucursalDTO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author antonio.ruiz
 */
public class SucursalDelegate {

    private final SucursalDAO sucursalDAO;
    
    public SucursalDelegate() {
        sucursalDAO = new SucursalDAO();
    }
    
    public List<SucursalDTO> selectSucursales(Connection con){
        return sucursalDAO.selectSucursales(con);
    }

    public List<EstadoDTO> getEstadoSucursales(String pais, Connection con) {
        return sucursalDAO.getEstadoSucursales(pais, con);
    }
}
