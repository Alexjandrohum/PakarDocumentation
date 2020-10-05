package com.scpakar.scpakarappws.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scpakar.scpakarappws.delegate.PreguntaDelegate;
import com.scpakar.scpakarappws.dto.DatosPreguntasDTO;
import com.scpakar.scpakarappws.util.Configuracion;
import java.sql.Connection;
import java.text.DateFormat;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
@Path("preguntasFrecuentes")
public class PreguntasFrecuentesWS {

    private final GsonBuilder gb;
    private final Gson gson;
    private final PreguntaDelegate delegate;

    public PreguntasFrecuentesWS() {
        delegate = new PreguntaDelegate();
        gb = new GsonBuilder();
        gb.setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT);
        gson = gb.create();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getDatosPreguntas() {
        DatosPreguntasDTO datos = new DatosPreguntasDTO();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            datos = delegate.getDatosPreguntas(con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(datos);
    }
}
