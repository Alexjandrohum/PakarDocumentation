package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.LiberacionDAO;
import com.scpakar.scpakarappws.dto.ConsultaDTO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author antonio.ruiz
 */
public class LiberacionDelegate {
    
    private final LiberacionDAO liberacionDAO;
    
    public LiberacionDelegate() {
        liberacionDAO = new LiberacionDAO();
    }

    public List<ConsultaDTO> getProductosDisponibles(Connection connection, String idSocio, String idPedido) {
        return liberacionDAO.getProductosDisponibles(connection, idSocio, idPedido);
    }
    
}
