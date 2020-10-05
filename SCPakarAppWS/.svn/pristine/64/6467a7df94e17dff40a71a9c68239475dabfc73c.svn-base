package com.scpakar.scpakarappws.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scpakar.scpakarappws.delegate.OfertasDelegate;
import com.scpakar.scpakarappws.dto.CategoriaOfertaDTO;
import com.scpakar.scpakarappws.dto.MensajeDTO;
import com.scpakar.scpakarappws.dto.ProductoDTO;
import com.scpakar.scpakarappws.dto.TallaDTO;
import com.scpakar.scpakarappws.util.Configuracion;
import com.scpakar.scpakarappws.util.EstadoHTTPEnum;
import com.scpakar.scpakarappws.util.GsonUtil;
import com.scpakar.scpakarappws.util.ResponseHttp;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author antonio.ruiz
 */
@Path("ofertas")
public class OfertasWS {

    private final GsonBuilder gb;
    private final Gson gson;
    private final OfertasDelegate delegate;

    public OfertasWS() {
        delegate = new OfertasDelegate();
        gb = new GsonBuilder();
        gb.setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT);
        gson = gb.create();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCategorias() {
        List<CategoriaOfertaDTO> lista = new ArrayList<>();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            lista = delegate.selectCategorias(con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(lista);
    }

    @GET
    @Path("lista")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProductosLista(@QueryParam("id") int idCategoria) {
        List<ProductoDTO> lista = new ArrayList<>();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            lista = delegate.selectProductosLista(idCategoria, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(lista);
    }

    @GET
    @Path("producto")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTallasOferta(@QueryParam("ll") String llave) {
        Connection connection = null;
        List<TallaDTO> lista = new ArrayList<>();
        try {
            if (llave != null && !llave.isEmpty()) {
                connection = Factory.getConnection(Configuracion.getJndi());
                lista = delegate.selectTallasOferta(connection, llave);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(lista);
    }

    @GET
    @Path("departamentos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFiltrosDepartamentos(@QueryParam("id") int idCategoria) {
        ResponseHttp respuesta = new ResponseHttp();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            List<String> departamentos = delegate.selectFiltrosDepartamentos(idCategoria, con);
            if (departamentos.size() > 0) {
                respuesta.setEstado(EstadoHTTPEnum.OK);
                respuesta.setObjeto(GsonUtil.gson.toJson(departamentos));
            } else {
                respuesta.setEstado(EstadoHTTPEnum.NO_ACEPTABLE);
                respuesta.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("No existen departamentos")));
            }
        } catch (Exception ex) {
            Util.registraError(ex);
            respuesta.setEstado(EstadoHTTPEnum.NO_DISPONIBLE);
            respuesta.setObjeto(GsonUtil.gson.toJson(new MensajeDTO(ex.getMessage())));
        } finally {
            Factory.close(con);
        }
        return respuesta.getResponse();
    }

    @GET
    @Path("marcas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFiltrosMarcas(@QueryParam("id") int idCategoria) {
        ResponseHttp respuesta = new ResponseHttp();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            List<String> marcas = delegate.selectFiltrosMarcas(idCategoria, con);
            if (marcas.size() > 0) {
                respuesta.setEstado(EstadoHTTPEnum.OK);
                respuesta.setObjeto(GsonUtil.gson.toJson(marcas));
            } else {
                respuesta.setEstado(EstadoHTTPEnum.NO_ACEPTABLE);
                respuesta.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("No existen marcas")));
            }
        } catch (Exception ex) {
            Util.registraError(ex);
            respuesta.setEstado(EstadoHTTPEnum.NO_DISPONIBLE);
            respuesta.setObjeto(GsonUtil.gson.toJson(new MensajeDTO(ex.getMessage())));
        } finally {
            Factory.close(con);
        }
        return respuesta.getResponse();
    }

}
