package ua.nure.easygo.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.google.android.gms.maps.model.LatLng;

import easygo.nure.ua.easygoclient.R;
import easygo.nure.ua.easygoclient.databinding.ActivityPointBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.easygo.adapters.PointAttrAdapter;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.Point;
import ua.nure.easygo.rest.EasyGoService;
import ua.nure.easygo.rest.RestService;

public class PointActivity extends BaseActivity {

    public static final String EXTRA_POINT_ID = "point_id", EXTRA_MAP_ID = "map_id", EXTRA_LOC = "location";


    EasyGoService service;

    ActivityPointBinding binding;

    boolean editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editing = RestService.authorised(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_point);
        final TableLayout table;
        table = (TableLayout) findViewById(R.id.attr_list);

        table.setEnabled(editing);
        binding.textName.setEnabled(editing);


        service = RestService.get();

        long id = getIntent().getLongExtra(EXTRA_POINT_ID, -1);

        if (id > 0) {

            service.getPoint(id).enqueue(new Callback<Point>() {
                @Override
                public void onResponse(Call<Point> call, Response<Point> response) {

                    binding.setPoint(response.body());
                    service.getMap(response.body().mapId).enqueue(new Callback<Map>() {
                        @Override
                        public void onResponse(Call<Map> call, Response<Map> response) {
                            binding.setMap(response.body());
                            PointAttrAdapter attrAdapter = new PointAttrAdapter(PointActivity.this, response.body().mapAttributes.attributes, binding.getPoint().attributeValues, table, !editing);
                        }

                        @Override
                        public void onFailure(Call<Map> call, Throwable t) {

                        }
                    });

                }

                @Override
                public void onFailure(Call<Point> call, Throwable t) {

                }
            });
        } else {
            final Point p = new Point();
            LatLng location = getIntent().getParcelableExtra(EXTRA_LOC);

            p.x = (float) location.latitude;
            p.y = (float) location.longitude;
            binding.setPoint(p);
            long mapId =
                    getIntent().getLongExtra(EXTRA_MAP_ID, 0);
            service.getMap(mapId).enqueue(new Callback<Map>() {
                @Override
                public void onResponse(Call<Map> call, Response<Map> response) {


                    binding.setMap(response.body());
                    p.mapId = response.body().mapId;
                    PointAttrAdapter attrAdapter = new PointAttrAdapter(PointActivity.this, response.body().mapAttributes.attributes, binding.getPoint().attributeValues, table, !editing);
                }

                @Override
                public void onFailure(Call<Map> call, Throwable t) {

                }
            });
        }


        setSupportActionBar(binding.toolbar2);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            RestService.get().postPoint(binding.getPoint()).enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    finish();
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    finish();
                }
            });

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPause() {
        super.onPause();


        setResult(RESULT_OK);

    }
}
