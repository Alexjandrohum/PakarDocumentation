package com.scpakar.scpakarweb.dao;

import com.scpakar.scpakarweb.dto.ActivacionDTO;
import com.scpakar.scpakarweb.dto.ErrorDTO;
import com.scpakar.scpakarweb.dto.SocioDTO;
import com.scpakar.scpakarweb.util.AESCrypt;
import com.scpakar.scpakarweb.util.FactoryWS;
import com.scpakar.scpakarweb.ws.WebService;
import java.io.IOException;
import mx.com.pakar.util.Util;
import retrofit2.Response;

/**
 *
 * @author pablo.martinez
 */
public class AccesoDAO {

    public SocioDTO validaLogin(String idUsuario, String password) {
        SocioDTO socio = new SocioDTO();
        try {
            WebService ws = FactoryWS.getWS();
            if (ws != null) {
                Response<SocioDTO> response = ws.login(idUsuario, password).execute();
                if (response.isSuccessful() && response.body() != null) {
                    socio = response.body();
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
        return socio;
    }
    
    public SocioDTO validaCorreo(String correo) {
        SocioDTO socio = new SocioDTO();
        try {
            WebService ws = FactoryWS.getWS();
            if (ws != null) {
                Response<SocioDTO> response = ws.validaCorreo(correo).execute();
                if (response.isSuccessful() && response.body() != null) {
                    socio = response.body();
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
        return socio;
    }

    public ErrorDTO activaSocio(ActivacionDTO activacion) {
        ErrorDTO resultado = new ErrorDTO();
        try {
            WebService ws = FactoryWS.getWS();
            if (ws != null) {
                Response<ErrorDTO> response = ws.activaSocio(activacion.getCorreo(), activacion.getIdSocio(), AESCrypt.encryptMD5(activacion.getPassword()),
                        activacion.getCodigo(), activacion.getSerie(), activacion.isSobreEscribe(), activacion.getOrigen()).execute();
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
}
