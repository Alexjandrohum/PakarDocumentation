package com.scpakar.scpakarweb.dao;

import com.scpakar.scpakarweb.dto.EstadoDTO;
import com.scpakar.scpakarweb.dto.Pais;
import com.scpakar.scpakarweb.dto.SucursalDTO;
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
public class AlmacenDAO {

    public List<EstadoDTO> selectEstadosMX() {
        List<EstadoDTO> estados = new ArrayList<>();

        try {
            WebService webService = FactoryWS.getWS();
            if (webService != null) {
                Response<List<EstadoDTO>> response = webService.getEstadoSucursales(Pais.MX.getIdPais()).execute();
                if (response.isSuccessful() && response.body() != null) {
                    estados = response.body();
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
        return estados;
    }

    public List<SucursalDTO> selectAlmacenEstado(String IdEstado, List<EstadoDTO> estados) {
        List<SucursalDTO> almacenes = new ArrayList<>();

        for (EstadoDTO e : estados) {
            if (e.getNombreEstado().equals(IdEstado)) {
                almacenes = e.getSucursales();
                break;
            }
        }
        return almacenes;
    }
}
