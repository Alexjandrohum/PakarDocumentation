package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.BannerDTO;
import com.scpakar.scpakarappws.dto.DestacadoDTO;
import com.scpakar.scpakarappws.dto.ProductoDTO;
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
 * @author antonio.ruiz
 */
public class BannerDAO {

    public List<DestacadoDTO> selectDestacados(Connection connection) {
        Statement st = null;
        ResultSet r = null;
        List<DestacadoDTO> banners = new ArrayList<>();
        String query = "SELECT URL, Llave FROM scp_destacados WITH(NOLOCK) WHERE activo = 1 AND Socio = 0";
        try {
            st = connection.createStatement();
            r = st.executeQuery(query);
            while (r.next()) {
                DestacadoDTO b = new DestacadoDTO();
                b.setUrlImagen(r.getString("URL").trim());
                b.setProducto(selectProducto(connection, r.getString("Llave"), ""));
                banners.add(b);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(st);
        }
        return banners;
    }

    public List<DestacadoDTO> selectDestacadosPakarTeam(Connection connection) {
        Statement st = null;
        ResultSet r = null;
        List<DestacadoDTO> destacados = new ArrayList<>();
        String query = "SELECT d.IdDestacado, ISNULL(l.URLFoto,d.URL) AS URLFoto, d.IdModulo, d.IdLista "
                + "FROM scp_destacados d WITH(NOLOCK) "
                + "INNER JOIN scp_oferta_lista l WITH(NOLOCK) ON d.IdLista = l.IdLista "
                + "INNER JOIN scp_oferta_producto p WITH(NOLOCK) ON d.IdLista = p.IdLista "
                + "WHERE l.activo = 1 AND d.socio = 1 AND GETDATE() BETWEEN l.ActivoDesde AND l.ActivoHasta "
                + "GROUP BY d.IdDestacado, ISNULL(l.URLFoto,d.URL), d.IdModulo, d.IdLista, d.Orden "
                + "ORDER BY Orden ASC";
        try {
            st = connection.createStatement();
            r = st.executeQuery(query);
            while (r.next()) {
                DestacadoDTO b = new DestacadoDTO();
                b.setIdDestacado(r.getInt("IdDestacado"));
                b.setUrlImagen(r.getString("URLFoto").trim());
                b.setIdLista(r.getInt("IdLista"));
                b.setIdModulo(r.getInt("IdModulo"));
                destacados.add(b);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(st);
        }
        return destacados;
    }

    public List<DestacadoDTO> selectDestacados(boolean isSocio, String idSocio, Connection connection, int version) {
        PreparedStatement ps = null;
        ResultSet r = null;
        List<DestacadoDTO> banners = new ArrayList<>();
        String query = "SELECT d.IdDestacado, d.URL, ISNULL(d.llave,'') AS Llave, d.IdModulo, d.IdLista "
                + "FROM scp_destacados d WITH(NOLOCK) "
                + "WHERE d.activo = 1 AND d.socio = ? "
                + "ORDER BY Orden ASC ";
        try {
            ps = connection.prepareStatement(query);
            ps.setBoolean(1, isSocio);
            r = ps.executeQuery();
            while (r.next()) {
                DestacadoDTO b = new DestacadoDTO();
                b.setIdDestacado(r.getInt("IdDestacado"));
                b.setUrlImagen(r.getString("URL"));
                b.setIdLista(r.getInt("IdLista"));
                b.setIdModulo(r.getInt("IdModulo"));
                b.setIdModulo(b.getIdModulo() == 2 && version == 0 ? 0 : b.getIdModulo());
                if (!r.getString("llave").isEmpty()) {
                    b.setProducto(selectProducto(connection, r.getString("Llave"), idSocio));
                }
                banners.add(b);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return banners;
    }

    public List<String> selectBannersIntro(Connection connection) {
        Statement st = null;
        ResultSet r = null;
        List<String> banners = new ArrayList<>();
        String query = "SELECT URL FROM scp_banner WITH(NOLOCK) WHERE activo = 1";
        try {
            st = connection.createStatement();
            r = st.executeQuery(query);
            while (r.next()) {
                banners.add(r.getString("URL").trim());
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(st);
        }
        return banners;
    }

    public List<BannerDTO> selectBanners(Connection connection) {
        Statement st = null;
        ResultSet r = null;
        List<BannerDTO> banners = new ArrayList<>();
        String query = "SELECT URL FROM scp_banner WITH(NOLOCK) WHERE activo = 1";
        try {
            st = connection.createStatement();
            r = st.executeQuery(query);
            while (r.next()) {
                banners.add(new BannerDTO(r.getString("URL").trim()));
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(st);
        }
        return banners;
    }

    private ProductoDTO selectProducto(Connection con, String llave, String idSocio) {
        PreparedStatement ps = null;
        ResultSet r = null;
        String query = "SELECT DISTINCT p.Codigo, pc.CodigoGuion, p.Llave, p.Marca, mr.nombre_comercial, p.Modelo, p.Color, [dbo].[get_precio_socio](?,p.Llave) AS Precio, p.Material, scwp.Corrida as CorridaVisible, scwp.Tacon, scwp.Dimensiones, scwp.Comentario AS Promo "
                + ",cg.Color AS NombreColor, cg.Hex "
                + "FROM producto p WITH(NOLOCK) "
                + "INNER JOIN scpakar_vista_producto_catalogo_vigente ps WITH(NOLOCK)  "
                + "ON p.Llave = ps.id_producto "
                + "INNER JOIN scpakar_vista_nombre_marcas_app mr WITH(NOLOCK) "
                + "ON p.Marca = mr.id_marca "
                + "INNER JOIN scw_producto scwp WITH(NOLOCK) "
                + "ON scwp.Llave = p.Llave "
                + "INNER JOIN producto_codigo pc WITH(NOLOCK) "
                + "ON pc.Llave = p.Llave "
                + "INNER JOIN color_base cb WITH(NOLOCK) ON LEFT(p.Color, 2) = cb.Codigo "
                + "INNER JOIN color_general cg WITH(NOLOCK) ON cb.General = cg.Codigo "
                + "WHERE p.Llave = ? "
                + "AND [dbo].[get_precio_socio](?,p.Llave) > 0 ";
        ProductoDTO producto = new ProductoDTO();
        try {
            String temporada = new PropiedadDAO().selectValor("Temporada Actual", con);
            String urlRepo = new PropiedadDAO().selectValor("Imagenes App", con);
            urlRepo = urlRepo.replace("?", temporada);
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            ps.setString(2, llave);
            ps.setString(3, idSocio);
            r = ps.executeQuery();
            if (r.next()) {
                producto.setCodigo(r.getString("codigo"));
                producto.setCodigoVisible(r.getString("CodigoGuion"));
                producto.setLlave(r.getString("Llave"));
                producto.setMarca(r.getString("Marca"));
                producto.setNombreMarca(r.getString("nombre_comercial").trim().replace("�", ""));
                producto.setModelo(r.getString("Modelo"));
                producto.setColor(r.getString("Color"));
                producto.setPrecio(r.getDouble("Precio"));
                producto.setMaterial(r.getString("Material"));
                producto.setCorridaVisible(r.getString("CorridaVisible"));
                producto.setUrl(urlRepo + producto.getCodigo() + ".jpg");
                producto.setTacon(r.getString("Tacon").trim());
                producto.setDimensiones(r.getString("Dimensiones").trim());
                producto.setPromocionTexto(r.getString("Promo").trim());
                producto.setNombreColor(r.getString("NombreColor").trim());
                producto.setHexColor(r.getString("Hex").trim());
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return producto;
    }
}
