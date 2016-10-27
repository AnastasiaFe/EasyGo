package ua.nure.easygo.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import easygo.nure.ua.easygoclient.R;
import easygo.nure.ua.easygoclient.databinding.ActivityPointBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.Point;
import ua.nure.easygo.rest.EasyGoService;
import ua.nure.easygo.rest.RestService;

public class PointActivity extends AppCompatActivity {

    public static final String EXTRA_POINT_ID="point_id";
public static final int MASK_MAP = 0xffff0000;
    public static final int MASK_POINT=~MASK_MAP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityPointBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_point);


        int id = getIntent().getIntExtra(EXTRA_POINT_ID, 0);

        final int map=(id&MASK_MAP)>>>16, point=id&MASK_POINT;
        EasyGoService service = RestService.get();
        service.getMap(map).enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                Point p;
                p = response.body().getPoints().get(point);
                binding.setPoint(p);
                binding.setMap(response.body());
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {

            }
        });


    }
}
