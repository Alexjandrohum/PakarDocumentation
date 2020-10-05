package com.scpakar.scpakarweb.dao;

import com.scpakar.scpakarweb.dto.ContactoDTO;
import com.scpakar.scpakarweb.util.FactoryWS;
import com.scpakar.scpakarweb.ws.WebService;
import java.io.IOException;
import mx.com.pakar.util.Util;
import retrofit2.Response;

/**
 * @author Pablo Martinez
 */
@SuppressWarnings("CallToPrintStackTrace")
public class ContactoDAO {

    public boolean enviaContacto(ContactoDTO contacto) {
        boolean resultado = false;
        try {
            WebService webService = FactoryWS.getWS();
            if (webService != null) {
                Response<String> response = webService.enviaContacto(contacto.getNombre(), contacto.getCorreo(), contacto.getTelefono(), contacto.getCelular(), contacto.getMensaje()).execute();
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
            ex.printStackTrace();
            Util.registraError(ex);
        } finally {
            FactoryWS.closeWebService();
        }
        return resultado;
    }

}
