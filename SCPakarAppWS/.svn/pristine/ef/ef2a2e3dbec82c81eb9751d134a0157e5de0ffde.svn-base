package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.AppOrigen;
import com.scpakar.scpakarappws.dto.CatalogoDTO;
import com.scpakar.scpakarappws.dto.CorridaDTO;
import com.scpakar.scpakarappws.dto.MonedaDTO;
import com.scpakar.scpakarappws.dto.ProductoDTO;
import com.scpakar.scpakarappws.dto.SeccionDTO;
import com.scpakar.scpakarappws.dto.TallaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
public class CatalogoDAO {

    public List<CatalogoDTO> selectCatalogos(AppOrigen origen, Connection connection) {
        Statement st = null;
        ResultSet r = null;
        List<CatalogoDTO> catalogos = new ArrayList<>();
        String query = "SELECT c.id_catalogo, COALESCE(NULLIF(c.nombre,''), catalogo)AS catalogo, c.temporada, "
                + " c.paginas, ISNULL(url_pdf,'') AS url_pdf, c.color, c.premium, c.catalogo AS catalogo_url, COUNT(DISTINCT s.id_producto) AS num_articulos, "
                + " CASE WHEN pd.clave IS NULL THEN 0 ELSE 1 END  AS pdf_precio "
                + "FROM catalogo c WITH(NOLOCK) "
                + "INNER JOIN propiedad p WITH(NOLOCK) "
                + "ON c.temporada = p.valor "
                + "LEFT JOIN producto_seccion s WITH(NOLOCK) "
                + "ON c.id_catalogo = s.id_catalogo AND s.x <> 0 AND s.y <> 0 "
                + "LEFT JOIN catalogo_pdf pd WITH(NOLOCK) "
                + "ON c.id_catalogo = pd.id_catalogo "
                + "WHERE c.id_estatus = 1 AND c.visible = 1 "
                + "AND p.clave = 'Temporada Actual' "
                + "GROUP BY c.id_catalogo, c.nombre, c.catalogo, c.paginas, c.url_pdf, c.color, c.premium, c.orden, c.temporada, pd.clave "
                + "ORDER BY orden";
        try {
            //String temporada = new PropiedadDAO().selectValor("Temporada Actual", connection);
            //String urlRepo = new PropiedadDAO().selectValor("Imagenes App", connection);

            st = connection.createStatement();
            r = st.executeQuery(query);
            while (r.next()) {
                CatalogoDTO c = new CatalogoDTO();
                c.setIdCatalogo(r.getInt("id_catalogo"));
                c.setNombre(r.getString("catalogo").trim());
                c.setPaginas(r.getInt("paginas"));
                String url = (origen == AppOrigen.ANDROID || origen == AppOrigen.IOS)
                        ? "http://repository.scpakarmx.com/img_app/"
                        + r.getString("temporada") + "/portada/"
                        + r.getString("catalogo_url").trim().replace(" ", "_") + ".png"
                        : "http://repository.scpakarmx.com/img_cat/"
                        + c.getTemporada() + "/portada/"
                        + r.getString("catalogo_url").trim().replace(" ", "_") + ".png";
                c.setUrl(url);
                c.setPdfUrl(r.getString("url_pdf"));
                c.setPdfPrecios(r.getBoolean("pdf_precio"));
                c.setColor(r.getString("color"));
                c.setPremium(r.getBoolean("premium"));
                c.setNumeroArticulos(r.getInt("num_articulos"));
                //c.setNumeroArticulos(selectNumeroArticulos(connection, c.getIdCatalogo(), 0, 0));
                catalogos.add(c);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(st);
        }
        return catalogos;
    }

    public List<SeccionDTO> selectSeccionCatalogo(int idCatalogo, Connection connection) {
        PreparedStatement ps = null;
        ResultSet r = null;
        List<SeccionDTO> secciones = new ArrayList<>();
        String query = "SELECT  s.seccion, s.pagina, c.catalogo, c.paginas, c.temporada "
                + "FROM catalogo c WITH(NOLOCK) "
                + "INNER JOIN seccion s WITH(NOLOCK) "
                + "ON c.id_catalogo = s.id_catalogo "
                + "INNER JOIN propiedad p WITH(NOLOCK) "
                + "ON p.valor = c.temporada "
                + "WHERE c.id_estatus = 1 AND c.visible = 1 "
                + "AND p.clave = 'Temporada Actual' "
                + "AND c.id_catalogo = ? "
                + "ORDER BY c.orden, s.pagina";
        try {
            //String temporada = new PropiedadDAO().selectValor("Temporada Actual", connection);
            //String urlRepo = new PropiedadDAO().selectValor("Imagenes App", connection);

            ps = connection.prepareStatement(query);
            ps.setInt(1, idCatalogo);
            r = ps.executeQuery();
            int paginasCatalogo = 0;
            while (r.next()) {
                SeccionDTO s = new SeccionDTO();
                s.setNombre(r.getString("seccion").trim());
                s.setPagina(r.getInt("pagina"));
                s.setIdSeccion(String.valueOf(idCatalogo) + s.getPagina());
                s.setIdCatalogo(idCatalogo);
                String pagina = null;
                switch (Integer.toString(s.getPagina()).length()) {
                    case 1:
                        pagina = "00" + s.getPagina();
                        break;
                    case 2:
                        pagina = "0" + s.getPagina();
                        break;
                    default:
                        pagina = Integer.toString(s.getPagina());
                        break;
                }
                s.setUrl("http://repository.scpakarmx.com/img_cat/" + r.getString("temporada") + "/" + r.getString("catalogo").trim().replace(" ", "_") + "/normal/" + pagina + ".jpg");
                paginasCatalogo = r.getInt("paginas");

                secciones.add(s);
            }

            for (int i = 0; i < secciones.size(); i++) {
                SeccionDTO se = secciones.get(i);
                if (i == 0) {
                    se.setPagina(1);
                    se.setPaginaFinal(secciones.size() > 1 ? secciones.get(i + 1).getPagina() - 1 : paginasCatalogo);
                } else if ((i + 1) == secciones.size()) {
                    se.setPaginaFinal(paginasCatalogo);
                } else {
                    se.setPaginaFinal(secciones.get(i + 1).getPagina() - 1);
                }
                secciones.set(i, se);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return secciones;
    }

    public List<ProductoDTO> selectProductoSeccion(int idCatalogo, String gpoDescuento, Connection connection) {
        PreparedStatement ps = null;
        ResultSet r = null;
        List<ProductoDTO> productos = new ArrayList<>();
//        String query = "SELECT  codigo_visible, id_producto, pagina, Departamento, Marca, nombreMarca, Modelo, Color, Codigo, Precio, Corrida, Material, CorridaVisible, NombreColor, Hex, Tacon "
//                + "FROM (   "
//                + "SELECT ps.codigo AS codigo_visible, ps.id_producto, ps.pagina, p.Departamento, p.Marca, ISNULL(mr.nombre_comercial,'') AS nombreMarca, p.Modelo, p.Color, p.Codigo, ISNULL(gd.Precio,p.Precio) as Precio, p.Corrida, p.Material, scwp.Corrida AS CorridaVisible, cg.Color AS NombreColor, cg.Hex, scwp.Tacon "
//                + ", ROW_NUMBER() OVER (PARTITION BY id_producto ORDER BY pagina ASC) AS indice   "
//                + "FROM producto_seccion  ps WITH(NOLOCK)    "
//                + "LEFT JOIN producto p WITH(NOLOCK)    "
//                + "ON ps.id_producto = p.Llave    "
//                + "INNER JOIN scw_producto scwp WITH(NOLOCK)  "
//                + "ON p.Llave = scwp.Llave  "
//                + "INNER JOIN color_base cb WITH(NOLOCK)  "
//                + "ON LEFT(p.Color, 2) = cb.Codigo  "
//                + "INNER JOIN color_general cg WITH(NOLOCK) "
//                + "ON cb.General = cg.Codigo  "
//                + "LEFT JOIN scpakar_vista_nombre_marcas_app mr WITH(NOLOCK)    "
//                + "ON p.Marca = mr.id_marca    "
//                + "LEFT JOIN socio s WITH(NOLOCK) "
//                + "ON s.IdSocio = ? "
//                + "LEFT JOIN scpakar_vista_precio_grupo_descuento gd WITH(NOLOCK) "
//                + "ON gd.Llave = ps.id_producto COLLATE Latin1_General_CI_AS AND gd.GrupoDescuento = s.GrupoDescuento COLLATE Latin1_General_CI_AS "
//                + "WHERE ps.id_catalogo = ? AND ps.x <> 0 AND ps.y <> 0     "
//                + "GROUP BY ps.codigo, ps.id_producto, ps.pagina, p.Departamento, p.Marca, ISNULL(mr.nombre_comercial,''), p.Modelo, p.Color, p.Codigo, p.Precio, p.Corrida, p.Material, scwp.Corrida, cg.Color, cg.Hex, scwp.Tacon  , gd.Precio"
//                + ") AS d   "
//                + "WHERE d.indice = 1 AND d.Precio > 0 "
//                + "ORDER BY pagina "
//                + "option (maxdop 2, optimize for unknown) ";
        String query = "SELECT  codigo_visible, id_producto, pagina, Departamento, "
                + "        Marca, nombreMarca, Modelo, Color, Codigo, Precio, Corrida, Material, "
                + "        CorridaVisible, NombreColor, Hex, Tacon, Dimensiones, Comentario AS Promo "
                + "        , PrecioAntes, Descuento, Oferta, ISNULL(DescuentoAdicional,'') AS DescuentoAdicional  "
                + "  FROM scp_catalogos_productos WITH(NOLOCK) "
                + " WHERE GrupoDescuento = ? "
                + "   AND id_catalogo = ? "
                + " ORDER BY OFERTA DESC, "
                + " pagina "
                + "OPTION (MAXDOP 2, optimize FOR unknown)";
        try {
            //String temporada = new PropiedadDAO().selectValor("Temporada Actual", connection);
            String urlRepo = selectUrlRepo(connection);
            ps = connection.prepareStatement(query);
            ps.setString(1, gpoDescuento);
            ps.setInt(2, idCatalogo);
            r = ps.executeQuery();
            while (r.next()) {
                ProductoDTO p = new ProductoDTO();
                p.setCodigoVisible(r.getString("codigo_visible").trim());
                p.setLlave(r.getString("id_producto").trim());
                p.setPagina(r.getInt("pagina"));
                p.setMarca(r.getString("Marca").trim());
                p.setNombreMarca(r.getString("nombreMarca").trim().replace("�", ""));
                p.setModelo(r.getString("Modelo").trim());
                p.setColor(r.getString("Color").trim());
                p.setCodigo(r.getString("Codigo").trim());
                p.setPrecio(r.getDouble("Precio"));
                p.setMaterial(r.getString("Material"));
                p.setCorrida(r.getString("Corrida").trim());
                p.setCorridaVisible(r.getString("CorridaVisible").trim());
                p.setNombreColor(r.getString("NombreColor").trim());
                p.setHexColor(r.getString("Hex").trim());
                p.setDepartamento(r.getString("Departamento").trim());
                p.setUrl(urlRepo + p.getCodigo() + ".jpg");
                p.setTacon(r.getString("Tacon").trim());
                p.setIdCatalogo(idCatalogo);
                p.setDimensiones(r.getString("Dimensiones").trim());
                p.setPromocionTexto(r.getString("Promo").trim());
                p.setOferta(r.getBoolean("Oferta"));
                if (p.isOferta()) {
                    p.setPrecioOferta(p.getPrecio());
                    p.setPrecio(r.getDouble("PrecioAntes"));
                    p.setDescuentoTexto(r.getString("Descuento"));
                    // Versiones anteriores app
                    try {
                        double descuento = Double.parseDouble(p.getDescuentoTexto());
                        p.setDescuento(descuento);
                        p.setDescuentoTexto(p.getDescuentoTexto() + "%");
                    } catch (NumberFormatException e) {

                    }
                    p.setDescuentoDoble(r.getString("DescuentoAdicional"));
                }
                productos.add(p);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return productos;
    }

    public List<ProductoDTO> selectProductoSeccion(int idCatalogo, int paginaInicial, int paginaFinal, String gpoDescuento, Connection connection) {
        PreparedStatement ps = null;
        ResultSet r = null;
        List<ProductoDTO> productos = new ArrayList<>();
//        String query = "SELECT codigo_visible, id_producto, pagina, Departamento, Marca, nombreMarca, Modelo, Color, Codigo, Precio, Corrida, Material, CorridaVisible, NombreColor, Hex, Tacon, idSeccion  "
//                + "FROM (  "
//                + "	SELECT ps.codigo AS codigo_visible, ps.id_producto, ps.pagina, p.Departamento, p.Marca, ISNULL(mr.nombre_comercial,'') AS nombreMarca, p.Modelo, p.Color, p.Codigo, ISNULL(gd.Precio,p.Precio) as Precio, p.Corrida, p.Material, scwp.Corrida AS CorridaVisible, cg.Color AS NombreColor, cg.Hex, scwp.Tacon, s.pagina AS IdSeccion  "
//                + "	, ROW_NUMBER() OVER (PARTITION BY id_producto ORDER BY ps.pagina ASC) AS indice   "
//                + "	FROM producto_seccion  ps WITH(NOLOCK)   "
//                + "	INNER JOIN seccion s WITH(NOLOCK) "
//                + "	ON s.id_catalogo = ps.id_catalogo AND s.pagina >= ? "
//                + "	INNER JOIN producto p WITH(NOLOCK)   "
//                + "	ON ps.id_producto = p.Llave   "
//                + "	INNER JOIN scw_producto scwp WITH(NOLOCK) "
//                + "	ON scwp.Llave = p.Llave "
//                + "	INNER JOIN color_base cb WITH(NOLOCK)  "
//                + "	ON LEFT(p.Color, 2) = cb.Codigo "
//                + "	INNER JOIN color_general cg WITH(NOLOCK) "
//                + "	ON cb.General = cg.Codigo "
//                + "	LEFT JOIN scpakar_vista_nombre_marcas_app mr WITH(NOLOCK)   "
//                + "	ON p.Marca = mr.id_marca   "
//                + "	LEFT JOIN socio so WITH(NOLOCK)"
//                + "	ON so.IdSocio = ?"
//                + "	LEFT JOIN scpakar_vista_precio_grupo_descuento gd WITH(NOLOCK)"
//                + "	ON gd.Llave = ps.id_producto COLLATE Latin1_General_CI_AS AND gd.GrupoDescuento = so.GrupoDescuento COLLATE Latin1_General_CI_AS"
//                + "	WHERE ps.id_catalogo = ? AND ps.x <> 0 AND ps.y <> 0 AND ps.pagina BETWEEN ? AND ?  "
//                + "	GROUP BY ps.codigo, ps.id_producto, ps.pagina, p.Departamento, p.Marca, ISNULL(mr.nombre_comercial,''), p.Modelo, p.Color, p.Codigo, p.Precio, p.Corrida, p.Material, scwp.Corrida, cg.Color, cg.Hex, scwp.Tacon, s.pagina, gd.Precio  "
//                + ") AS d  "
//                + "WHERE d.indice = 1 AND d.Precio > 0 "
//                + "ORDER BY pagina "
//                + "option (maxdop 2, optimize for unknown) ";
        String query = "SELECT codigo_visible, id_producto, pagina, Departamento, "
                + "        Marca, nombreMarca, Modelo, Color, Codigo, Precio, Corrida, Material, "
                + "        CorridaVisible, NombreColor, Hex, Tacon, Dimensiones, Comentario AS Promo "
                + "        , PrecioAntes, Descuento, Oferta, ISNULL(DescuentoAdicional,'') AS DescuentoAdicional "
                + "  FROM scp_catalogos_productos_seccion WITH(NOLOCK) "
                + " WHERE GrupoDescuento = ? "
                + "   AND id_catalogo = ? "
                + "   AND pagina BETWEEN ? AND ? "
                + "ORDER BY OFERTA DESC, "
                + " pagina  "
                + "OPTION (MAXDOP 2, optimize FOR unknown)";
        try {
            //String temporada = new PropiedadDAO().selectValor("Temporada Actual", connection);
            String urlRepo = selectUrlRepo(connection);
            ps = connection.prepareStatement(query);
            //ps.setInt(1, paginaInicial);
            ps.setString(1, gpoDescuento);
            ps.setInt(2, idCatalogo);
            ps.setInt(3, paginaInicial);
            ps.setInt(4, paginaFinal);
            r = ps.executeQuery();
            while (r.next()) {
                ProductoDTO p = new ProductoDTO();
                p.setCodigoVisible(r.getString("codigo_visible").trim());
                p.setLlave(r.getString("id_producto").trim());
                p.setPagina(r.getInt("pagina"));
                p.setMarca(r.getString("Marca").trim());
                p.setNombreMarca(r.getString("nombreMarca").trim().replace("�", ""));
                p.setModelo(r.getString("Modelo").trim());
                p.setColor(r.getString("Color").trim());
                p.setCodigo(r.getString("Codigo").trim());
                p.setPrecio(r.getDouble("Precio"));
                p.setCorrida(r.getString("Corrida").trim());
                p.setCorridaVisible(r.getString("CorridaVisible").trim());
                p.setNombreColor(r.getString("NombreColor").trim());
                p.setHexColor(r.getString("Hex"));
                p.setMaterial(r.getString("Material"));
                p.setDepartamento(r.getString("Departamento").trim());
                p.setUrl(urlRepo + p.getCodigo() + ".jpg");
                p.setTacon(r.getString("Tacon").trim());
                p.setIdSeccion(String.valueOf(idCatalogo) + paginaInicial);
                p.setIdCatalogo(idCatalogo);
                p.setDimensiones(r.getString("Dimensiones").trim());
                p.setPromocionTexto(r.getString("Promo").trim());
                p.setOferta(r.getBoolean("Oferta"));
                if (p.isOferta()) {
                    p.setPrecioOferta(p.getPrecio());
                    p.setPrecio(r.getDouble("PrecioAntes"));
                    p.setDescuentoTexto(r.getString("Descuento"));
                    // Versiones anteriores app
                    try {
                        double descuento = Double.parseDouble(p.getDescuentoTexto());
                        p.setDescuento(descuento);
                        p.setDescuentoTexto(p.getDescuentoTexto() + "%");
                    } catch (NumberFormatException e) {

                    }
                    p.setDescuentoDoble(r.getString("DescuentoAdicional"));
                }
                productos.add(p);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return productos;
    }

    public List<TallaDTO> selectTallasProducto(String llave, String gpoDescuento, Connection connection) {
        PreparedStatement ps = null;
        ResultSet r = null;
        List<TallaDTO> tallas = new ArrayList<>();
        /*Se agrega p.Precio > 0 debido a tallas que no tienen precio ni departamento */
        String query = "SELECT p2.Talla, t.talla_visible, ISNULL(v.Precio,p2.Precio) AS Precio, p2.Llave "
                + "FROM producto p WITH(NOLOCK) "
                + "INNER JOIN producto p2 WITH(NOLOCK) ON p.Codigo = p2.Codigo "
                + "LEFT JOIN scpakar_vista_precio_grupo_descuento v WITH(NOLOCK) ON p2.Llave = v.Llave COLLATE Latin1_General_CI_AS AND v.GrupoDescuento = ? "
                + "INNER JOIN talla t WITH(NOLOCK) ON p2.Talla = t.talla "
                + "WHERE p.Llave = ? "
                + "AND p2.Precio > 0 "
                + "GROUP BY p2.Talla, t.talla_visible, t.orden, p2.Precio, p2.Llave, v.Precio "
                + "ORDER BY t.orden "
                + "option (maxdop 2, optimize for unknown)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, gpoDescuento);
            ps.setString(2, llave);
            r = ps.executeQuery();
            while (r.next()) {
                TallaDTO t = new TallaDTO();
                t.setTalla(r.getString("Talla").trim());
                t.setTallaVisible(r.getString("talla_visible").trim());
                t.setLlave(r.getString("Llave").trim());
                t.setPrecio(r.getDouble("Precio"));
                tallas.add(t);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return tallas;
    }

    public List<CorridaDTO> selectCorridaProducto(String codigo, String idSocio, Connection connection) {
        PreparedStatement ps = null;
        ResultSet r = null;
        List<CorridaDTO> corridas = new ArrayList<>();
        /*Se agrega p.Precio > 0 debido a tallas que no tienen precio ni departamento */
        String query = "SELECT p.Llave, ISNULL(sp.Corrida,'') AS Corrida, dbo.get_precio_socio(?,p.Llave) AS Precio  "
                + "FROM producto p WITH(NOLOCK) "
                + "LEFT JOIN scw_producto sp WITH(NOLOCK) "
                + "ON p.Llave = sp.Llave "
                + "WHERE p.Codigo = ? "
                + "GROUP BY p.Llave, sp.Corrida, p.Precio "
                + "ORDER BY Llave ASC";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idSocio);
            ps.setString(2, codigo);
            r = ps.executeQuery();
            while (r.next()) {
                CorridaDTO c = new CorridaDTO(r.getString("Llave").trim(), r.getString("Corrida").trim(), r.getDouble("Precio"));

                corridas.add(c);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return corridas;
    }

    public List<ProductoDTO> selectOpcionesProducto(String llave, String gpoDescuento, Connection connection) {
        List<ProductoDTO> opciones = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT LlaveOpcion, Marca, nombreMarca, Modelo, color, Codigo, CodigoGuion, Precio, Corrida, Material, CorridaVisible, NombreColor, Hex, Tacon, Dimensiones, Promo  "
                + "FROM ( "
                + "SELECT DISTINCT op.LlaveOpcion, p.Marca, ISNULL(mr.nombre_comercial,'') AS nombreMarca,  "
                + "p.Modelo, p.Color, p.Codigo, pc.CodigoGuion, ISNULL(gd.Precio,p.Precio) AS Precio, p.Corrida, p.Material, scwp.Corrida AS CorridaVisible, cg.Color AS NombreColor, cg.Hex, scwp.Tacon, scwp.Dimensiones, scwp.Comentario AS Promo  "
                + "FROM opcion_producto op WITH(NOLOCK)  "
                + "INNER JOIN producto p WITH(NOLOCK) ON op.LlaveOpcion = p.Llave  "
                + "INNER JOIN scw_producto scwp WITH(NOLOCK) ON p.Llave = scwp.Llave  "
                + "INNER JOIN color_base cb WITH(NOLOCK) ON LEFT(p.Color, 2) = cb.Codigo  "
                + "INNER JOIN color_general cg WITH(NOLOCK) ON cb.General = cg.Codigo  "
                + "INNER JOIN producto_codigo pc WITH(NOLOCK) ON p.Llave = pc.Llave  "
                + "LEFT JOIN scpakar_vista_precio_grupo_descuento gd WITH(NOLOCK) ON gd.Llave = op.LlaveOpcion COLLATE Latin1_General_CI_AS AND gd.GrupoDescuento = ? "
                + "LEFT JOIN scpakar_vista_nombre_marcas_app mr WITH(NOLOCK) ON p.Marca = mr.id_marca   "
                + "WHERE op.LlaveOrigen = ?  "
                + "GROUP BY op.LlaveOpcion, p.Marca, ISNULL(mr.nombre_comercial,''),  "
                + "p.Modelo, p.Color, p.Codigo, pc.CodigoGuion, p.Corrida, p.Material, scwp.Corrida, cg.Color, cg.Hex, scwp.Tacon, gd.Precio, p.Precio, scwp.Dimensiones, scwp.Comentario "
                + ") AS t WHERE Precio > 0";
        try {
            //String temporada = new PropiedadDAO().selectValor("Temporada Actual", connection);
            String urlRepo = selectUrlRepo(connection);
            ps = connection.prepareStatement(query);
            ps.setString(1, gpoDescuento);
            ps.setString(2, llave);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductoDTO p = new ProductoDTO();
                p.setCodigoVisible(rs.getString("CodigoGuion").trim());
                p.setLlave(rs.getString("LlaveOpcion").trim());
                p.setMarca(rs.getString("Marca").trim());
                p.setNombreMarca(rs.getString("nombreMarca").trim().replace("�", ""));
                p.setModelo(rs.getString("Modelo").trim());
                p.setColor(rs.getString("Color").trim());
                p.setCodigo(rs.getString("Codigo").trim());
                p.setPrecio(rs.getDouble("Precio"));
                p.setMaterial(rs.getString("Material"));
                p.setCorrida(rs.getString("Corrida").trim());
                p.setCorridaVisible(rs.getString("CorridaVisible").trim());
                p.setNombreColor(rs.getString("NombreColor").trim());
                p.setHexColor(rs.getString("Hex").trim());
                p.setUrl(urlRepo + p.getCodigo() + ".jpg");
                p.setTacon(rs.getString("Tacon").trim());
                p.setDimensiones(rs.getString("Dimensiones").trim());
                p.setPromocionTexto(rs.getString("Promo").trim());
                opciones.add(p);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return opciones;
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

    public String selectGrupoDescuento(String idSocio, Connection con) {
        String grupoDescuento = "";
        PreparedStatement ps = null;
        ResultSet r = null;
        String query = "SELECT ISNULL(GrupoDescuento, '') AS gpo "
                + "FROM socio WITH(NOLOCK) "
                + "WHERE IdSocio = ? "
                + "OPTION (MAXDOP 2, optimize FOR unknown) ";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            r = ps.executeQuery();
            if (r.next()) {
                grupoDescuento = r.getString("gpo").trim();
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return grupoDescuento;
    }

    public String getCatalogoPdfPrecio(Connection con, String idSocio, int idCatalogo, int pc, String moneda) {
        String url = "";
        PreparedStatement ps = null;
        ResultSet r = null;
        String query = "EXEC dbo.get_url_catalogo_pdfS ?,?,?,0,?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, pc);
            ps.setInt(2, idCatalogo);
            ps.setString(3, idSocio);
            ps.setString(4, moneda);
            r = ps.executeQuery();
            if (r.next()) {
                url = r.getString("Url").trim();
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return url;
    }

    public List<MonedaDTO> selectMonedas(Connection connection) {
        List<MonedaDTO> monedas = new ArrayList<>();
        Statement st = null;
        ResultSet r = null;
//        String query = "SELECT m.Abreviatura, UPPER(m.Nombre) AS Nombre FROM catalogo_pdf_moneda m WITH(NOLOCK) WHERE m.Activo = 1  ORDER BY Orden ASC";
        String query = "SELECT m.Abreviatura, "
                + "CASE WHEN m.TipoCambio = 0 THEN m.Nombre ELSE (m.Nombre+' ($ '+CAST(m.TipoCambio AS VARCHAR)+')') END AS Nombre "
                + "FROM catalogo_pdf_moneda m WITH(NOLOCK) WHERE m.Activo = 1  ORDER BY Orden ASC";
        try {
            if (connection != null) {
                st = connection.createStatement();
                r = st.executeQuery(query);
                while (r.next()) {
                    MonedaDTO m = new MonedaDTO(r.getString("Abreviatura"), r.getString("Nombre"));
                    monedas.add(m);
                }
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(st);
        }
        return monedas;
    }

    public List<String> selectTomasExtra(Connection connection, String codigo) {
        List<String> tomas = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet r = null;
        String query = "EXEC [dbo].[get_url_tomas_extras] ? ";
        try {
            if (connection != null) {
                ps = connection.prepareStatement(query);
                ps.setString(1, codigo);
                r = ps.executeQuery();
                while (r.next()) {
                    tomas.add(r.getString(1));
                }
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return tomas;
    }

    public List<ProductoDTO> getColoresProducto(Connection connection, String codigo, String gpoDescuento) {
        List<ProductoDTO> colores = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet r = null;
        String query = "SELECT Llave, Marca, nombreMarca, Modelo, color, Codigo, CodigoGuion, Precio, Corrida, Material, CorridaVisible, NombreColor, Hex, Tacon, Dimensiones, Promo   "
                + "FROM (  "
                + "SELECT DISTINCT p.Llave, p.Marca, ISNULL(mr.nombre_comercial,'') AS nombreMarca,   "
                + "p.Modelo, p.Color, p.Codigo, pc.CodigoGuion, ISNULL(gd.Precio,p.Precio) AS Precio, p.Corrida, p.Material, scwp.Corrida AS CorridaVisible, cg.Color AS NombreColor, cg.Hex, scwp.Tacon, scwp.Dimensiones, scwp.Comentario AS Promo   "
                + "FROM producto_color_adicional pca WITH(NOLOCK)   "
                + "INNER JOIN producto_seccion ps WITH(NOLOCK) ON pca.IdCodigo = ps.codigo "
                + "INNER JOIN producto p WITH(NOLOCK) ON ps.id_producto = p.Llave   "
                + "INNER JOIN scw_producto scwp WITH(NOLOCK) ON p.Llave = scwp.Llave   "
                + "INNER JOIN color_base cb WITH(NOLOCK) ON LEFT(p.Color, 2) = cb.Codigo   "
                + "INNER JOIN color_general cg WITH(NOLOCK) ON cb.General = cg.Codigo   "
                + "INNER JOIN producto_codigo pc WITH(NOLOCK) ON p.Llave = pc.Llave   "
                + "LEFT JOIN scpakar_vista_precio_grupo_descuento gd WITH(NOLOCK) ON gd.Llave = p.Llave COLLATE Latin1_General_CI_AS AND gd.GrupoDescuento = ?  "
                + "LEFT JOIN scpakar_vista_nombre_marcas_app mr WITH(NOLOCK) ON p.Marca = mr.id_marca    "
                + "WHERE pca.codigo = ?  "
                + "GROUP BY p.Llave, p.Marca, ISNULL(mr.nombre_comercial,''),   "
                + "p.Modelo, p.Color, p.Codigo, pc.CodigoGuion, p.Corrida, p.Material, scwp.Corrida, cg.Color, cg.Hex, scwp.Tacon, gd.Precio, p.Precio, scwp.Dimensiones, scwp.Comentario  "
                + ") AS t WHERE Precio > 0";
        try {
            if (connection != null) {
                String urlRepo = selectUrlRepo(connection);
                ps = connection.prepareStatement(query);
                ps.setString(1, gpoDescuento);
                ps.setString(2, codigo);
                r = ps.executeQuery();
                while (r.next()) {
                    ProductoDTO p = new ProductoDTO();
                    p.setCodigoVisible(r.getString("CodigoGuion").trim());
                    p.setLlave(r.getString("Llave").trim());
                    p.setMarca(r.getString("Marca").trim());
                    p.setNombreMarca(r.getString("nombreMarca").trim().replace("�", ""));
                    p.setModelo(r.getString("Modelo").trim());
                    p.setColor(r.getString("Color").trim());
                    p.setCodigo(r.getString("Codigo").trim());
                    p.setPrecio(r.getDouble("Precio"));
                    p.setMaterial(r.getString("Material"));
                    p.setCorrida(r.getString("Corrida").trim());
                    p.setCorridaVisible(r.getString("CorridaVisible").trim());
                    p.setNombreColor(r.getString("NombreColor").trim());
                    p.setHexColor(r.getString("Hex").trim());
                    p.setUrl(urlRepo + p.getCodigo() + ".jpg");
                    p.setTacon(r.getString("Tacon").trim());
                    p.setDimensiones(r.getString("Dimensiones").trim());
                    p.setPromocionTexto(r.getString("Promo").trim());
                    colores.add(p);
                }
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(r);
            Factory.close(ps);
        }
        return colores;
    }
}
