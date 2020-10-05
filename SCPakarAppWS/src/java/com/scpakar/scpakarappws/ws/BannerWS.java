package com.scpakar.scpakarappws.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scpakar.scpakarappws.delegate.CatalogoDelegate;
import com.scpakar.scpakarappws.dto.BannerDTO;
import com.scpakar.scpakarappws.dto.ListaDTO;
import com.scpakar.scpakarappws.dto.DestacadoDTO;
import com.scpakar.scpakarappws.dto.ListaCategoriaDTO;
import com.scpakar.scpakarappws.dto.ProductoDTO;
import com.scpakar.scpakarappws.util.Configuracion;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
@Path("banner")
public class BannerWS {

    private final GsonBuilder gb;
    private final Gson gson;
    private final CatalogoDelegate delegate;

    public BannerWS() {
        delegate = new CatalogoDelegate();
        gb = new GsonBuilder();
        gb.setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT);
        gson = gb.create();
    }

    /**
     * Método para carga de imágenes de app inicio (Banner-Intro) 
     * 
     * @return Devuelve lista de destacados para invitado. 
     * @deprecated use {@link #getInicioDestacados(boolean, java.lang.String, int)} instead. Nueva versión para socios e invitados.
     */
    @Deprecated
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("destacados")
    public String getDestacados() {
        List<DestacadoDTO> lista = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            lista = delegate.selectDestacados(connection);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(lista);
    }

    /**
     * Método para carga de imágenes de app inicio (Banner-Intro) 
     * 
     * @return Devuelve lista string de url banners. 
     * @deprecated use {@link #getBanners()} instead. Para uso de persitencia con DTO.
     */
    @Deprecated
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("intro")
    public String getBannersIntro() {
        List<String> banners = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            banners = delegate.selectBannersIntro(connection);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(banners);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBanners() {
        List<BannerDTO> banners = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            banners = delegate.selectBanners(connection);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(banners);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("inicioDestacados")
    public String getInicioDestacados(@QueryParam("sc") boolean isSocio, @QueryParam("id") String idSocio, @QueryParam("version") int version) {
        List<DestacadoDTO> lista = new ArrayList<>();
        Connection connection = null;
        try {
            if (idSocio == null || idSocio.isEmpty()) {
                idSocio = "";
            }
            connection = Factory.getConnection(Configuracion.getJndi());
            lista = delegate.selectDestacados(isSocio, idSocio, connection, version);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(lista);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaTemporada")
    public String getListaTemporada(@QueryParam("id") int idLista) {
        ListaDTO lista = new ListaDTO();
        Connection connection = null;
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            lista = delegate.selectLista(idLista, connection);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(lista);
    }

    @GET
    @Path("productosTemporadas")
    @Produces(MediaType.APPLICATION_JSON)
    public String getListaProductos(@QueryParam("id") int idLista, @QueryParam("idC") int idCategoria, @QueryParam("idSocio") String idSocio) {
        List<ProductoDTO> productos = new ArrayList<>();
        Connection connection = null;
        String gpoDescuento = "";
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            if (idSocio != null && !idSocio.isEmpty()) {
                gpoDescuento = delegate.selectGrupoDescuento(idSocio, connection);
            }
            productos = delegate.selectListaProductos(idLista, idCategoria, gpoDescuento, connection);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(productos);
    }

    @GET
    @Path("listaCategorias")
    @Produces(MediaType.APPLICATION_JSON)
    public String getListaCategorias(@QueryParam("id") int idLista) {
        List<ListaCategoriaDTO> categorias = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            categorias = delegate.selectListaCategorias(idLista, connection);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(categorias);
    }

    @GET
    @Path("destacadosPakarTeam")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDestacadosPakarTeam(@QueryParam("id") String numeroControl) {
        List<DestacadoDTO> destacados = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            destacados = delegate.selectDestacadosPakarTeam(connection);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(destacados);
    }

}
