package com.grupopakar.grupopakarappws.ws;

import com.grupopakar.grupopakarappws.delegate.LoginDelegate;
import com.grupopakar.grupopakarappws.dto.CredencialesDTO;
import com.grupopakar.grupopakarappws.dto.EstadoDTO;
import com.grupopakar.grupopakarappws.util.Configuracion;
import java.sql.Connection;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author antonio.ruiz
 */
@Path("login")
public class LoginWS {

    @POST   
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(CredencialesDTO infoAcceso) {
        EstadoDTO estado = new EstadoDTO();
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            System.out.println(infoAcceso);
            estado = new LoginDelegate().loginUsuario(infoAcceso.getUsuario(), infoAcceso.getPassword(), con);
        } catch (Exception ex) {
            Util.registraError(ex);
        }finally{
            Factory.close(con);
        }
        return estado.getResponse();
    }
    
}