package com.scpakar.scpakarappws.ws;

import com.scpakar.scpakarappws.delegate.PropiedadDelegate;
import com.scpakar.scpakarappws.util.Configuracion;
import java.sql.Connection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
@Path("propiedad")
public class PropiedadWS {

    private final PropiedadDelegate delegate;

    public PropiedadWS() {
        delegate = new PropiedadDelegate();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("obtenPropiedad/{clave}")
    public String selectPropiedad(@PathParam("clave") String clavePropiedad) {
        String valor = "";
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            valor = delegate.selectPropiedad(clavePropiedad, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return valor;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("actualizaPropiedad/{clave}&{valor}")
    public String updatePropiedad(@PathParam("clave") String clavePropiedad, @PathParam("valor") String valorPropiedad) {
        boolean resultado = false;
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            resultado = delegate.updatePropiedad(clavePropiedad, valorPropiedad, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(resultado);
    }
}
