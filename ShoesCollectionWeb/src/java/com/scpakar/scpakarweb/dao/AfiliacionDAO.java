package com.scpakar.scpakarweb.dao;

import com.scpakar.scpakarweb.dto.AfiliacionDTO;
import com.scpakar.scpakarweb.util.FactoryWS;
import com.scpakar.scpakarweb.ws.WebService;
import java.io.IOException;
import mx.com.pakar.util.Util;
import retrofit2.Response;

/**
 *
 * @author nicolas.canaan
 */
@SuppressWarnings("CallToPrintStackTrace")
public class AfiliacionDAO {

    public boolean guardaAfiliacion(AfiliacionDTO af) {
        boolean resultado = false;
        try {
            WebService webService = FactoryWS.getWS();
            if (webService != null) {
                Response<String> response = webService.guardaAfiliacion(af.getNombre(), af.getApellidos(), af.getDireccion(), af.getColonia(), af.getCp(),
                        af.getCiudad(), af.getEstado(), af.getTienda(), af.getTelefono(), af.getCelular(), af.getCorreo(), af.getEstadoCivil(), af.getEstudios(),
                        af.getDependientes(), af.getOcupacion(), af.getEnteraste(), af.getRazon(), af.getOtro(), af.getCatalogos()).execute();
                if (response.isSuccessful() && response.body() != null) {
                    resultado = Boolean.parseBoolean(response.body());
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
        return resultado;
    }

}
