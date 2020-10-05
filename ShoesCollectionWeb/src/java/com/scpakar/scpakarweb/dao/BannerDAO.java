package com.scpakar.scpakarweb.dao;

import com.scpakar.scpakarweb.dto.DestacadoDTO;
import com.scpakar.scpakarweb.util.FactoryWS;
import com.scpakar.scpakarweb.ws.WebService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mx.com.pakar.util.Util;
import retrofit2.Response;

/**
 *
 * @author pablo.martinez
 */
@SuppressWarnings("CallToPrintStackTrace")
public class BannerDAO {

    public List<DestacadoDTO> selectBannerActivo() {
        List<DestacadoDTO> banner = new ArrayList<>();

        try {
            WebService webService = FactoryWS.getWS();
            if (webService != null) {
                Response<List<DestacadoDTO>> response = webService.getDestacados().execute();
                if (response.isSuccessful() && response.body() != null) {
                    banner = response.body();
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
        return banner;
    }

}
