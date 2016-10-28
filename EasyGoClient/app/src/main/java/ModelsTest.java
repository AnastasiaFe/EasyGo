import android.databinding.tool.reflection.Callable;

import com.google.gson.Gson;

import ua.nure.easygo.model.Point;
import ua.nure.easygo.model.attributes.AttributeType;
import ua.nure.easygo.model.attributes.AttributeValue;
import ua.nure.easygo.model.attributes.MapAttribute;

/**
 * Created by Oleg on 28.10.2016.
 */

public class ModelsTest {
    public static void main(String[] args) {
        Gson g = new Gson();
        Object o;

        o = new AttributeValue(5, "3.423");

        System.out.printf(g.toJson(o));
    }
}
