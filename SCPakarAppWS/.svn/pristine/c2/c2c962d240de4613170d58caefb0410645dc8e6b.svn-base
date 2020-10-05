package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.OfertasDAO;
import com.scpakar.scpakarappws.dto.CategoriaOfertaDTO;
import com.scpakar.scpakarappws.dto.ProductoDTO;
import com.scpakar.scpakarappws.dto.TallaDTO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author antonio.ruiz
 */
public class OfertasDelegate {

    private final OfertasDAO ofertasDAO;

    public OfertasDelegate() {
        this.ofertasDAO = new OfertasDAO();
    }

    public List<CategoriaOfertaDTO> selectCategorias(Connection con) {
        return ofertasDAO.selectCategorias(con);
    }

    public List<ProductoDTO> selectProductosLista(int idCategoria, Connection con) {
        return ofertasDAO.selectProductosLista(idCategoria, con);
    }

    public List<TallaDTO> selectTallasOferta(Connection con, String llave) {
        return ofertasDAO.selectTallasOferta(con, llave);
    }
    
    public List<String> selectFiltrosDepartamentos(int idCategoria, Connection con) {
        return ofertasDAO.selectFiltrosDepartamentos(idCategoria, con);
    }
    
    public List<String> selectFiltrosMarcas(int idCategoria, Connection con) {
        return ofertasDAO.selectFiltrosMarcas(idCategoria, con);
    }
    
}
