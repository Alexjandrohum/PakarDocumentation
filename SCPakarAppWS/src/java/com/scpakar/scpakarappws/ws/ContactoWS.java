package com.scpakar.scpakarappws.ws;

import com.scpakar.scpakarappws.delegate.ContactoDelegate;
import com.scpakar.scpakarappws.dto.ContactoDTO;
import com.scpakar.scpakarappws.util.Configuracion;
import java.sql.Connection;
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
@Path("contacto")
public class ContactoWS {

    private final ContactoDelegate delegate;

    public ContactoWS() {
        delegate = new ContactoDelegate();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("enviaContacto")
    public String enviaContacto(@QueryParam("nm") String nombre, @QueryParam("crr") String correo,
            @QueryParam("tel") String telefono, @QueryParam("cel") String celular, @QueryParam("msg") String mensaje) {
        boolean resultado = false;
        Connection connection = null;
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            ContactoDTO dto = new ContactoDTO(nombre, correo, telefono, celular, mensaje);
            resultado = delegate.enviaCorreoContacto(dto, connection);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return String.valueOf(resultado);
    }

}
