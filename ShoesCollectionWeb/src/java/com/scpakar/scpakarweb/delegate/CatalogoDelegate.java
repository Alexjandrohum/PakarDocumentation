package com.scpakar.scpakarweb.delegate;

import com.scpakar.scpakarweb.dao.BannerDAO;
import com.scpakar.scpakarweb.dao.CatalogoDAO;
import com.scpakar.scpakarweb.dto.CatalogoDTO;
import com.scpakar.scpakarweb.dto.CorridaDTO;
import com.scpakar.scpakarweb.dto.DestacadoDTO;
import com.scpakar.scpakarweb.dto.ProductoDTO;
import com.scpakar.scpakarweb.dto.SeccionDTO;
import com.scpakar.scpakarweb.dto.TallaDTO;
import java.util.List;

/**
 *
 * @author pablo.martinez
 */
public class CatalogoDelegate {

    private final BannerDAO bannderDao;
    private final CatalogoDAO catalogoDao;

    public CatalogoDelegate() {
        this.bannderDao = new BannerDAO();
        this.catalogoDao = new CatalogoDAO();
    }

    public List<DestacadoDTO> selectBannerActivo() {
        return bannderDao.selectBannerActivo();
    }

    public List<CatalogoDTO> selectCatalogos() {
        return catalogoDao.selectCatalogos();
    }

    public List<SeccionDTO> selectSeccion(int idCatalogo) {
        return catalogoDao.selectSeccion(idCatalogo);
    }

    public List<ProductoDTO> selectProductoCatalogo(int idCatalogo, int paginaInicial, int paginaFinal, String idSocio) {
        return catalogoDao.selectProductoCatalogo(idCatalogo, paginaInicial, paginaFinal, idSocio);
    }

    public List<TallaDTO> selectTallasProducto(String Llave, String idSocio) {
        return catalogoDao.selectTallasProducto(Llave, idSocio);
    }

    public List<CorridaDTO> selectCorridaProducto(String codigo, String idSocio) {
        return catalogoDao.selectCorridaProducto(codigo, idSocio);
    }
}
