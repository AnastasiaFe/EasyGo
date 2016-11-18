import android.databinding.tool.reflection.Callable;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.easygo.model.MapList;
import ua.nure.easygo.model.Point;
import ua.nure.easygo.model.attributes.AttributeType;
import ua.nure.easygo.model.attributes.AttributeValue;
import ua.nure.easygo.model.attributes.MapAttribute;
import ua.nure.easygo.rest.MockService;
import ua.nure.easygo.rest.RestService;

/**
 * Created by Oleg on 28.10.2016.
 */

public class ModelsTest {
    public static void main(String[] args) {
        final Gson g = new Gson();
        Object o;

        ((MockService)RestService.get()).getMaps().enqueue(new Callback<MapList>() {
            @Override
            public void onResponse(Call<MapList> call, Response<MapList> response) {
                System.out.printf(g.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<MapList> call, Throwable t) {

            }
        });


    }
}
