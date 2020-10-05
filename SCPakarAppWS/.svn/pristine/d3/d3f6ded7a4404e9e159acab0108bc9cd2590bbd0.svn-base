package com.scpakar.scpakarappws.ws;

import com.scpakar.scpakarappws.delegate.AfiliacionDelegate;
import com.scpakar.scpakarappws.dto.AfiliacionDTO;
import com.scpakar.scpakarappws.util.Configuracion;
import java.sql.Connection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author pablo.martinez
 */
@Path("afiliacion")
public class AfiliacionWS {

    private final AfiliacionDelegate delegate;

    public AfiliacionWS() {
        delegate = new AfiliacionDelegate();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("afiliate")
    public String guardaAfiliacion(@QueryParam("nm") String nombre, @QueryParam("ap") String apellidos,
            @QueryParam("di") String direccion, @QueryParam("col") String colonia, @QueryParam("cp") String cp,
            @QueryParam("cd") String ciudad, @QueryParam("et") String estado, @QueryParam("ti") String tienda,
            @QueryParam("tel") String telefono, @QueryParam("cel") String celular, @QueryParam("crr") String correo,
            @QueryParam("cv") String estadoCivil, @QueryParam("est") String estudios, @QueryParam("dep") String dependientes,
            @QueryParam("ocup") String ocupacion, @QueryParam("ent") String enteraste, @QueryParam("rz") String razon,
            @QueryParam("ot") String otro, @QueryParam("cat") String catalogos) {
        boolean resultado = false;
        Connection connection = null;
        try {
            AfiliacionDTO af = new AfiliacionDTO();
            af.setNombre(nombre);
            af.setApellidos(apellidos);
            af.setDireccion(direccion);
            af.setColonia(colonia);
            af.setCp(cp);
            af.setCiudad(ciudad);
            af.setEstado(estado);
            af.setTienda(tienda);
            af.setTelefono(telefono);
            af.setCelular(celular);
            af.setCorreo(correo);
            af.setEstadoCivil(estadoCivil);
            af.setEstudios(estudios);
            af.setDependientes(dependientes);
            af.setOcupacion(ocupacion);
            af.setEnteraste(enteraste);
            af.setRazon(razon);
            af.setOtro(otro);
            af.setCatalogos(catalogos);

            connection = Factory.getConnection(Configuracion.getJndi());
            resultado = delegate.guardaAfiliacion(af, connection);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(connection);
        }
        return String.valueOf(resultado);
    }
}
