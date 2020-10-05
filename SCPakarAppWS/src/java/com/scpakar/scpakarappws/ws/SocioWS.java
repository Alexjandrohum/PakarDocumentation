package com.scpakar.scpakarappws.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scpakar.scpakarappws.delegate.SocioDelegate;
import com.scpakar.scpakarappws.dto.AppOrigen;
import com.scpakar.scpakarappws.dto.ClienteDTO;
import com.scpakar.scpakarappws.dto.ErrorDTO;
import com.scpakar.scpakarappws.dto.SocioDTO;
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
@Path("socio")
public class SocioWS {

    private final GsonBuilder gb;
    private final Gson gson;
    private final SocioDelegate delegate;

    public SocioWS() {
        delegate = new SocioDelegate();
        gb = new GsonBuilder();
        gb.setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT);
        gson = gb.create();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("nuevoCliente")
    public String nuevoCliente(@QueryParam("ids") String idSocio,
            @QueryParam("nom") String nombre,
            @QueryParam("prc") int porcentaje,
            @QueryParam("tel") String telefono) {
        ErrorDTO error = new ErrorDTO();
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                error = delegate.nuevoCliente(nombre, porcentaje, idSocio, telefono, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(error);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getClientesSocio/{idSocio}")
    public String getClientesSocio(@PathParam("idSocio") String idSocio) {
        List<ClienteDTO> clientes = new ArrayList<>();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            clientes = delegate.getClientesSocio(idSocio, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(clientes);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("registraToken")
    public String registraTokenSocio(@QueryParam("id") String idSocio,
            @QueryParam("tk") String token) {
        boolean resultado = false;
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            resultado = delegate.registraTokenSocio(idSocio, token, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(resultado);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("validaSocio/{idSocio}")
    public String validaSocio(@PathParam("idSocio") String idSocio) {
        SocioDTO socio = new SocioDTO();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            socio = delegate.validaSocio(idSocio, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(socio);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("modificarCliente")
    public String modificarCliente(@QueryParam("idc") String idCliente,
            @QueryParam("nm") String nombre,
            @QueryParam("pc") int porcentaje,
            @QueryParam("ids") String idSocio,
            @QueryParam("tel") String telefono,
            @QueryParam("act") boolean activo) {
        ErrorDTO error = new ErrorDTO();
        Connection con = null;
        try {
            if (idCliente != null && !idCliente.isEmpty()
                    && idSocio != null && !idSocio.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                error = delegate.modificarCliente(idCliente, nombre, porcentaje, idSocio, telefono, activo, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(error);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("validaVersion")
    public String validaVersion(@QueryParam("ve") String version,
            @QueryParam("or") int idOrigen) {
        boolean resultado = false;
        Connection con = null;
        try {
            if (version != null && !version.isEmpty()) {
                AppOrigen origen = AppOrigen.getOrigen(idOrigen);
                con = Factory.getConnection(Configuracion.getJndi());
                resultado = delegate.validaVersion(version, origen, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(resultado);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscaSocioActivo")
    public String buscaSocioActivo(@QueryParam("id") String idSocio) {
        SocioDTO socio = null;
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                socio = delegate.buscaSocioActivo(idSocio, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(socio);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("opcionesCancelar")
    public String getOpcionesCancelar(@QueryParam("origen") int origen) {
        List<String> opciones = new ArrayList<>();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            opciones = delegate.getOpcionesCancelar(con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(opciones);
    }
}
