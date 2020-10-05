/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupopakar.grupopakarappws.ws;

import com.grupopakar.grupopakarappws.delegate.NotificacionesDelegate;
import com.grupopakar.grupopakarappws.dto.EstadoDTO;
import com.grupopakar.grupopakarappws.dto.NotificacionDTO;
import com.grupopakar.grupopakarappws.util.Configuracion;
import java.sql.Connection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author rosa.zalas
 */
@Path("notificaciones")
public class NotificacionesWS {
    
    
    /**
     * Obtiene los notificaciones pendientes del usuario
     *
     * @param tokenDeUsuario token de la session
     * @param 
     * @return Objeto de tipo Response indicando el estatus que devuelve el
     * request y devolviendo un mensaje en formato Json indicando el resultado
     * de la operación request
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotificacionesPendientes(@HeaderParam("token") String tokenDeUsuario) {
        EstadoDTO estado = new EstadoDTO();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            estado = new NotificacionesDelegate().obtenerNotificacionesPendientes(tokenDeUsuario, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return estado.getResponse();
    }
    
    /**
     * Revisa notificación
     * 
     * @param token token de la session
     * @param NotificacionDTO valores en formato Json
     * @return Objeto de tipo Response indicando el estatus que devuelve el request y devolviendo un mensaje en formato Json indicando el resultado de la operación request
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("revisa")
    public Response revisaNotificacion(@HeaderParam("token") String tokenDeUsuario, NotificacionDTO notificacion) {

        EstadoDTO estado = new EstadoDTO();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            System.out.println("holas");
            estado = new NotificacionesDelegate().revisaNotificacion(tokenDeUsuario, notificacion.getIdNotificacion(), con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return estado.getResponse();
    }
}
