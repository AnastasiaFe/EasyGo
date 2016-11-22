package ua.nure.easygo.rest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.MapList;
import ua.nure.easygo.model.User;

/**
 * Created by Oleg on 21.11.2016.
 */

public interface RealService {
    /**
     * @param query search query to find a map
     * @return list of all maps without any points and attributes. Only icon and basic info
     */
    @GET("maps")
    Call<MapList> getMaps(String query);

    /**
     * @param id
     * @return full map with points, attributes etc.
     */
    @GET("maps/{id}")
    Call<Map> getMap(@Path("id") int id);


    /**
     * Adds new map or updates existing one
     *
     * @param map
     * @return
     */
    @POST("maps")
    Call<Integer> addOrUpdateMap(@Body Map map);


    /**
     * @param login
     * @return user info
     */
    @GET("users/{login}")
    Call<User> getUser(@Path("login") String login);

}
