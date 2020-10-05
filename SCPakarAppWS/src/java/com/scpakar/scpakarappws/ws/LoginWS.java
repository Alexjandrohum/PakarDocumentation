package com.scpakar.scpakarappws.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scpakar.scpakarappws.delegate.LoginDelegate;
import com.scpakar.scpakarappws.dto.ErrorDTO;
import com.scpakar.scpakarappws.dto.SocioDTO;
import com.scpakar.scpakarappws.util.Configuracion;
import java.sql.Connection;
import java.text.DateFormat;
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
@Path("login")
public class LoginWS {

    private final GsonBuilder gb;
    private final Gson gson;
    private final LoginDelegate delegate;

    public LoginWS() {
        delegate = new LoginDelegate();
        gb = new GsonBuilder();
        gb.setDateFormat(DateFormat.MEDIUM, DateFormat.SHORT);
        gson = gb.create();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@QueryParam("us") String usuario, @QueryParam("ps") String password) {
        SocioDTO socio = new SocioDTO();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            socio = delegate.login(usuario, password, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(socio);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("validaCorreo")
    public String validaCorreo(@QueryParam("em") String email) {
        SocioDTO socio = new SocioDTO();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            socio = delegate.validaCorreo(email, con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(socio);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("activaSocio")
    public String activaSocio(@QueryParam("em") String email,
            @QueryParam("id") String idSocio, @QueryParam("pw") String password,
            @QueryParam("code") String codigo, @QueryParam("se") String serie,
            @QueryParam("re") boolean reEmail, @QueryParam("or") int origen) {
        ErrorDTO registro = new ErrorDTO();
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty() && email != null && !email.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                registro = delegate.activaSocio(email, idSocio, password, codigo, serie, reEmail, origen, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(registro);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("selectPaginaAfiliate")
    public String selectPaginaAfiliate() {
        String url = "";
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            url = delegate.selectPaginaAfiliate(con);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return url;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("recuperaPassword")
    public String recuperaPassword(@QueryParam("id") String idSocio, @QueryParam("cr") String email) {
        ErrorDTO resultado = new ErrorDTO();
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty() && email != null && !email.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                resultado = delegate.recuperaPassword(idSocio, email, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return gson.toJson(resultado);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("actualizaPassword/{id}")
    public String actualizaPassword(@PathParam("id") String idSocio,
            @QueryParam("pw") String password, @QueryParam("pwa") String passwordActual) {
        boolean resultado = false;
        Connection con = null;
        try {
            if (idSocio != null && !idSocio.isEmpty() && password != null && !password.isEmpty()
                    && passwordActual != null && !passwordActual.isEmpty()) {
                con = Factory.getConnection(Configuracion.getJndi());
                resultado = delegate.actualizaPassword(idSocio, password, passwordActual, con);
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
        return String.valueOf(resultado);
    }
}
