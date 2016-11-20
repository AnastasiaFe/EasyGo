package ua.nure.easygo.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.google.android.gms.maps.model.LatLng;

import easygo.nure.ua.easygoclient.R;
import easygo.nure.ua.easygoclient.databinding.ActivityPointBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.easygo.MockUtil;
import ua.nure.easygo.adapters.PointAttrAdapter;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.MapList;
import ua.nure.easygo.model.Point;
import ua.nure.easygo.rest.EasyGoService;
import ua.nure.easygo.rest.RestService;

public class PointActivity extends AppCompatActivity {

    public static final String EXTRA_POINT_ID = "point_id", EXTRA_MAP_ID = "map_id", EXTRA_LOC = "location";


    Point p;
    Map m;
    EasyGoService service;
    boolean creating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityPointBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_point);
        service = RestService.get();

        int id = getIntent().getIntExtra(EXTRA_POINT_ID, -1);
        final int mapId;
        if (id >= 0) {

            final int map = MockUtil.getMapIndex(id), point = MockUtil.getPointIndex(id);

            mapId = map;
            service.getMaps().enqueue(new Callback<MapList>() {
                @Override
                public void onResponse(Call<MapList> call, Response<MapList> response) {

                    p = response.body().maps.get(map).points.get(point);
                    binding.setPoint(p);
                    m = response.body().maps.get(map);
                    binding.setMap(m);
                }

                @Override
                public void onFailure(Call<MapList> call, Throwable t) {

                }
            });
        } else {
            creating = true;
            p = new Point();
            LatLng location = getIntent().getParcelableExtra(EXTRA_LOC);
            p.x = (float) location.latitude;
            p.y = (float) location.longitude;
            binding.setPoint(p);
            mapId =
                    getIntent().getIntExtra(EXTRA_MAP_ID, 0);
            service.getMaps().enqueue(new Callback<MapList>() {
                @Override
                public void onResponse(Call<MapList> call, Response<MapList> response) {
                    RestService.mapList = response.body();
                    m = response.body().maps.get(mapId);
                    binding.setMap(m);

                }

                @Override
                public void onFailure(Call<MapList> call, Throwable t) {

                }
            });
        }

        final TableLayout table;
        table = (TableLayout) findViewById(R.id.attr_list);

        //if (p.attributeValues != null) {

        service.getMaps().enqueue(new Callback<MapList>() {
            @Override
            public void onResponse(Call<MapList> call, Response<MapList> response) {
                Map m = response.body().maps.get(mapId);


                PointAttrAdapter attrAdapter = new PointAttrAdapter(PointActivity.this, m.mapAttributes, p.attributeValues, table);
            }

            @Override
            public void onFailure(Call<MapList> call, Throwable t) {

            }
        });


        //!table.setAdapter(attrAdapter);
        //}

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (creating) {
            setResult(RESULT_OK);
            p.mapId = m.mapId;
            p.pointId = m.points.size();
            m.points.add(p);
        }
        RestService.save();
    }
}
