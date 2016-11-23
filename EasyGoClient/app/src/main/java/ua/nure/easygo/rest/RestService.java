package ua.nure.easygo.rest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Oleg on 27.10.2016.
 */

public class RestService {

    private final static boolean USE_MOCK = false;
    private static String token = "";
    private static EasyGoService service;

    public synchronized static EasyGoService get() {
        if (service == null) {
            if (USE_MOCK) {
                // service = new MockService();
            } else {
                //TODO: replace url
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request().newBuilder()
                                        .addHeader("Token", token).build();
                                return chain.proceed(request);
                            }
                        })
                        .build();


                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .baseUrl("http://192.168.43.13:8080/").build();

                service = retrofit.create(EasyGoService.class);
            }
        }
        return service;
    }
}
