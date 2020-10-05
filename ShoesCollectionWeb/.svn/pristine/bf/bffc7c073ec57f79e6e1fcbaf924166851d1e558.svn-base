package com.scpakar.scpakarweb.ws;

import com.scpakar.scpakarweb.util.Constante;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 *
 * @author pablo.martinez
 */
public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(Constante.TIME_OUT_CONNECT_HTTP, TimeUnit.MILLISECONDS)
                    .readTimeout(Constante.TIME_OUT_READ_HTTP, TimeUnit.MILLISECONDS)
                    .writeTimeout(Constante.TIME_OUT_READ_HTTP, TimeUnit.MILLISECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    public static void destroyClient() {
        retrofit = null;
    }
}
