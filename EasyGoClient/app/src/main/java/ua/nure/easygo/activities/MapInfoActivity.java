package ua.nure.easygo.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import easygo.nure.ua.easygoclient.R;
import easygo.nure.ua.easygoclient.databinding.ActivityMapInfoBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.easygo.Constants;
import ua.nure.easygo.adapters.MapAttributesAdapter;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.MapList;
import ua.nure.easygo.rest.EasyGoService;
import ua.nure.easygo.rest.RestService;

public class MapInfoActivity extends AppCompatActivity {

    public static final String EXTRA_MAP_ID = "map_id";
    EasyGoService service;
    boolean creating;
    Map map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMapInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_map_info);

        service = RestService.get();
        final int mapId = getIntent().getIntExtra(EXTRA_MAP_ID, Constants.ID_NONE);
        if (mapId != Constants.ID_NONE) {

            service.getMaps().enqueue(new Callback<MapList>() {
                @Override
                public void onResponse(Call<MapList> call, Response<MapList> response) {
                    RestService.mapList = response.body();
                    map = response.body().maps.get(mapId);
                    binding.setMap(map);
                    binding.listAttributes.setAdapter(new MapAttributesAdapter(MapInfoActivity.this, map.mapAttributes.attributes));
                }

                @Override
                public void onFailure(Call<MapList> call, Throwable t) {

                }
            });

        } else {
            creating = true;

            map = new Map();
            binding.setMap(map);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);

        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (creating) {
            service.getMaps().enqueue(new Callback<MapList>() {
                @Override
                public void onResponse(Call<MapList> call, Response<MapList> response) {
                    RestService.mapList = response.body();
                    map.mapId = response.body().maps.size();
                    response.body().maps.add(map);
                    RestService.save();
                }

                @Override
                public void onFailure(Call<MapList> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            //save
        }
        return super.onOptionsItemSelected(item);
    }
}
