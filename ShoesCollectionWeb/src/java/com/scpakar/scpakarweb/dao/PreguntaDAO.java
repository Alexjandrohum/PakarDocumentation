package com.scpakar.scpakarweb.dao;

import com.scpakar.scpakarweb.dto.DatosPreguntasDTO;
import com.scpakar.scpakarweb.util.FactoryWS;
import com.scpakar.scpakarweb.ws.WebService;
import java.io.IOException;
import mx.com.pakar.util.Util;
import retrofit2.Response;

/**
 *
 * @author pablo.martinez
 */
@SuppressWarnings("CallToPrintStackTrace")
public class PreguntaDAO {

    public DatosPreguntasDTO getDatosPreguntas() {
        DatosPreguntasDTO datos = new DatosPreguntasDTO();
        try {
            WebService webService = FactoryWS.getWS();
            if (webService != null) {
                Response<DatosPreguntasDTO> response = webService.getDatosPreguntas().execute();
                if (response.isSuccessful() && response.body() != null) {
                    datos = response.body();
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
        return datos;
    }
}
