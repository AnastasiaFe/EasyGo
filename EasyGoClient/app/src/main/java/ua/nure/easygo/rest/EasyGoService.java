package ua.nure.easygo.rest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ua.nure.easygo.model.MapList;
import ua.nure.easygo.model.User;

/**
 * Created by Oleg on 27.10.2016.
 */

public interface EasyGoService {
    @GET("api/")
    Call<MapList> getMaps();

    @POST("api/")
    Call<String> saveMaps(@Body MapList mapList);

    @GET("users/{login}")
    Call<User> getUser(@Path("login") String login);

}
