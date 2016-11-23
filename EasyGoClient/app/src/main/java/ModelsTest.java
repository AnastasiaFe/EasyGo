import com.google.gson.Gson;

import java.sql.Array;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import ua.nure.easygo.model.attributes.AttributeType;
import ua.nure.easygo.model.attributes.MapAttribute;
import ua.nure.easygo.rest.RestService;

/**
 * Created by Oleg on 28.10.2016.
 */

public class ModelsTest {
    public static void main(String[] args) {
        final Gson g = new Gson();
        Object o = Arrays.asList(new MapAttribute("name", AttributeType.STRING));
        System.out.println(g.toJson(o));
    }
}
