package com.scpakar.scpakarweb.dao;

import com.scpakar.scpakarweb.dto.AppOrigen;
import com.scpakar.scpakarweb.dto.CatalogoDTO;
import com.scpakar.scpakarweb.dto.CorridaDTO;
import com.scpakar.scpakarweb.dto.ProductoDTO;
import com.scpakar.scpakarweb.dto.SeccionDTO;
import com.scpakar.scpakarweb.dto.TallaDTO;
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
public class CatalogoDAO {

    public List<CatalogoDTO> selectCatalogos() {
        List<CatalogoDTO> catalogos = new ArrayList<>();

        try {
            WebService ws = FactoryWS.getWS();
            if (ws != null) {
                Response<List<CatalogoDTO>> response = ws.getCatalogos(AppOrigen.WEB.getIdOrigen()).execute();
                if (response.isSuccessful() && response.body() != null) {
                    catalogos = response.body();
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
        return catalogos;
    }

    public List<SeccionDTO> selectSeccion(int idCatalogo) {
        List<SeccionDTO> seccion = new ArrayList<>();

        try {
            WebService ws = FactoryWS.getWS();
            if (ws != null) {
                Response<List<SeccionDTO>> response = ws.getSecciones(idCatalogo).execute();
                if (response.isSuccessful() && response.body() != null) {
                    seccion = response.body();
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
        return seccion;
    }

    public List<ProductoDTO> selectProductoCatalogo(int idCatalogo, int paginaInicial, int paginaFinal, String idSocio) {
        List<ProductoDTO> productos = new ArrayList<>();

        try {
            WebService ws = FactoryWS.getWS();
            if (ws != null) {
                Response<List<ProductoDTO>> response = ws.getProductos(idCatalogo, paginaInicial, paginaFinal, idSocio).execute();
                if (response.isSuccessful() && response.body() != null) {
                    productos = response.body();
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
        return productos;
    }

    public List<TallaDTO> selectTallasProducto(String Llave, String idSocio) {
        List<TallaDTO> tallas = new ArrayList<>();

        try {
            WebService ws = FactoryWS.getWS();
            if (ws != null) {
                Response<List<TallaDTO>> response = ws.getTallasProducto(Llave, idSocio).execute();
                if (response.isSuccessful() && response.body() != null) {
                    tallas = response.body();
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
        return tallas;
    }

    public List<CorridaDTO> selectCorridaProducto(String codigo, String idSocio) {
        List<CorridaDTO> corridas = new ArrayList<>();

        try {
            WebService ws = FactoryWS.getWS();
            if (ws != null) {
                Response<List<CorridaDTO>> response = ws.getCorridaProducto(codigo, idSocio).execute();
                if (response.isSuccessful() && response.body() != null) {
                    corridas = response.body();
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
        return corridas;
    }

}
