/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scpakar.scpakarappws.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scpakar.scpakarappws.delegate.LiberacionDelegate;
import com.scpakar.scpakarappws.dto.ConsultaDTO;
import com.scpakar.scpakarappws.util.Configuracion;
import java.sql.Connection;
import java.text.DateFormat;
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
 * @author antonio.ruiz
 */
@Path("liberacion")
public class LiberacionWS {

    private final LiberacionDelegate delegate;
    private final Gson gson;

    public LiberacionWS() {
        delegate = new LiberacionDelegate();
        GsonBuilder gb = new GsonBuilder();
        gb.setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT);
        gson = gb.create();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("productoDisponible")
    public String getDisponibles(@QueryParam("idSocio") String idSocio, @QueryParam("idPedido") String idPedido) {
        List<ConsultaDTO> pedidos = null;
        Connection connection = null;
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            pedidos = delegate.getProductosDisponibles(connection, idSocio, idPedido);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return gson.toJson(pedidos);
    }

}
