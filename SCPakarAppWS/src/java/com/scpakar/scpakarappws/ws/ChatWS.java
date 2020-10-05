package com.scpakar.scpakarappws.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scpakar.scpakarappws.delegate.ChatDelegate;
import com.scpakar.scpakarappws.dto.ChatMensajeDTO;
import com.scpakar.scpakarappws.util.Configuracion;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
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
 * @author antonio.ruiz
 */
@Path("chat")
public class ChatWS {

    private final GsonBuilder gb;
    private final Gson gson;
    private final ChatDelegate delegate;

    public ChatWS() {
        delegate = new ChatDelegate();
        gb = new GsonBuilder();
        gb.setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT);
        gson = gb.create();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idSocio}")
    public String getMensajesChat(@PathParam("idSocio") String idSocio) {
        List<ChatMensajeDTO> chats = new ArrayList<>();
        Connection connection = null;
        try {
            if (!idSocio.isEmpty()) {
                connection = Factory.getConnection(Configuracion.getJndi());
                chats = delegate.getMensajesChat(idSocio, connection);
            } else {
                Util.registraError(new Exception(getClass().getName() + "-[getMensajesChat]: idSocio empty."));
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(chats);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{idSocio}/enviaMensaje")
    public String enviaMensaje(@QueryParam("mensaje") String mensaje,
            @PathParam("idSocio") String idSocio) {
        boolean resultado = false;
        Connection connection = null;
        try {
            if (!idSocio.isEmpty()) {
                connection = Factory.getConnection(Configuracion.getJndi());
                resultado = delegate.enviaMensaje(mensaje, idSocio, connection);
            } else {
                Util.registraError(new Exception(getClass().getName() + "-[enviaMensaje]: idSocio empty"));
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return String.valueOf(resultado);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("ocultaChats")
    public String ocultaChats(@QueryParam("id") String idSocio) {
        boolean resultado = false;
        Connection connection = null;
        try {
            if (!idSocio.isEmpty()) {
                connection = Factory.getConnection(Configuracion.getJndi());
                resultado = delegate.ocultaChats(idSocio, connection);
            } else {
                Util.registraError(new Exception(getClass().getName() + "-[ocultaChats]: idSocio empty"));
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return String.valueOf(resultado);
    }
}
