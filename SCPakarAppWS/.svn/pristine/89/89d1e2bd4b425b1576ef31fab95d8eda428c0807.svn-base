package com.scpakar.scpakarappws.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scpakar.scpakarappws.delegate.CatalogoDelegate;
import com.scpakar.scpakarappws.dto.AppOrigen;
import com.scpakar.scpakarappws.dto.CatalogoDTO;
import com.scpakar.scpakarappws.dto.CorridaDTO;
import com.scpakar.scpakarappws.dto.MonedaDTO;
import com.scpakar.scpakarappws.dto.ProductoDTO;
import com.scpakar.scpakarappws.dto.SeccionDTO;
import com.scpakar.scpakarappws.dto.TallaDTO;
import com.scpakar.scpakarappws.util.Configuracion;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
@Path("catalogo")
public class CatalogoWS {

    private final GsonBuilder gb;
    private final Gson gson;
    private final CatalogoDelegate delegate;

    public CatalogoWS() {
        delegate = new CatalogoDelegate();
        gb = new GsonBuilder();
        gb.setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT);
        gson = gb.create();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getListaCatalogos(@QueryParam("or") int idOrigen) {
        List<CatalogoDTO> lista = new ArrayList<>();
        Connection connection = null;
        try {
            AppOrigen origen = AppOrigen.getOrigen(idOrigen);
            connection = Factory.getConnection(Configuracion.getJndi());
            lista = delegate.selectCatalogos(origen, connection);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(lista);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("seccion/{idCatalogo}")
    public String getSeccionCatalogo(@PathParam("idCatalogo") String idCatalogo) {
        List<SeccionDTO> lista = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            lista = delegate.selectSeccionCatalogo(Integer.parseInt(idCatalogo), connection);
        } catch (NumberFormatException ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(lista);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("producto/{idCatalogo}")
    public String getProductosCatalogo(@PathParam("idCatalogo") int idCatalogo,
            @QueryParam("pi") int paginaInicial, @QueryParam("pf") int paginaFinal, @QueryParam("idSocio") String idSocio) {
        List<ProductoDTO> productos = new ArrayList<>();
        Connection connection = null;
        String gpoDescuento = "";
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            if (idSocio != null && !idSocio.isEmpty()) {
                gpoDescuento = delegate.selectGrupoDescuento(idSocio, connection);
            }
            if (paginaFinal == 0 && paginaInicial == 0) {
                productos = delegate.selectProductoSeccion(idCatalogo, gpoDescuento, connection);
            } else {
                productos = delegate.selectProductoSeccion(idCatalogo, paginaInicial, paginaFinal, gpoDescuento, connection);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(productos);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("talla")
    public String getTallasProducto(@QueryParam("ll") String llave, @QueryParam("idSocio") String idSocio) {
        List<TallaDTO> tallas = new ArrayList<>();
        Connection connection = null;
        String gpoDescuento = "";
        try {
            if (llave != null && !llave.isEmpty()) {
                connection = Factory.getConnection(Configuracion.getJndi());
                if (idSocio != null && !idSocio.isEmpty()) {
                    gpoDescuento = delegate.selectGrupoDescuento(idSocio, connection);
                }
                tallas = delegate.selectTallasProducto(llave, gpoDescuento, connection);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(tallas);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("corrida")
    public String getCorridaProducto(@QueryParam("c") String codigo, @QueryParam("idSocio") String idSocio) {
        List<CorridaDTO> corridas = new ArrayList<>();
        Connection connection = null;
        try {
            if (codigo != null && !codigo.isEmpty()) {
                connection = Factory.getConnection(Configuracion.getJndi());
                corridas = delegate.selectCorridaProducto(codigo, (idSocio == null) ? "" : idSocio, connection);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(corridas);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("opcion")
    public String getOpcionesProducto(@QueryParam("ll") String llave, @QueryParam("id") String idSocio) {
        List<ProductoDTO> opciones = new ArrayList<>();
        Connection connection = null;
        String gpoDescuento = "";
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            if (idSocio != null && !idSocio.isEmpty()) {
                gpoDescuento = delegate.selectGrupoDescuento(idSocio, connection);
            }
            opciones = delegate.selectOpcionesProducto(llave, gpoDescuento, connection);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(opciones);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("busqueda")
    public String getBusquedaProducto(@QueryParam("bu") String busqueda, @QueryParam("id") String idSocio) {
        List<ProductoDTO> productos = new ArrayList<>();
        Connection connection = null;
        String gpoDescuento = "";
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            if (idSocio != null && !idSocio.isEmpty()) {
                gpoDescuento = delegate.selectGrupoDescuento(idSocio, connection);
            }
            if (busqueda != null && !busqueda.isEmpty()) {
                productos = delegate.busquedaLibre(busqueda, gpoDescuento, connection);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(productos);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("pdfPrecio")
    public String getCatalogoPDF(@QueryParam("id") int idCatalogo, @QueryParam("idSocio") String idSocio,
            @QueryParam("pc") String pc, @DefaultValue("MXN") @QueryParam("mn") String moneda) {
        String url = "";
        Connection connection = null;
        try {
            if (pc != null && !pc.isEmpty() && idSocio != null && !idSocio.isEmpty()) {
                connection = Factory.getConnection(Configuracion.getJndi());
                url = delegate.getCatalogoPdfPrecio(connection, idSocio, idCatalogo, Integer.parseInt(pc), moneda);
            }
        } catch (NumberFormatException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return url;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("monedas")
    public String getMonedas() {
        List<MonedaDTO> monedas = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            monedas = delegate.selectMonedas(connection);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(monedas);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("tomasExtra")
    public String getTomasExtra(@QueryParam("id") String codigo) {
        List<String> tomas = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            tomas = delegate.selectTomasExtra(connection, codigo);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(tomas);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("coloresProducto")
    public String getColoresProducto(@QueryParam("id") String codigo, @QueryParam("idSocio") String idSocio) {
        List<ProductoDTO> colores = new ArrayList<>();
        Connection connection = null;
        String gpoDescuento = "";
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            if (idSocio != null && !idSocio.isEmpty()) {
                gpoDescuento = delegate.selectGrupoDescuento(idSocio, connection);
            }
            colores = delegate.getColoresProducto(connection, codigo, gpoDescuento);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(colores);
    }
}
