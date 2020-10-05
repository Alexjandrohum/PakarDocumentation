package com.grupopakar.grupopakarappws.ws;

import com.grupopakar.grupopakarappws.delegate.TiendaDelegate;
import com.grupopakar.grupopakarappws.dto.EstadoDTO;
import com.grupopakar.grupopakarappws.util.Configuracion;
import java.sql.Connection;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author carlos.juarez
 */
@Path("tiendas")
public class TiendaWS {

    @GET
    @Path("cercanas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTiendasCercanas(@HeaderParam("tokenDeUsuario") String tokenUsuario, @QueryParam("lat") String latitud, @QueryParam("lon") String longitud) {
        EstadoDTO estado = new EstadoDTO();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            estado = new TiendaDelegate().obtieneTiendasCercanas(tokenUsuario, latitud, longitud, con);
        } catch (Exception e) {
            Util.registraError(e);
        } finally {
            Factory.close(con);
        }
        return estado.getResponse();
    }

    @GET
    @Path("buscaTienda")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTiendasCoincidencias(@HeaderParam("tokenDeUsuario") String tokenUsuario, @QueryParam("dc") String descripcion) {
        EstadoDTO estado = null;
        Connection con = null;
        try {
            estado = new EstadoDTO();
            con = Factory.getConnection(Configuracion.getJndi());
            estado = new TiendaDelegate().buscaTiendas(tokenUsuario, descripcion, con);
        } catch (Exception e) {
            Util.registraError(e);
        } finally {
            Factory.close(con);
        }
        return estado.getResponse();
    }
    
    @GET
    @Path("getModulos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulos(@HeaderParam("tokenDeUsuario") String tokenUsuario, @QueryParam("nc") String numeroControl) {
        EstadoDTO estado = null;
        Connection con = null;
        try {
            estado = new EstadoDTO();
            con = Factory.getConnection(Configuracion.getJndi());
            estado = new TiendaDelegate().getModulos(tokenUsuario, numeroControl, con);
        } catch (Exception e) {
            Util.registraError(e);
        } finally {
            Factory.close(con);
        }
        return estado.getResponse();
    }
}
