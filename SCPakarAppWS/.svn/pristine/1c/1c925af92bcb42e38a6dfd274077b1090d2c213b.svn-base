package com.scpakar.scpakarappws.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scpakar.scpakarappws.delegate.SucursalDelegate;
import com.scpakar.scpakarappws.dto.EstadoDTO;
import com.scpakar.scpakarappws.dto.SucursalDTO;
import com.scpakar.scpakarappws.util.Configuracion;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author antonio.ruiz
 */
@Path("sucursal")
public class SucursalWS {

    private final GsonBuilder gb;
    private final Gson gson;
    private final SucursalDelegate delegate;

    public SucursalWS() {
        delegate = new SucursalDelegate();
        gb = new GsonBuilder();
        gb.setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT);
        gson = gb.create();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSucursales() {
        List<SucursalDTO> lista = new ArrayList<>();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            lista = delegate.selectSucursales(con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(lista);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getEstadoSucursales/{pais}")
    public String getEstadoSucursales(@PathParam("pais") String pais) {
        List<EstadoDTO> estados = new ArrayList<>();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            estados = delegate.getEstadoSucursales(pais, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(estados);
    }
}
