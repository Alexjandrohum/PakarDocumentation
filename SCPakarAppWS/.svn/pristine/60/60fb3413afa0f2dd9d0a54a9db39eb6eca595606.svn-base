package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.PedidoDAO;
import com.scpakar.scpakarappws.dao.PropiedadDAO;
import com.scpakar.scpakarappws.dto.ConsultaDTO;
import com.scpakar.scpakarappws.dto.PaqueteriaDTO;
import com.scpakar.scpakarappws.dto.PedidoRecuperadoDTO;
import com.scpakar.scpakarappws.dto.ProductoDTO;
import com.scpakar.scpakarappws.dto.TipoEnvio;
import com.scpakar.scpakarappws.dto.TotalEstatusPedidoDTO;
import com.scpakar.scpakarappws.dto.TotalPedidoDTO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author pablo.martinez
 */
public class PedidoDelegate {

    private final PedidoDAO pedidoDao;
    private final PropiedadDAO propiedadDAO;

    public PedidoDelegate() {
        this.pedidoDao = new PedidoDAO();
        this.propiedadDAO = new PropiedadDAO();
    }

    public String agregaPedido(String socio, int canal, String usuario, Connection con) {
        return pedidoDao.agregaPedido(socio, canal, usuario, con);
    }

    public boolean actualizaCanalConfirmacion(String idPedido, int idCanalConfirma, Connection con) {
        return pedidoDao.actualizaCanalConfirmacion(idPedido, idCanalConfirma, con);
    }

    public boolean actualizaCanalDestino(String idPedido, int idCanalConfirma, String destino, Connection con) {
        return pedidoDao.actualizaCanalDestino(idPedido, idCanalConfirma, destino, con);
    }

    public boolean actualizaDestino(String pedido, String destino, Connection con) {
        return pedidoDao.actualizaDestino(pedido, destino, con);
    }

    public List<ConsultaDTO> buscaProductosDuplicados(String llave, String talla, String socio, Connection con) {
        return pedidoDao.buscaProductosDuplicados(llave, talla, socio, con);
    }

    public boolean agregaProducto(String pedido, String llave, String talla, String codigo, Connection con) {
        return pedidoDao.agregaProducto(pedido, llave, talla, codigo, con);
    }

    public List<ConsultaDTO> buscaProductosDetalle(String pedido, Connection con) {
        return pedidoDao.buscaProductosDetalle(pedido, con);
    }

    public List<ConsultaDTO> buscaProductosDetalleApp(String pedido, Connection con) {
        return pedidoDao.buscaProductosDetalleApp(pedido, con);
    }

    public List<ConsultaDTO> consultaProductosDetalleApp(String idSocio, int tipoPedido, Connection con) {
        return pedidoDao.consultaProductosDetalleApp(idSocio, tipoPedido, con);
    }

    public List<ConsultaDTO> consultaProductosDetalleApp(String idSocio, String idCliente, Connection con) {
        return pedidoDao.consultaProductosDetalleApp(idSocio, idCliente, con);
    }

    public TotalPedidoDTO selectTotales(String idPedido, Connection con) {
        return pedidoDao.selectTotales(idPedido, con);
    }

    public boolean cambiaEstatusProducto(String pedido, int linea, String estatus, Connection con) {
        return pedidoDao.cambiaEstatusProducto(pedido, linea, estatus, con);
    }

    public boolean eliminaProductoApp(String pedido, int linea, Connection con) {
        return pedidoDao.eliminaProductoApp(pedido, linea, con);
    }

    public boolean cambiaEstatusPedido(String pedido, String estatus, Connection con) {
        return pedidoDao.cambiaEstatusPedido(pedido, estatus, con);
    }

    public boolean confirmaPedido(String idPedido, String comentario, String destinatario, String direccionEnvio, Connection con) {
        return pedidoDao.confirmaPedido(idPedido, comentario, destinatario, direccionEnvio, con);
    }

    public boolean actualizaPaqueteria(String pedido, boolean paqueteria, Connection con) {
        return pedidoDao.actualizaPaqueteria(pedido, paqueteria, con);
    }

    public boolean confirmaEnvio(String idPedido, String destinatario, String direccionEnvio, String comentario, Connection con) {
        return pedidoDao.confirmaEnvio(idPedido, destinatario, direccionEnvio, comentario, con);
    }

    public boolean agregaPedidoClientePorcentaje(String idPedido, String idSocio, String idCliente, int porcentaje, Connection con) {
        return pedidoDao.agregaPedidoClientePorcentaje(idPedido, idSocio, idCliente, porcentaje, con);
    }

    public List<PedidoRecuperadoDTO> getPedidosNuevos(String idSocio, Connection con) {
        return pedidoDao.getPedidosNuevos(idSocio, con);
    }

    public List<ConsultaDTO> buscaCodigo(String codigo, String idSocio, Connection con) {
        return pedidoDao.buscaCodigo(codigo, idSocio, con);
    }

    public List<ProductoDTO> busquedaLibre(String busqueda, String idSocio, Connection con) {
        return pedidoDao.busquedaLibre(busqueda, idSocio, con);
    }

    public boolean validaPedido(String idPedido, Connection con) {
        return pedidoDao.validaPedido(idPedido, con);
    }

    public TotalEstatusPedidoDTO getTotalEstatusPedidos(String idSocio, Connection con) {
        return pedidoDao.getEstatusPedidos(idSocio, con);
    }

    public boolean pedidoProrroga(String idUsuario, String pedido, int dias, Connection con) {
        return pedidoDao.pedidoProrroga(idUsuario, pedido, dias, con);
    }

    public int selectDiasProrroga(String idPedido, Connection con) {
        return pedidoDao.selectDiasProrroga(idPedido, con);
    }

    public boolean marcarAbandonado(String idUsuario, String pedido, Connection con) {
        return pedidoDao.marcarAbandonado(idUsuario, pedido, con);
    }

    public boolean marcarProductoAbandonado(String idUsuario, String pedido, int linea, String comentario, Connection con) {
        return pedidoDao.marcarProductoAbandonado(idUsuario, pedido, linea, comentario, con);
    }

    public boolean actualizaCanal(Connection con, String idSocio, int idCanal, String valor, boolean isPreferido, String idUsuario) {
        return pedidoDao.actualizaCanal(con, idSocio, idCanal, valor, isPreferido, idUsuario);
    }

    public boolean actualizaPedidoDomicilio(Connection con, String idPedido, int idCanal, String idAlmacen,
            boolean paqueteria, String destinatario, String direccion, String comentarios) {
        return pedidoDao.actualizaPedidoDomicilio(con, idPedido, idCanal, idAlmacen, paqueteria, destinatario, direccion, comentarios);
    }

    public PaqueteriaDTO ultimaInfoDomicilio(Connection con, String idSocio) {
        return pedidoDao.ultimaInfoDomicilio(con, idSocio);
    }

    public String consultaEnvioDisponible(Connection con, String clave) {
        return propiedadDAO.selectPropiedad(clave, con);
    }
}
