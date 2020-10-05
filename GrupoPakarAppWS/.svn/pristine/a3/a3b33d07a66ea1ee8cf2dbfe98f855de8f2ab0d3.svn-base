package com.grupopakar.grupopakarappws.ws;

import com.grupopakar.grupopakarappws.delegate.PlantillaDelegate;
import com.grupopakar.grupopakarappws.dto.EstadoDTO;
import com.grupopakar.grupopakarappws.dto.MensajeDTO;
import com.grupopakar.grupopakarappws.dto.ValidaPlantillaDTO;
import com.grupopakar.grupopakarappws.util.Configuracion;
import com.grupopakar.grupopakarappws.util.EstadoHTTPEnum;
import com.grupopakar.grupopakarappws.util.GsonUtil;
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
 * @author pablo.martinez
 */
@Path("plantilla")
public class PlantillaWS {

    private final PlantillaDelegate delegate;

    public PlantillaWS() {
        this.delegate = new PlantillaDelegate();
    }

    /**
     * Obtiene plantilla de tienda por medio de una petición GET
     *
     * @param tokenUsuario token de la sesión de usuario
     * @param idTienda Identificador de tienda de la cual se quiere obtener la
     * plantilla
     * @return <code>Response</code> con el resultado de la petición y el
     * contenido de la plantilla.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @SuppressWarnings("null")
    public Response getPlantilla(@HeaderParam("tokenDeUsuario") String tokenUsuario, @QueryParam("idTienda") int idTienda) {
        EstadoDTO estado = null;
        Connection connection = null;
        try {
            estado = new EstadoDTO();
            connection = Factory.getConnection(Configuracion.getJndi());
            if (connection != null) {
                estado = delegate.getPlantillaTienda(connection, tokenUsuario, idTienda);
            } else {
                estado.setEstado(EstadoHTTPEnum.NO_DISPONIBLE);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Conexión no disponible")));
            }
        } catch (Exception e) {
            Util.registraError(e);
        } finally {
            Factory.close(connection);
        }
        return estado.getResponse();
    }

    /**
     * Registra una petición de validación de plantilla con respecto a un
     * empleado.
     *
     * @param tokenUsuario token de la sesión de usuario
     * @param validacion Objeto con los datos de la solicitud de validación.
     * @return <code>Response</code> con el resultado de la petición de
     * validación.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("validar")
    @SuppressWarnings("null")
    public Response validaPlantilla(@HeaderParam("tokenDeUsuario") String tokenUsuario, ValidaPlantillaDTO validacion) {
        EstadoDTO estado = new EstadoDTO();
        Connection connection = null;
        //System.out.println("Dato: " + validacion);
        try {
            connection = Factory.getConnection(Configuracion.getJndi());
            if (connection != null) {
                estado = delegate.validaPlantilla(connection, tokenUsuario, validacion);
            } else {
                estado.setEstado(EstadoHTTPEnum.NO_DISPONIBLE);
                estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Conexión no disponible")));
            }
        } catch (Exception e) {
            Util.registraError(e);
        } finally {
            Factory.close(connection);
        }
        return estado.getResponse();
    }

}
