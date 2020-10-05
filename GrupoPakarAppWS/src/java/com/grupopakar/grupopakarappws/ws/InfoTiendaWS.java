package com.grupopakar.grupopakarappws.ws;

import com.grupopakar.grupopakarappws.delegate.InfoTiendaDelegate;
import com.grupopakar.grupopakarappws.dto.EstadoDTO;
import com.grupopakar.grupopakarappws.dto.InfoTiendaDTO;
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
 * @author rosa.zalas
 */
@Path("infoTienda")
public class InfoTiendaWS {

    /**
     * Obtiene información de la tienda
     *
     * @param tokenDeUsuario token de la session
     * @param idTienda tienda sobre la cual se deben obtener la información
     * @return Objeto de tipo Response indicando el estatus que devuelve el
     * request y devolviendo un mensaje en formato Json indicando el resultado
     * de la operación request
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoTienda(@HeaderParam("token") String tokenDeUsuario, @QueryParam("id") int idTienda) {
        EstadoDTO estado = new EstadoDTO();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            estado = new InfoTiendaDelegate().infoTienda(idTienda, tokenDeUsuario, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return estado.getResponse();
    }

    /**
     * Actualizar información tienda
     *
     * @param tokenDeUsuario token de la session
     * @param infoTiendaDTO objeto con la nueva informacion.
     * @return Objeto de tipo Response indicando el estatus que devuelve el
     * request y devolviendo un mensaje en formato Json indicando el resultado
     * de la operación request
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("actualizaInfo")
    public Response actualizaInfoTienda(@HeaderParam("token") String tokenDeUsuario, InfoTiendaDTO infoTiendaDTO) {
        EstadoDTO estado = new EstadoDTO();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            //Actualiza información tienda
            estado = new InfoTiendaDelegate().actualizaInfoTienda(tokenDeUsuario, infoTiendaDTO, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return estado.getResponse();
    }

}
