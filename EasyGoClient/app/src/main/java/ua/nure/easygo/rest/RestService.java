package ua.nure.easygo.rest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.nure.easygo.model.MapList;

/**
 * Created by Oleg on 27.10.2016.
 */

public class RestService {

    private final static boolean USE_MOCK = true;

    private static EasyGoService service;

    public synchronized static EasyGoService get() {
        if (service == null) {
            if (USE_MOCK) {
                service = new MockService();
            } else {
                //TODO: replace url
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://192.168.43.13:8080/").build();
                service = retrofit.create(EasyGoService.class);
            }
        }
        return service;
    }

    public static MapList mapList;

    public static void save() {
        get().saveMaps(mapList).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
