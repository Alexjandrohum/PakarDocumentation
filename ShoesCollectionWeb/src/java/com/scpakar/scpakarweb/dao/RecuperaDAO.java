package com.scpakar.scpakarweb.dao;

import com.scpakar.scpakarweb.dto.AfiliacionDTO;
import com.scpakar.scpakarweb.dto.ErrorDTO;
import com.scpakar.scpakarweb.dto.SocioDTO;
import com.scpakar.scpakarweb.util.FactoryWS;
import com.scpakar.scpakarweb.ws.WebService;
import java.io.IOException;
import mx.com.pakar.util.Util;
import retrofit2.Response;

/**
 *
 * @author pablo.martinez
 */
public class RecuperaDAO {

    public ErrorDTO recuperaPassword(String idSocio, String email) {
        ErrorDTO resultado = new ErrorDTO();
        try {
            WebService ws = FactoryWS.getWS();
            if (ws != null) {
                Response<ErrorDTO> response = ws.recuperaPassword(idSocio, email).execute();
                if (response.isSuccessful() && response.body() != null) {
                    resultado = response.body();
                } else {
                    Util.registraError(new Exception((response.message() == null) ? "" : response.message()));
                }
            }
        } catch (IOException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            FactoryWS.closeWebService();
        }
        return resultado;
    }

    public boolean actualizaPassword(String idSocio, String password, String passwordActual) {
        boolean resultado = false;
        try {
            WebService webService = FactoryWS.getWS();
            if (webService != null) {
                Response<String> response = webService.actualizaPassword(idSocio, password, passwordActual).execute();
                if (response.isSuccessful() && response.body() != null) {
                    resultado = Boolean.parseBoolean(response.body());
                } else {
                    Util.registraError(new Exception((response.message() == null) ? "Error inesperado" : response.message()));
                }
            }
        } catch (IOException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            FactoryWS.closeWebService();
        }
        return resultado;
    }
}
