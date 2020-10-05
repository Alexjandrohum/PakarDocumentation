package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.CategoriaOfertaDTO;
import com.scpakar.scpakarappws.dto.ListaCategoriaDTO;
import com.scpakar.scpakarappws.dto.ListaDTO;
import com.scpakar.scpakarappws.dto.ProductoDTO;
import com.scpakar.scpakarappws.dto.TallaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author antonio.ruiz
 */
public class OfertasDAO {

    public List<CategoriaOfertaDTO> selectCategorias(Connection con) {
        Statement st = null;
        ResultSet rs = null;
        String query = "EXEC [dbo].[scpakar_ofertas_trae_clasificacion]";
        List<CategoriaOfertaDTO> listas = new ArrayList<>();
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                CategoriaOfertaDTO categoria = new CategoriaOfertaDTO();
                categoria.setIdCategoria(rs.getInt("IdCategoria"));
                categoria.setNombre(rs.getString("Nombre"));
                categoria.setUrlFoto(rs.getString("UrlFoto"));
                listas.add(categoria);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(st);
        }
        return listas;
    }

    public List<ProductoDTO> selectProductosLista(int idCategoria, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProductoDTO> productos = new ArrayList<>();
        String query = "EXEC [dbo].[scpakar_ofertas_trae_productos] ?";
        try {
            String urlRepo = selectUrlRepo(con);
            ps = con.prepareStatement(query);
            ps.setInt(1, idCategoria);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductoDTO p = new ProductoDTO();
                p.setCodigoVisible(rs.getString("CodigoGuion").trim());
                p.setLlave(rs.getString("Llave").trim());
                p.setMarca(rs.getString("Marca").trim());
                p.setNombreMarca(rs.getString("nombre_comercial").trim().replace("�", ""));
                p.setModelo(rs.getString("Modelo").trim());
                p.setColor(rs.getString("Color").trim());
                p.setCodigo(rs.getString("Codigo").trim());
                p.setPrecioOferta(rs.getDouble("PrecioAntes"));
                p.setPrecio(rs.getDouble("PrecioOferta"));
                p.setDescuento(rs.getDouble("Descuento"));
                p.setTallasDisponibles(selectTallasOferta(rs.getString("Tallas").trim()));
                p.setOferta(true);
                p.setMaterial(rs.getString("Material"));
                p.setDepartamento(rs.getString("Departamento").trim());
                p.setUrl(urlRepo + p.getCodigo() + ".jpg");
                p.setTacon(rs.getString("Tacon").trim());
                p.setDimensiones(rs.getString("Dimensiones").trim());
                p.setPromocionTexto(rs.getString("Promo").trim());
                p.setHexColor(rs.getString("Hex").trim());
                p.setNombreColor(rs.getString("NombreColor").trim());
                productos.add(p);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return productos;
    }

    public List<TallaDTO> selectTallasOferta(Connection connection, String llave) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TallaDTO> tallas = new ArrayList<>();
        String query = "SELECT i.Talla, t.talla_visible "
                + "FROM vwInventarioDisponibleSKU AS i "
                + "INNER JOIN talla AS t WITH (NOLOCK) ON i.Talla = t.talla "
                + "WHERE i.Llave = ? "
                + "GROUP BY i.Talla, t.talla_visible, t.orden "
                + "HAVING SUM(i.Unidades) > 0 "
                + "ORDER BY t.orden "
                + "OPTION (MAXDOP 2, OPTIMIZE FOR UNKNOWN);";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, llave);
            rs = ps.executeQuery();
            while (rs.next()) {
                TallaDTO talla = new TallaDTO();
                talla.setTalla(rs.getString("Talla"));
                talla.setTallaVisible(rs.getString("talla_visible"));
                tallas.add(talla);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return tallas;
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

    public List<ProductoDTO> selectListaProductos(int idLista, int idCategoria, String gpoDescuento, Connection connection) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProductoDTO> productos = new ArrayList<>();
        String query = "EXEC [dbo].[scpakar_destacado_trae_productos] ?,?,?";
//        String resto = idCategoria != 0 ? "AND op.IdCategoria = ? " : "";
//        String query = "SELECT Codigo, Llave, Marca, nombre_comercial, "
//                + "    Modelo, Color, Material, TACON, "
//                + "    Precio, Departamento, "
//                + "    NombreColor, Hex, Corrida, CorridaVisible, Dimensiones, Promo "
//                + "FROM (    "
//                + "    SELECT DISTINCT p.Codigo, p.Llave, p.Marca, mr.nombre_comercial, "
//                + "    p.Modelo, p.Color, p.Material, ISNULL(scwp.Tacon,0) AS TACON, "
//                + "    ISNULL(gd.Precio,p.Precio) AS Precio, ISNULL(op.Departamento, p.Departamento) AS Departamento, "
//                + "    cg.Color AS NombreColor, cg.Hex, p.Corrida, ISNULL(scwp.Corrida, '') AS CorridaVisible, ISNULL(scwp.Dimensiones, '') as Dimensiones, ISNULL(scwp.Comentario, '') AS Promo "
//                + "    FROM scp_oferta_lista ol WITH(NOLOCK) "
//                + "    INNER JOIN scp_lista_producto op WITH(NOLOCK) "
//                + "    ON ol.IdLista = op.IdLista " + resto
//                + "    INNER JOIN producto p WITH(NOLOCK) "
//                + "    ON op.Llave = p.Llave "
//                + "    INNER JOIN scpakar_vista_nombre_marcas_app mr WITH(NOLOCK) "
//                + "    ON p.Marca = mr.id_marca "
//                + "    LEFT JOIN scpakar_vista_precio_grupo_descuento gd WITH(NOLOCK)"
//                + "    ON gd.Llave = p.Llave COLLATE Latin1_General_CI_AS AND gd.GrupoDescuento = ? "
//                + "    LEFT JOIN scw_producto scwp WITH(NOLOCK) "
//                + "    ON scwp.Llave = p.Llave "
//                + "    INNER JOIN color_base cb WITH(NOLOCK) "
//                + "    ON LEFT(p.Color, 2) = cb.Codigo "
//                + "    INNER JOIN color_general cg WITH(NOLOCK) "
//                + "    ON cb.General = cg.Codigo "
//                + "    WHERE op.idLista = ? AND ol.Activo = 1 "
//                + ") AS l WHERE Precio > 0";
        try {
            String urlRepo = selectUrlRepo(connection);
            ps = connection.prepareStatement(query);
            ps.setString(1, gpoDescuento);
            ps.setInt(2, idLista);
            ps.setInt(3, idCategoria);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductoDTO p = new ProductoDTO();
                p.setCodigoVisible(rs.getString("CodigoGuion".trim()));
                p.setLlave(rs.getString("Llave").trim());
                p.setMarca(rs.getString("Marca").trim());
                p.setNombreMarca(rs.getString("nombre_comercial").trim().replace("�", ""));
                p.setModelo(rs.getString("Modelo").trim());
                p.setColor(rs.getString("Color").trim());
                p.setCodigo(rs.getString("codigo").trim());
                p.setPrecio(rs.getDouble("Precio"));
                p.setMaterial(rs.getString("Material"));
                p.setDepartamento(rs.getString("Departamento").trim());
                p.setUrl(urlRepo + p.getCodigo() + ".jpg");
                p.setTacon(rs.getString("Tacon").trim());
                p.setNombreColor(rs.getString("NombreColor").trim());
                p.setCorrida(rs.getString("Corrida").trim());
                p.setCorridaVisible(rs.getString("CorridaVisible").trim());
                p.setHexColor(rs.getString("Hex").trim());
                p.setDimensiones(rs.getString("Dimensiones").trim());
                p.setPromocionTexto(rs.getString("Promo").trim());
                p.setOferta(rs.getBoolean("Oferta"));
                if (p.isOferta()) {
                    p.setPrecioOferta(p.getPrecio());
                    p.setPrecio(rs.getDouble("PrecioOferta"));
                    p.setDescuentoTexto(rs.getString("Descuento"));
                    try {
                        double descuento = Double.parseDouble(p.getDescuentoTexto());
                        p.setDescuento(descuento);
                        p.setDescuentoTexto(p.getDescuentoTexto() + "%");
                    } catch (NumberFormatException e) {

                    }
                    //p.setTallasDisponibles(selectTallasOferta(connection, p.getLlave()));
                    p.setDescuentoDoble(rs.getString("DescuentoAdicional"));
                }
                p.setTallasDisponibles(selectTallasOferta(p.getCorridaVisible()));
                productos.add(p);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return productos;
    }

    public ListaDTO selectLista(int idLista, Connection connection) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ListaDTO lista = new ListaDTO();
        String query = "SELECT l.IdLista, Nombre, URLFoto, COUNT(DISTINCT Llave) AS Cantidad, Informacion, PrecioOferta "
                + "FROM scp_oferta_lista l WITH(NOLOCK) "
                + "INNER JOIN scp_lista_producto s (NOLOCK) ON l.IdLista = s.IdLista "
                + "WHERE Activo = 1 "
                + "AND GETDATE() BETWEEN ActivoDesde AND ActivoHasta "
                + "AND l.IdLista = ? "
                + "GROUP BY l.IdLista, Nombre, URLFoto, Informacion, PrecioOferta "
                + "OPTION (MAXDOP 2, optimize FOR unknown)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idLista);
            rs = ps.executeQuery();
            if (rs.next()) {
                lista.setNombre(rs.getString("Nombre"));
                lista.setIdLista(rs.getInt("IdLista"));
                lista.setUrlFoto(rs.getString("URLFoto"));
                lista.setCantidad(rs.getInt("Cantidad"));
                lista.setInformacion(rs.getString("Informacion"));
                lista.setPrecioOferta(rs.getInt("PrecioOferta"));
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

    public List<ListaCategoriaDTO> selectListaCategorias(int idLista, int idCategoria, Connection connection) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ListaCategoriaDTO> lista = new ArrayList<>();
        //String resto = idCategoria != 0 ? "AND IdPadre = ? " : "AND IdPadre IS NULL ";
        //String resto2 = idCategoria != 0 ? "" : "OR c.IdLista = L.IdLista ";
        String query2 = "SELECT IdCategoria, Nombre, URLFoto "
                + "FROM scp_listas_categoria WITH(NOLOCK) "
                + "WHERE IdLista = ? "
                + "AND Activo = 1 "
                + "AND IdPadre = ? "
                + "ORDER BY Orden "
                + "OPTION (OPTIMIZE FOR UNKNOWN, MAXDOP 2)";
//        String query = "SELECT x.IdCategoria, x.Nombre, x.URLFoto "
//                + "  FROM (SELECT c.IdCategoria, c.Nombre, c.URLFoto, p.Llave, c.Orden "
//                + "          FROM scp_oferta_lista AS L WITH(NOLOCK) "
//                + "            INNER JOIN scp_lista_producto AS P WITH(NOLOCK) "
//                + "            INNER JOIN vwInventarioDisponibleSKU AS W WITH(NOLOCK) ON P.Llave = W.Llave ON L.IdLista = P.IdLista "
//                + "            INNER JOIN scp_listas_categoria AS c WITH(NOLOCK) ON c.IdLista = L.IdLista "
//                + "         WHERE L.IdLista = ? "
//                + "           and L.Activo = 1 "
//                + "           AND c.Activo = 1 "
//                + "           AND GETDATE() BETWEEN L.ActivoDesde AND L.ActivoHasta "
//                + " AND IdPadre IS NULL "
//                + "         GROUP BY c.IdCategoria, c.Nombre, c.URLFoto, c.Orden, p.Llave "
//                + "        HAVING SUM(W.Unidades) >0) as x "
//                + "  GROUP BY x.IdCategoria, x.Nombre, x.URLFoto, x.Orden "
//                + " HAVING COUNT(x.Llave) >0 "
//                + "  ORDER BY x.Orden "
//                + "OPTION (OPTIMIZE FOR UNKNOWN, MAXDOP 2) ";
        String query = "SELECT IdCategoria, Nombre, URLFoto "
                + "FROM scp_listas_categoria WITH(NOLOCK) "
                + "WHERE IdLista = ? "
                + "AND Activo = 1 "
                + "AND IdPadre IS NULL "
                + "ORDER BY Orden "
                + "OPTION (OPTIMIZE FOR UNKNOWN, MAXDOP 2)";
        try {
            if (idCategoria != 0) {
                ps = connection.prepareStatement(query2);
                ps.setInt(2, idCategoria);
            } else {
                ps = connection.prepareStatement(query);
            }
            ps.setInt(1, idLista);
            rs = ps.executeQuery();
            while (rs.next()) {
                ListaCategoriaDTO d = new ListaCategoriaDTO();
                d.setNombre(rs.getString("Nombre"));
                d.setIdLista(idLista);
                d.setUrl(rs.getString("URLFoto"));
                d.setIdCategoria(rs.getInt("IdCategoria"));
                d.setHijos(selectListaCategorias(idLista, d.getIdCategoria(), connection));
                lista.add(d);
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

    private List<TallaDTO> selectTallasOferta(String tallaString) {
        List<TallaDTO> tallas = new ArrayList<>();
        for (String t : Arrays.asList(tallaString.split(", "))) {
            tallas.add(new TallaDTO(t, t));
        }
        return tallas;
    }
    
    /**
     * @author alberto.quirino
     * @param idCategoria
     * @param con
     * @return List de departamentos en base al idCategoria que se pasa como argumento
     */
    public List<String> selectFiltrosDepartamentos(int idCategoria, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> departamentos = new ArrayList<>();
        String query = "SELECT dbo.get_oferta_departamento (?) AS departamentos";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, idCategoria);
            rs = ps.executeQuery();
            while (rs.next()) {
                for (String d : Arrays.asList(rs.getString("departamentos").split(","))) {
                    departamentos.add(d);
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
        return departamentos;
    }
    
    /**
     * @author alberto.quirino
     * @param idCategoria
     * @param con
     * @return List de marcas en base al idCategoria que se pasa como argumento
     */
    public List<String> selectFiltrosMarcas(int idCategoria, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> marcas = new ArrayList<>();
        String query = "SELECT dbo.get_oferta_marca (?) AS marcas";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, idCategoria);
            rs = ps.executeQuery();
            while (rs.next()) {
                for (String d : Arrays.asList(rs.getString("marcas").split(","))) {
                    marcas.add(d);
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
        return marcas;
    }
    
}
