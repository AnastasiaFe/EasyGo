import com.google.gson.Gson;

/**
 * Created by Oleg on 28.10.2016.
 */

public class ModelsTest {
    public static void main(String[] args) {
        final Gson g = new Gson();
        Object o = new long[]{43, 324, 43};
        System.out.println(g.toJson(o));
    }
}
