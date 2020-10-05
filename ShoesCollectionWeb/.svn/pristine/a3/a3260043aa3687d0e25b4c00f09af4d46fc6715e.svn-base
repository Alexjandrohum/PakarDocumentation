package com.scpakar.scpakarweb.util;

import com.scpakar.scpakarweb.ws.RetrofitClient;
import com.scpakar.scpakarweb.ws.WebService;

/**
 *
 * @author pablo.martinez
 */
public class FactoryWS {

    /**
     * Cliente de WebService
     *
     * @return instancia de cliente Retrofit.
     */
    public static WebService getWS() {
        return RetrofitClient.getClient(Constante.BASE_URL).create(WebService.class);
    }

    public static void closeWebService() {
        RetrofitClient.destroyClient();
    }
}
