package com.scpakar.scpakarweb.dao;

import com.scpakar.scpakarweb.util.Configuracion;
import com.scpakar.scpakarweb.util.FactoryWS;
import com.scpakar.scpakarweb.ws.WebService;
import java.io.IOException;
import mx.com.pakar.util.Factory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.com.pakar.util.Util;
import retrofit2.Response;

/**
 *
 * @author nicolas.canaan
 */
@SuppressWarnings("CallToPrintStackTrace")
public class PropiedadDAO {

    public String selectPropiedad(String clavePropiedad) {
        String valorPropiedad = "";
        try {
            WebService webService = FactoryWS.getWS();
            if (webService != null) {
                Response<String> response = webService.selectPropiedad(clavePropiedad).execute();
                if (response.isSuccessful() && response.body() != null) {
                    valorPropiedad = response.body();
                } else {
                    Util.registraError(new Exception((response.message() == null) ? "Error inesperado" : response.message()));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            FactoryWS.closeWebService();
        }
        return valorPropiedad;
    }

    public boolean updatePropiedad(String clave, String valor) {
        boolean ok = false;
        try {
            WebService webService = FactoryWS.getWS();
            if (webService != null) {
                Response<String> response = webService.updatePropiedad(clave, valor).execute();
                if (response.isSuccessful() && response.body() != null) {
                    ok = Boolean.parseBoolean(response.body());
                } else {
                    Util.registraError(new Exception((response.message() == null) ? "Error inesperado" : response.message()));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            FactoryWS.closeWebService();
        }
        return ok;
    }

}
