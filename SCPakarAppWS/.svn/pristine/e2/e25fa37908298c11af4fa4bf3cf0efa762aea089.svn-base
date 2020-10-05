package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.ConsultaDTO;
import com.scpakar.scpakarappws.dto.Estatus;
import com.scpakar.scpakarappws.dto.PaqueteriaDTO;
import com.scpakar.scpakarappws.dto.PedidoRecuperadoDTO;
import com.scpakar.scpakarappws.dto.ProductoDTO;
import com.scpakar.scpakarappws.dto.Propiedades;
import com.scpakar.scpakarappws.dto.TipoMovimientoPedido;
import com.scpakar.scpakarappws.dto.TotalEstatusPedidoDTO;
import com.scpakar.scpakarappws.dto.TotalPedidoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.com.pakar.dao.PropiedadDAO;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
public class PedidoDAO {

    public String agregaPedido(String socio, int canal, String usuario, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String pedido = "";
        try {
            String qry = "EXEC scpakar_pedido_nuevo_pedido ?,?,?";
            ps = con.prepareStatement(qry);
            ps.setString(1, socio);
            ps.setInt(2, canal);
            ps.setString(3, usuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (!rs.getString(1).startsWith("ERROR")) {
                    pedido = rs.getString(1);
                }
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return pedido;
    }

    public boolean actualizaCanalConfirmacion(String idPedido, int idCanalConfirma, Connection con) {
        PreparedStatement ps = null;
        int resultado = 0;
        String query = "UPDATE pedido SET IdCanalConfirma = ? WHERE IdPedido = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, idCanalConfirma);
            ps.setString(2, idPedido);
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return resultado > 0;
    }

    public boolean actualizaCanalDestino(String idPedido, int idCanalConfirma, String destino, Connection con) {
        PreparedStatement ps = null;
        int resultado = 0;
        String query = "UPDATE pedido SET IdCanalConfirma = ?, IdAlmacenDestino = ? WHERE IdPedido = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, idCanalConfirma);
            ps.setString(2, destino);
            ps.setString(3, idPedido);
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return resultado > 0;
    }

    public boolean actualizaDestino(String pedido, String destino, Connection con) {
        PreparedStatement ps = null;
        boolean exito = false;
        String qry = " UPDATE pedido SET IdAlmacenDestino = ? WHERE idPedido = ?";
        try {
            ps = con.prepareStatement(qry);
            ps.setString(1, destino);
            ps.setString(2, pedido);
            exito = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return exito;
    }

    public List<ProductoDTO> busquedaLibre(String busqueda, String gpoDescuento, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProductoDTO> lista = new ArrayList<>();
        try {
            String urlRepo = selectUrlRepo(con);
            String query = "SELECT Codigo, CodigoUnico, Llave, Marca, NombreMarca, Modelo, Color, Precio, Material, CorridaVisible, Tacon, Dimensiones, Promo, NombreColor, Hex "
                    + "FROM [dbo].[scpakar_producto_busqueda_app]  (?,?) "
                    + "ORDER BY Llave ASC";
            ps = con.prepareCall(query);
            ps.setString(1, busqueda);
            ps.setString(2, gpoDescuento);
            ps.execute();
            rs = ps.getResultSet();
            while (rs.next()) {
                ProductoDTO dto = new ProductoDTO();
                dto.setCodigo(rs.getString("CodigoUnico").trim());
                dto.setCodigoVisible(rs.getString("Codigo").trim());
                dto.setLlave(rs.getString("Llave").trim());
                dto.setMarca(rs.getString("Marca"));
                dto.setNombreMarca(rs.getString("NombreMarca").trim().replace("�", ""));
                dto.setModelo(rs.getString("Modelo").trim());
                dto.setColor(rs.getString("Color").trim());
                dto.setPrecio(rs.getDouble("Precio"));
                dto.setMaterial(rs.getString("Material").trim());
                dto.setUrl(urlRepo + dto.getCodigo() + ".jpg");
                dto.setCorridaVisible(rs.getString("CorridaVisible").trim());
                dto.setTacon(rs.getString("Tacon").trim());
                dto.setDimensiones(rs.getString("Dimensiones").trim());
                dto.setPromocionTexto(rs.getString("Promo").trim());
                dto.setHexColor(rs.getString("Hex").trim());
                dto.setNombreColor(rs.getString("NombreColor").trim());
                lista.add(dto);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return lista;
    }

    public List<ConsultaDTO> buscaProductosDuplicados(String llave, String talla, String socio, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ConsultaDTO> lista = new ArrayList<>();
        int dias = 0;
        String qry = "   SELECT x.IdContacto+' '+CONVERT(VARCHAR(8),x.Inicio,114) pedido,p.Marca+' '+p.Modelo+' '+p.Color articulo, "
                + "   x.Llave llave,x.Talla talla,p.Codigo codigo,x.IdAlmacenOrigen procedencia,x.DiasLlegada tiempo,x.IdEstatus estatus,p.Codigo foto "
                + "   FROM "
                + "   ( "
                + "       SELECT c.IdContacto,c.Inicio,pd.Llave,pd.Talla,IdAlmacenOrigen,pd.DiasLlegada,IdEstatus "
                + "       FROM "
                + "       ( "
                + "           SELECT IdContacto,Inicio,IdSocio FROM contacto WITH(NOLOCK) "
                + "WHERE IdSocio= ? "
                + "AND DATEADD(d,0,DATEDIFF(d,0,Inicio))>=DATEADD(d,0,DATEDIFF(d,0,GETDATE()-?)) "
                + "       ) c "
                + "       INNER JOIN pedido_detalle pd WITH(NOLOCK) "
                + "       ON c.IdContacto=pd.IdPedido "
                + "       AND pd.Llave= ? "
                + "       AND pd.Talla= ? "
                + "       WHERE pd.IdEstatus NOT IN (SELECT IdEstatus FROM estatus WITH(NOLOCK) WHERE Nombre IN ('Negado inventario','Negado operación','Cancelado')) "
                + "   ) x "
                + "   INNER JOIN producto p WITH(NOLOCK) "
                + "   ON x.Llave=p.Llave "
                + "   AND x.Talla=p.Talla "
                + "   ORDER BY pedido desc ";
        try {
            dias = new PropiedadDAO().selectValorNumero(Propiedades.DIAS_PRODUCTO_DUPLICADO.getClave(), con);
            ps = con.prepareCall(qry);
            ps.setString(1, socio);
            ps.setInt(2, dias);
            ps.setString(3, llave);
            ps.setString(4, talla);
            ps.execute();
            rs = ps.getResultSet();
            while (rs.next()) {
                ConsultaDTO dto = new ConsultaDTO();
                dto.setIdPedido(rs.getString("pedido"));
                dto.setArticulo(rs.getString("articulo"));
                dto.setLlave(rs.getString("llave"));
                dto.setTalla(rs.getString("talla"));
                dto.setCodigo(rs.getString("codigo"));
                dto.setInventario(rs.getString("procedencia"));
                dto.setTiempo(rs.getInt("tiempo"));
                dto.setEstatus(Estatus.getEstatus(rs.getInt("estatus")));
                dto.setFoto(rs.getString("foto"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return lista;
    }

    public boolean agregaProducto(String pedido, String llave, String talla, String codigo, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exito = false;
        String qry = "EXEC scpakar_pedido_inserta_producto ?,?,?,?";
        try {
            ps = con.prepareStatement(qry);
            ps.setString(1, pedido);
            ps.setString(2, llave);
            ps.setString(3, talla);
            ps.setString(4, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                exito = !rs.getString(1).startsWith("ERROR");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return exito;
    }

    public List<ConsultaDTO> buscaProductosDetalle(String pedido, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ConsultaDTO> lista = new ArrayList<>();
        String query = " EXEC dbo.scpakar_pedido_productos_detalle ? ";
        try {
            ps = con.prepareCall(query);
            ps.setString(1, pedido);
            ps.execute();
            rs = ps.getResultSet();
            while (rs.next()) {
                ConsultaDTO dto = new ConsultaDTO();
                dto.setLlaveUnica(rs.getString("llaveUnica"));
                dto.setIdPedido(rs.getString("pedido"));
                dto.setLinea(rs.getInt("linea"));
                dto.setLlave(rs.getString("llave"));
                dto.setCodigo(rs.getString("codigo"));
                dto.setArticulo(rs.getString("articulo"));
                dto.setTalla(rs.getString("talla"));
                dto.setPrecio(rs.getDouble("precio"));
                dto.setCatalogo(rs.getString("catalogo"));
                dto.setInventario(rs.getString("procedencia"));
                dto.setFoto(rs.getString("foto"));
                dto.setEstatus(Estatus.getEstatus(rs.getInt("estatus")));
                dto.setResurtido(rs.getString("resurtido"));
                dto.setNegado(rs.getBoolean("negado"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return lista;
    }

    public List<ConsultaDTO> buscaProductosDetalleApp(String pedido, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ConsultaDTO> lista = new ArrayList<>();
        String query = " EXEC  [dbo].[scpakar_pedido_productos_detalle_app] ? ";
        try {
            ps = con.prepareCall(query);
            ps.setString(1, pedido);
            ps.execute();
            rs = ps.getResultSet();
            while (rs.next()) {
                ConsultaDTO dto = new ConsultaDTO();
                dto.setIdPedido(pedido);
                //dto.setLlaveUnica(rs.getString("llaveUnica"));
                dto.setIdCanal(rs.getInt("IdCanal"));
                dto.setLinea(rs.getInt("Linea"));
                dto.setFoto(rs.getString("Imagen").trim());
                dto.setLlave(rs.getString("Llave").trim());
                dto.setTalla(rs.getString("Talla").trim());
                dto.setTallaVisible(rs.getString("TallaVisible").trim());
                dto.setMarca(rs.getString("Marca").trim());
                dto.setNombreMarca(rs.getString("MarcaCompleta").trim().replace("�", ""));
                dto.setModelo(rs.getString("Modelo").trim());
                dto.setColor(rs.getString("Color").trim());
                dto.setArticulo(rs.getString("Descripcion").trim());
                dto.setEstatus(Estatus.getEstatus(rs.getInt("IdEstatus")));
                dto.setIdAlmacenOrigen(rs.getString("Origen"));
                dto.setIdAlmacenDestino(rs.getString("Destino").trim());
                dto.setTiempo(rs.getInt("Dias"));
                dto.setNombreCliente(rs.getString("NombreCliente").trim());
                dto.setPorcentaje(rs.getInt("Porcentaje"));
                dto.setPrecio(rs.getDouble("PrecioSocio"));
                dto.setPrecioCliente(rs.getDouble("PrecioCliente"));
                dto.setGanancia(rs.getDouble("Ganancia"));
                dto.setResurtido(rs.getString("Resurtido").trim());
                dto.setCodigo(rs.getString("Codigo").trim());
                lista.add(dto);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return lista;
    }

    public List<ConsultaDTO> consultaProductosDetalleApp(String idSocio, int tipo, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ConsultaDTO> lista = new ArrayList<>();
        String query = " EXEC [dbo].[scpakar_pedido_productos_consulta_app] ?, ?";
        try {
            ps = con.prepareCall(query);
            ps.setString(1, idSocio);
            ps.setInt(2, tipo);
            ps.execute();
            rs = ps.getResultSet();
            while (rs.next()) {
                ConsultaDTO dto = new ConsultaDTO();
                dto.setIdSocio(idSocio);
                //dto.setLlaveUnica(rs.getString("llaveUnica"));
                dto.setIdPedido(rs.getString("IdPedido"));
                dto.setIdCanal(rs.getInt("IdCanal"));
                dto.setLinea(rs.getInt("Linea"));
                dto.setFoto(rs.getString("Imagen").trim());
                dto.setLlave(rs.getString("Llave").trim());
                dto.setTalla(rs.getString("Talla").trim());
                dto.setTallaVisible(rs.getString("TallaVisible").trim());
                dto.setMarca(rs.getString("Marca").trim());
                dto.setCodigo(rs.getString("Codigo").trim());
                dto.setNombreMarca(rs.getString("MarcaCompleta").trim().replace("�", ""));
                dto.setModelo(rs.getString("Modelo").trim());
                dto.setColor(rs.getString("Color").trim());
                dto.setArticulo(rs.getString("Descripcion").trim());
                dto.setEstatus(Estatus.getEstatus(rs.getInt("IdEstatus")));
                dto.setIdAlmacenDestino(rs.getString("Destino").trim());
                dto.setTiempo(rs.getInt("Dias"));
                dto.setNombreCliente(rs.getString("NombreCliente").trim());
                dto.setPorcentaje(rs.getInt("Porcentaje"));
                dto.setPrecio(rs.getDouble("PrecioSocio"));
                dto.setPrecioCliente(rs.getDouble("PrecioCliente"));
                dto.setGanancia(rs.getDouble("Ganancia"));
                dto.setFechaPedido(rs.getString("FechaCreacion").trim());
                dto.setFechaPrometida(rs.getString("FechaPrometida"));
                dto.setFechaCancela(rs.getString("FechaCancela"));
                dto.setFechaProrroga(rs.getString("FechaProrroga"));
                //dto.setPaqueteria(true);
                lista.add(dto);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return lista;
    }

    public List<ConsultaDTO> consultaProductosDetalleApp(String idSocio, String idCliente, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ConsultaDTO> lista = new ArrayList<>();
        String query = " EXEC [dbo].[scpakar_pedido_productos_consulta_cliente_app] ?,? ";
        try {
            ps = con.prepareCall(query);
            ps.setString(1, idSocio);
            ps.setString(2, idCliente);
            ps.execute();
            rs = ps.getResultSet();
            while (rs.next()) {
                ConsultaDTO dto = new ConsultaDTO();
                dto.setIdSocio(idSocio);
                //dto.setLlaveUnica(rs.getString("llaveUnica"));
                dto.setIdPedido(rs.getString("IdPedido"));
                dto.setIdCanal(rs.getInt("IdCanal"));
                dto.setLinea(rs.getInt("Linea"));
                dto.setFoto(rs.getString("Imagen").trim());
                dto.setLlave(rs.getString("Llave").trim());
                dto.setTalla(rs.getString("Talla").trim());
                dto.setTallaVisible(rs.getString("TallaVisible").trim());
                dto.setMarca(rs.getString("Marca").trim());
                dto.setNombreMarca(rs.getString("MarcaCompleta").trim().replace("�", ""));
                dto.setModelo(rs.getString("Modelo").trim());
                dto.setColor(rs.getString("Color").trim());
                dto.setCodigo(rs.getString("Codigo").trim());
                dto.setArticulo(rs.getString("Descripcion").trim());
                dto.setEstatus(Estatus.getEstatus(rs.getInt("IdEstatus")));
                dto.setIdAlmacenDestino(rs.getString("Destino").trim());
                dto.setTiempo(rs.getInt("Dias"));
                dto.setNombreCliente(rs.getString("NombreCliente").trim());
                dto.setPorcentaje(rs.getInt("Porcentaje"));
                dto.setPrecio(rs.getDouble("PrecioSocio"));
                dto.setPrecioCliente(rs.getDouble("PrecioCliente"));
                dto.setGanancia(rs.getDouble("Ganancia"));
                dto.setFechaPedido(rs.getString("FechaCreacion").trim());
                dto.setFechaPrometida(rs.getString("FechaPrometida"));
                dto.setFechaCancela(rs.getString("FechaCancela"));
                dto.setFechaProrroga(rs.getString("FechaProrroga"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);//(getClass().getName() + ".buscaProductosDetalle SQLException Error: "+ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex);//(getClass().getName() + ".buscaProductosDetalle Exception Error: "+ex.getMessage());
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return lista;
    }

    public int selectTotalPares(Connection connection, String idPedido) {
        int pares = 0;
        String query = "SELECT COUNT(IdPedido) AS pares FROM pedido_detalle WITH(NOLOCK)  "
                + "WHERE IdPedido = ? AND IdEstatus = (SELECT idEstatus FROM estatus WITH(NOLOCK) WHERE Nombre='Nuevo')";
        PreparedStatement ps = null;
        ResultSet r = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idPedido);
            r = ps.executeQuery();
            if (r.next()) {
                pares = r.getInt("pares");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return pares;
    }

    public int selectTotalTiempo(Connection connection, String idPedido) {
        int tiempo = 0;
        String query = "SELECT MAX(DiasLlegada) AS tiempo FROM pedido_detalle WITH(NOLOCK) "
                + "WHERE IdPedido = ? AND IdEstatus <> (SELECT idEstatus FROM estatus WITH(NOLOCK) WHERE Nombre='Cancelado')";
        PreparedStatement ps = null;
        ResultSet r = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idPedido);
            r = ps.executeQuery();
            if (r.next()) {
                tiempo = r.getInt("tiempo");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return tiempo;
    }

    public double selectTotalImporte(Connection connection, String idPedido) {
        double total = 0;
        String query = "SELECT CAST(ISNULL(SUM(ISNULL(Precio,0)),0) AS DECIMAL(10,2)) importe FROM pedido_detalle WITH(NOLOCK) "
                + "WHERE IdPedido = ? AND IdEstatus = (SELECT idEstatus FROM estatus WITH(NOLOCK) WHERE Nombre='Nuevo')";
        PreparedStatement ps = null;
        ResultSet r = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idPedido);
            r = ps.executeQuery();
            if (r.next()) {
                total = r.getInt("importe");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return total;
    }

    public double selectTotalImporteCliente(Connection connection, String idPedido) {
        double total = 0;
        String query = "SELECT CAST(ISNULL(SUM(ISNULL((pd.Precio * (1+(CAST(cp.Porcentaje AS DECIMAL(10,2))/100))),0)),0) AS DECIMAL(10,2)) importe "
                + "FROM pedido_detalle pd WITH(NOLOCK) "
                + "INNER JOIN scp_cliente_pedido cp WITH(NOLOCK) "
                + "ON pd.IdPedido = cp.IdPedido AND pd.Linea = cp.Linea "
                + "WHERE pd.IdPedido = ? AND pd.IdEstatus = (SELECT idEstatus FROM estatus WITH(NOLOCK) WHERE Nombre='Nuevo') ";
        PreparedStatement ps = null;
        ResultSet r = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idPedido);
            r = ps.executeQuery();
            if (r.next()) {
                total = r.getInt("importe");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return total;
    }

    public TotalPedidoDTO selectTotales(String idPedido, Connection connection) {
        TotalPedidoDTO totales = new TotalPedidoDTO();
        try {
            totales.setTotalPares(selectTotalPares(connection, idPedido));
            totales.setTotalImporte(selectTotalImporte(connection, idPedido));
            totales.setTotalTiempo(selectTotalTiempo(connection, idPedido));
            totales.setTotalCliente(selectTotalImporteCliente(connection, idPedido));
        } catch (Exception ex) {
            Util.registraError(ex);
        }
        return totales;
    }

    public boolean cambiaEstatusProducto(String pedido, int linea, String estatus, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exito = false;
        String qry = "EXEC scpakar_pedido_cambia_estatus_producto ?,?,? ";
        try {
            ps = con.prepareStatement(qry);
            ps.setString(1, pedido);
            ps.setInt(2, linea);
            ps.setString(3, estatus);
            rs = ps.executeQuery();
            if (rs.next()) {
                exito = !rs.getString(1).startsWith("ERROR");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return exito;
    }

    public boolean eliminaProductoApp(String pedido, int linea, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exito = false;
        String qry = "EXEC [dbo].[scpakar_pedido_elimina_producto_app] ?,? ";
        try {
            ps = con.prepareStatement(qry);
            ps.setString(1, pedido);
            ps.setInt(2, linea);
            rs = ps.executeQuery();
            if (rs.next()) {
                String mensaje = rs.getString("Resultado");
                exito = !mensaje.startsWith("ERROR");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return exito;
    }

    public boolean cambiaEstatusPedido(String pedido, String estatus, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exito = false;
        String qry = "EXEC scpakar_pedido_cambia_estatus ?,? ";
        try {
            ps = con.prepareStatement(qry);
            ps.setString(1, pedido);
            ps.setString(2, estatus);
            rs = ps.executeQuery();
            if (rs.next()) {
                exito = !rs.getString(1).startsWith("ERROR");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return exito;
    }

    /**
     *
     * @param con
     * @param idPedido
     * @return
     */
    public boolean confirmaPedido(String idPedido, String comentario, String destinatario, String direccionEnvio, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exito = false;
        String qry = " EXEC scpakar_pedido_confirma_pedido ?,?,?,?";
        try {
            ps = con.prepareStatement(qry);
            ps.setString(1, idPedido);
            ps.setString(2, comentario);
            ps.setString(3, destinatario);
            ps.setString(4, direccionEnvio);
            rs = ps.executeQuery();
            if (rs.next()) {
                exito = !rs.getString(1).startsWith("ERROR");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return exito;
    }

    public boolean actualizaPaqueteria(String pedido, boolean paqueteria, Connection con) {
        PreparedStatement ps = null;
        boolean exito = false;
        String qry = "UPDATE pedido SET paqueteria = ? WHERE IdPedido = ? ";
        try {
            ps = con.prepareStatement(qry);
            ps.setInt(1, (paqueteria) ? 1 : 0);
            ps.setString(2, pedido);
            exito = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return exito;
    }

    public boolean confirmaEnvio(String idPedido, String destinatario, String direccionEnvio, String comentario, Connection con) {
        PreparedStatement ps = null;
        boolean exito = false;
        String qry = " UPDATE pedido SET NombreDestinatario = ?, DireccionEnvio = ?, Comentarios = ? WHERE idPedido= ? ";
        try {
            ps = con.prepareStatement(qry);
            ps.setString(1, destinatario);
            ps.setString(2, direccionEnvio);
            ps.setString(3, comentario);
            ps.setString(4, idPedido);
            exito = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return exito;
    }

    public boolean agregaPedidoClientePorcentaje(String idPedido, String idSocio, String idCliente, int porcentaje, Connection con) {
        PreparedStatement ps = null;
        ResultSet r = null;
        boolean result = false;
        String query = "EXEC [dbo].[scpakar_cliente_pedido_app] ?,?,?,?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idPedido);
            ps.setString(2, idSocio);
            ps.setString(3, idCliente);
            ps.setInt(4, porcentaje);
            r = ps.executeQuery();
            if (r.next()) {
                result = r.getString("Resultado").startsWith("OK");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return result;
    }

    public List<PedidoRecuperadoDTO> getPedidosNuevos(String idSocio, Connection con) {
        PreparedStatement ps = null;
        ResultSet r = null;
        List<PedidoRecuperadoDTO> pedidos = new ArrayList<>();
        String query = "SELECT IdPedido, Inicio FROM [dbo].[scpakar_pedidos_nuevos_app](?) ORDER BY Inicio DESC";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            r = ps.executeQuery();
            while (r.next()) {
                PedidoRecuperadoDTO p = new PedidoRecuperadoDTO();
                p.setIdPedido(r.getString("Idpedido").trim());
                p.setFechaPedido(r.getTimestamp("Inicio"));
                pedidos.add(p);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return pedidos;
    }

    public List<ConsultaDTO> buscaCodigo(String codigo, String idSocio, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ConsultaDTO> lista = new ArrayList<>();
        try {
            String query = "EXEC dbo.scpakar_pedido_busca_producto_app ?,?";
            String urlRepo = selectUrlRepo(con);
            ps = con.prepareCall(query);
            ps.setString(1, codigo);
            ps.setString(2, idSocio);
            ps.execute();
            rs = ps.getResultSet();
            while (rs.next()) {
                ConsultaDTO dto = new ConsultaDTO();
                dto.setLlaveUnica(rs.getString("llave_unica"));
                dto.setLlave(rs.getString("llave"));
                dto.setCodigo(rs.getString("codigo"));
                dto.setArticulo(rs.getString("articulo"));
                dto.setTalla(rs.getString("talla"));
                dto.setTallaVisible(rs.getString("talla_visible"));
                dto.setPrecio(rs.getDouble("precio"));
                dto.setFoto(urlRepo + rs.getString("foto") + ".jpg");
                dto.setCatalogo(rs.getString("catalogo"));
                dto.setNombreMarca(rs.getString("nombreMarca").replace("�", ""));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return lista;
    }

    public boolean validaPedido(String idPedido, Connection con) {
        PreparedStatement ps = null;
        ResultSet r = null;
        boolean result = false;
        String query = "SELECT IdEstatus FROM pedido WITH(NOLOCK) WHERE IdPedido = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idPedido);
            r = ps.executeQuery();
            if (r.next()) {
                result = r.getInt("IdEstatus") == Estatus.NUEVO.getIdEstatus();
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return result;
    }

    public TotalEstatusPedidoDTO getEstatusPedidos(String idSocio, Connection con) {
        PreparedStatement ps = null;
        ResultSet r = null;
        TotalEstatusPedidoDTO total = new TotalEstatusPedidoDTO();
        String query = "SELECT pvt.[En Proceso], pvt.[En Camino], pvt.[Disponible En Tienda], (pvt.[En Proceso] + pvt.[En Camino] + pvt.[Disponible En Tienda] + pvt.[Historial]) AS Historico "
                + " FROM ( "
                + "	 SELECT CASE e.Nombre WHEN 'Pedido' THEN 'En Proceso' "
                + "	 WHEN 'Surtido' THEN 'En Camino' "
                + "	 WHEN 'Enviado' THEN 'En Camino' "
                + "	 WHEN 'Recibido' THEN 'Disponible En Tienda' "
                + "	 ELSE 'Historial' END "
                + "	 AS estatus FROM pedido_detalle AS d WITH(NOLOCK) "
                + "	 INNER JOIN pedido AS p WITH(NOLOCK) ON d.idPedido = p.idPedido "
                + "	 INNER JOIN estatus AS e WITH(NOLOCK) ON d.idEstatus = e.IdEstatus "
                + "	 LEFT JOIN scp_cliente_pedido cp WITH(NOLOCK) ON d.IdPedido = cp.IdPedido AND p.IdSocio = cp.IdSocio AND d.Linea = cp.Linea "
                + "	 LEFT JOIN scp_cliente c WITH(NOLOCK) ON cp.IdCliente = c.IdCliente AND cp.IdSocio = c.IdSocio "
                + "	 WHERE p.IdSocio = ? AND e.Nombre NOT IN ('Cancelado','Nuevo','Cancelado Operacion') AND ISNULL(cp.OcultaNegado,0) = 0 "
                + "	AND p.FechaCreacion >= DATEADD(DAY,-(SELECT CAST(valor AS INT) FROM propiedad WITH(NOLOCK) WHERE clave = 'Dias consulta'),DATEDIFF(DAY,0,GETDATE())) "
                + ") AS i PIVOT ( "
                + "	COUNT(estatus) FOR estatus IN ([En Proceso], [Disponible En Tienda], [En Camino], [Historial]) "
                + ") pvt";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            r = ps.executeQuery();
            if (r.next()) {
                total.setTotalPedidos(r.getInt("En Proceso"));
                total.setTotalEnviados(r.getInt("En Camino"));
                total.setTotalRecibidos(r.getInt("Disponible En Tienda"));
                total.setTotalHistorico(r.getInt("Historico"));
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return total;
    }

    public boolean guardaMovimientoPedido(String idSocio, String idPedido, String idUsuario, int dias, TipoMovimientoPedido tipoMovimiento, Connection con) {
        boolean resultado = false;
        PreparedStatement ps = null;
        String query = "";

        switch (tipoMovimiento) {
            case PRORROGA:
                query = "INSERT INTO movimiento_pedido(IdPedido, Linea, IdAgente, Dias, IdTipoMovimiento) "
                        + "SELECT pd.IdPedido, pd.Linea, ?, ?, ? "
                        + "FROM pedido_detalle pd WITH(NOLOCK) "
                        + " WHERE pd.IdPedido = ? "
                        + "AND pd.IdEstatus IN (5)";
                break;
            case ABANDONADO:
                query = "INSERT INTO movimiento_pedido(IdPedido, Linea, IdAgente, Dias, IdTipoMovimiento) "
                        + "SELECT pd.IdPedido, pd.Linea, ?, ?, ? "
                        + "FROM pedido_detalle pd WITH(NOLOCK) "
                        + " WHERE pd.IdPedido = ? "
                        + "AND pd.IdEstatus IN (9)";
                break;
            case REVISADO:
                if (idPedido.isEmpty()) {
                    query = "INSERT INTO movimiento_pedido(IdPedido, Linea, IdAgente, Dias, IdTipoMovimiento) "
                            + "SELECT pd.IdPedido, pd.Linea, ?, ?, ? "
                            + "FROM pedido_detalle pd WITH(NOLOCK) "
                            + "INNER JOIN pedido p WITH(NOLOCK) "
                            + "ON pd.IdPedido = p.IdPedido "
                            + " WHERE p.IdSocio = ? "
                            + "AND pd.IdEstatus IN (6)";
                } else {
                    query = "INSERT INTO movimiento_pedido(IdPedido, Linea, IdAgente, Dias, IdTipoMovimiento) "
                            + "SELECT pd.IdPedido, pd.Linea, ?, ?, ? "
                            + "FROM pedido_detalle pd WITH(NOLOCK) "
                            + " WHERE pd.IdPedido = ? "
                            + "AND pd.IdEstatus IN (6)";
                }
                break;
        }

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idUsuario);
            ps.setInt(2, dias);
            ps.setInt(3, tipoMovimiento.getIdTipo());
            if (idPedido.isEmpty() && tipoMovimiento == TipoMovimientoPedido.REVISADO) {
                ps.setString(4, idSocio);
            } else {
                ps.setString(4, idPedido);
            }
            resultado = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return resultado;
    }

    public boolean pedidoProrroga(String idUsuario, String pedido, int dias, Connection con) {
        PreparedStatement ps = null;
        boolean exito = false;
        String sql = " UPDATE pedido "
                + " SET FechaProrroga = DATEADD(DAY,?,FechaProrroga) "
                + " WHERE IdPedido= ? "
                + " AND DATEADD(DAY,?,FechaProrroga) <= DATEADD(HH,CAST((SELECT valor FROM propiedad WITH(NOLOCK) WHERE clave='Horas Prorroga App')AS INT),FechaCancela) ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, dias);
            ps.setString(2, pedido);
            ps.setInt(3, dias);
            exito = ps.executeUpdate() == 1;

            if (exito) {
                guardaMovimientoPedido("", pedido, idUsuario, dias, TipoMovimientoPedido.PRORROGA, con);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return exito;
    }

    public int selectDiasProrroga(String idPedido, Connection con) {
        PreparedStatement st = null;
        ResultSet r = null;
        int dias = 0;
        String query = "SELECT DATEDIFF(DAY,p.FechaProrroga,DATEADD(HH,CAST((SELECT valor FROM propiedad WITH(NOLOCK) WHERE clave='Horas Prorroga App')AS INT),p.FechaCancela)) AS Dias "
                + "FROM pedido p WITH(NOLOCK) "
                + "WHERE p.IdPedido = ? ";
        try {
            st = con.prepareStatement(query);
            st.setString(1, idPedido);
            r = st.executeQuery();
            if (r.next()) {
                dias = r.getInt("Dias");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(st);
        }
        return dias;
    }

    public boolean marcarAbandonado(String idUsuario, String pedido, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exito = false;
        String qry = " EXEC scpakar_monitor_marcar_abandonado ?,?";
        try {
            ps = con.prepareStatement(qry);
            ps.setString(1, pedido);
            ps.setString(2, idUsuario);

            rs = ps.executeQuery();
            if (rs.next()) {
                String mensaje = rs.getString(1);
                exito = !mensaje.startsWith("ERROR");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return exito;
    }

    public boolean marcarProductoAbandonado(String idUsuario, String pedido, int linea, String comentario, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exito = false;
        String mensaje = "";
        String qry = "EXEC [dbo].[scpakar_marca_producto_abanadonado] ?,?,?,?,1";
        try {
            ps = con.prepareStatement(qry);
            ps.setString(1, pedido);
            ps.setInt(2, linea);
            ps.setString(3, idUsuario);
            ps.setString(4, comentario);

            rs = ps.executeQuery();
            if (rs.next()) {
                mensaje = rs.getString("Resultado");
                exito = !mensaje.startsWith("ERROR");
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return exito;
    }

    private String selectUrlRepo(Connection connection) {
        String valorPropiedad = "";
        Statement st = null;
        ResultSet r = null;
        String query = "SELECT REPLACE(pr2.valor, '?', pr.valor) AS valor "
                + "FROM propiedad pr WITH(NOLOCK) "
                + "INNER JOIN propiedad pr2 WITH(NOLOCK)  "
                + "ON pr2.clave = 'Imagenes App' "
                + "WHERE pr.clave = 'Temporada Actual'";
        try {
            st = connection.createStatement();
            r = st.executeQuery(query);
            if (r.next()) {
                valorPropiedad = r.getString("valor").trim();
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(st);
        }
        return valorPropiedad;
    }

    public boolean actualizaCanal(Connection con, String idSocio, int idCanal, String valor, boolean isPreferido, String idUsuario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean resultado = false;
        String query = "EXEC actualiza_canal_socio ?,?,?,?,?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            ps.setInt(2, idCanal);
            ps.setString(3, valor);
            ps.setBoolean(4, isPreferido);
            ps.setString(5, idUsuario);
            rs = ps.executeQuery();
            if (rs.next() && !rs.getString(1).startsWith("ERROR")) {
                resultado = true;
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return resultado;
    }

    public PaqueteriaDTO ultimaInfoDomicilio(Connection con, String idSocio) {
        PaqueteriaDTO p = new PaqueteriaDTO();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String qry = "SELECT dbo.get_ultima_direccion(?) AS direccion,  "
                + "dbo.get_ultimo_destinatario(?) AS destinatario, "
                + "dbo.[get_canal_socio](?,25) AS telefono";
        try {
            ps = con.prepareStatement(qry);
            ps.setString(1, idSocio);
            ps.setString(2, idSocio);
            ps.setString(3, idSocio);
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setDestinatario(rs.getString("Destinatario"));
                p.setTelefono(rs.getString("Telefono"));
                String direccion = rs.getString("direccion");
                if (!direccion.isEmpty()) {
                    String[] parte = direccion.split("\\|", 6);
                    for (int i = 0; i < parte.length; i++) {
                        switch (i) {
                            case 0:
                                p.setCalle(parte[i]);
                                break;
                            case 1:
                                p.setNumero(parte[i]);
                                break;
                            case 2:
                                p.setColonia(parte[i]);
                                break;
                            case 3:
                                p.setCp(parte[i]);
                                break;
                            case 4:
                                p.setCiudad(parte[i]);
                                break;
                            case 5:
                                p.setEstado(parte[i]);
                                break;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return p;
    }

    public boolean actualizaPedidoDomicilio(Connection con, String idPedido, int idCanal, String idAlmacen,
            boolean paqueteria, String destinatario, String direccion, String comentarios) {
        PreparedStatement ps = null;
        boolean exito = false;
        String qry = "UPDATE pedido SET IdCanalConfirma = ?, IdAlmacenDestino = ?, "
                + "paqueteria = ?, NombreDestinatario = ?, DireccionEnvio = ?, "
                + "Comentarios = Comentarios + ? "
                + "WHERE idPedido= ?;";
        try {
            ps = con.prepareStatement(qry);
            ps.setInt(1, idCanal);
            ps.setString(2, idAlmacen);
            ps.setInt(3, (paqueteria) ? 1 : 0);
            ps.setString(4, destinatario);
            ps.setString(5, direccion);
            ps.setString(6, comentarios);
            ps.setString(7, idPedido);
            exito = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return exito;
    }
}
