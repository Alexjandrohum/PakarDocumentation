package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.BannerDAO;
import com.scpakar.scpakarappws.dao.CatalogoDAO;
import com.scpakar.scpakarappws.dao.OfertasDAO;
import com.scpakar.scpakarappws.dao.PedidoDAO;
import com.scpakar.scpakarappws.dto.AppOrigen;
import com.scpakar.scpakarappws.dto.BannerDTO;
import com.scpakar.scpakarappws.dto.DestacadoDTO;
import com.scpakar.scpakarappws.dto.CatalogoDTO;
import com.scpakar.scpakarappws.dto.CorridaDTO;
import com.scpakar.scpakarappws.dto.ListaCategoriaDTO;
import com.scpakar.scpakarappws.dto.ListaDTO;
import com.scpakar.scpakarappws.dto.MonedaDTO;
import com.scpakar.scpakarappws.dto.ProductoDTO;
import com.scpakar.scpakarappws.dto.SeccionDTO;
import com.scpakar.scpakarappws.dto.TallaDTO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author pablo.martinez
 */
public class CatalogoDelegate {

    private final CatalogoDAO catalogoDao;
    private final PedidoDAO pedidoDao;
    private final BannerDAO bannerDao;
    private final OfertasDAO ofertaDao;

    public CatalogoDelegate() {
        this.catalogoDao = new CatalogoDAO();
        this.pedidoDao = new PedidoDAO();
        this.bannerDao = new BannerDAO();
        this.ofertaDao = new OfertasDAO();
    }

    public List<CatalogoDTO> selectCatalogos(AppOrigen origen, Connection connection) {
        return catalogoDao.selectCatalogos(origen, connection);
    }

    public List<SeccionDTO> selectSeccionCatalogo(int idCatalogo, Connection connection) {
        return catalogoDao.selectSeccionCatalogo(idCatalogo, connection);
    }

    public List<ProductoDTO> selectProductoSeccion(int idCatalogo, String gpoDescuento, Connection connection) {
        return catalogoDao.selectProductoSeccion(idCatalogo, gpoDescuento, connection);
    }

    public List<ProductoDTO> selectProductoSeccion(int idCatalogo, int paginaInicial, int paginaFinal, String idSocio, Connection connection) {
        return catalogoDao.selectProductoSeccion(idCatalogo, paginaInicial, paginaFinal, idSocio, connection);
    }

    public List<TallaDTO> selectTallasProducto(String llave, String gpoDescuento, Connection connection) {
        return catalogoDao.selectTallasProducto(llave, gpoDescuento, connection);
    }

    public List<CorridaDTO> selectCorridaProducto(String codigo, String idSocio, Connection connection) {
        return catalogoDao.selectCorridaProducto(codigo, idSocio, connection);
    }

    public List<ProductoDTO> busquedaLibre(String busqueda, String gpoDescuento, Connection connection) {
        return pedidoDao.busquedaLibre(busqueda, gpoDescuento, connection);
    }

    public List<DestacadoDTO> selectDestacados(Connection connection) {
        return bannerDao.selectDestacados(connection);
    }

    public List<DestacadoDTO> selectDestacadosPakarTeam(Connection connection) {
        return bannerDao.selectDestacadosPakarTeam(connection);
    }

    public List<DestacadoDTO> selectDestacados(boolean isSocio, String idSocio, Connection connection, int version) {
        return bannerDao.selectDestacados(isSocio, idSocio, connection, version);
    }

    public List<String> selectBannersIntro(Connection connection) {
        return bannerDao.selectBannersIntro(connection);
    }

    public List<BannerDTO> selectBanners(Connection connection) {
        return bannerDao.selectBanners(connection);
    }

    public List<ProductoDTO> selectOpcionesProducto(String llave, String gpoDescuento, Connection connection) {
        return catalogoDao.selectOpcionesProducto(llave, gpoDescuento, connection);
    }

    public List<ProductoDTO> selectListaProductos(int idLista, int idCategoria, String gpoDescuento, Connection connection) {
        return ofertaDao.selectListaProductos(idLista, idCategoria, gpoDescuento, connection);
    }

    public ListaDTO selectLista(int idLista, Connection connection) {
        return ofertaDao.selectLista(idLista, connection);
    }

    public String selectGrupoDescuento(String idSocio, Connection con) {
        return catalogoDao.selectGrupoDescuento(idSocio, con);
    }

    public List<ListaCategoriaDTO> selectListaCategorias(int idLista, Connection connection) {
        return ofertaDao.selectListaCategorias(idLista, 0, connection);
    }

    public String getCatalogoPdfPrecio(Connection connection, String idSocio, int idCatalogo, int pc, String moneda) {
        return catalogoDao.getCatalogoPdfPrecio(connection, idSocio, idCatalogo, pc, moneda);
    }

    public List<MonedaDTO> selectMonedas(Connection connection) {
        return catalogoDao.selectMonedas(connection);
    }

    public List<String> selectTomasExtra(Connection connection, String codigo) {
        return catalogoDao.selectTomasExtra(connection, codigo);
    }
    
    public List<ProductoDTO> getColoresProducto(Connection connection, String codigo, String gpoDescuento) {
        return catalogoDao.getColoresProducto(connection, codigo, gpoDescuento);
    }
}
