package com.scpakar.scpakarappws.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scpakar.scpakarappws.delegate.CuentaDelegate;
import com.scpakar.scpakarappws.dto.CabeceraCuponDTO;
import com.scpakar.scpakarappws.dto.PaqueteriaDTO;
import com.scpakar.scpakarappws.util.Configuracion;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("cuenta")
public class CuentaWS {

    private final GsonBuilder gb;
    private final Gson gson;
    private final CuentaDelegate delegate;

    public CuentaWS() {
        delegate = new CuentaDelegate();
        gb = new GsonBuilder();
        gb.setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT);
        gson = gb.create();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("cupones")
    public String getCupones(String id) {
        List<CabeceraCuponDTO> cupones = new ArrayList<>();
        Connection con = null;
        try {
            if (id != null && !id.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                cupones = delegate.getCupones(id, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(cupones);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("codigoPostal")
    public String validaCodigoPostal(@QueryParam("cp") String cp, @QueryParam("idSocio") String idSocio) {
        List<PaqueteriaDTO> datos = null;
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty() && cp != null && !cp.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                delegate.registraCP(con, idSocio, cp);
                datos = delegate.validaCodigoPostal(cp, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(datos);
    }
}
