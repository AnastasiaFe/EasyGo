package ua.nure.easygo.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.MapList;

/**
 * Created by Oleg on 27.10.2016.
 */

public interface EasyGoService {
    @GET("maps")
    Call<MapList> getMaps();

    @GET("maps/{map}")
    Call<Map> getMap(@Path("map") long mapId);
}
