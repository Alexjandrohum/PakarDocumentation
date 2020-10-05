package com.grupopakar.grupopakarappws.ws;

import com.grupopakar.grupopakarappws.delegate.DispositivoDelegate;
import com.grupopakar.grupopakarappws.util.Configuracion;
import java.sql.Connection;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author pepe.ruiz
 */
@Path("dispositivo")
public class DispositivoWS {

    @POST
    public void registraToken(@HeaderParam("token") String tokenUsuario, String tokenFirebase) {
        Connection con = null;
        try {
            con = Factory.getConnection(Configuracion.getJndi());
            new DispositivoDelegate().registraToken(con, tokenUsuario, tokenFirebase);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(con);
        }
    }

}
