package com.grupopakar.grupopakarappws.ws;

import com.grupopakar.grupopakarappws.delegate.TareaDelegate;
import com.grupopakar.grupopakarappws.dto.EstadoDTO;
import com.grupopakar.grupopakarappws.dto.InfoTareaDTO;
import com.grupopakar.grupopakarappws.util.Configuracion;
import java.sql.Connection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author alberto.quirino
 */
@Path("tareas")
public class TareaWS {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTareas(@HeaderParam("tokenDeUsuario") String tokenUsuario, @QueryParam("idTienda") int idTienda) {
        EstadoDTO estado = new EstadoDTO();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            estado = new TareaDelegate().obtieneActividades(tokenUsuario, idTienda, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return estado.getResponse();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("actualizaEstatus")
    public Response actualizaEstatusTarea(@HeaderParam("token") String token, InfoTareaDTO infoTarea) {
        EstadoDTO estado = new EstadoDTO();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            estado = new TareaDelegate().actualizaEstatusTarea(token, infoTarea, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return estado.getResponse();
    }

}
