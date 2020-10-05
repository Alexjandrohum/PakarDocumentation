package com.scpakar.scpakarappws.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scpakar.scpakarappws.delegate.NotificacionDelegate;
import com.scpakar.scpakarappws.dto.NotificacionDTO;
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
@Path("notificacion")
public class NotificacionWS {

    private final GsonBuilder gb;
    private final Gson gson;
    private final NotificacionDelegate delegate;

    public NotificacionWS() {
        this.delegate = new NotificacionDelegate();
        this.gb = new GsonBuilder();
        this.gb.setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT);
        this.gson = gb.create();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getNotificaciones(@QueryParam("ids") String idSocio) {
        List<NotificacionDTO> notificaciones = new ArrayList<>();
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                notificaciones = delegate.selectNotificacionSocio(idSocio, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(notificaciones);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("ocultaNotificacion")
    public String ocultaNotificacion(@QueryParam("ids") String idSocio, @QueryParam("idc") String idContacto) {
        boolean result = false;
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty() && idContacto != null && !idContacto.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                if (idContacto.equals("-1")) {
                    result = delegate.ocultaNotificacionesSocio(idSocio, con);
                } else {
                    result = delegate.ocultaNotificacionSocio(idSocio, idContacto, con);
                }
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(result);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("noLeidas")
    public String getCuentaNotificacionesPendientes(@QueryParam("ids") String idSocio) {
        int notificaciones = 0;
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                notificaciones = delegate.getNotificacionSocioNoLeidas(idSocio, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(notificaciones);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("chatsNoLeidos")
    public String getCuentaChats(@QueryParam("ids") String idSocio) {
        int notificaciones = 0;
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                notificaciones = delegate.getChatsNoLeidos(idSocio, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(notificaciones);
    }
}
