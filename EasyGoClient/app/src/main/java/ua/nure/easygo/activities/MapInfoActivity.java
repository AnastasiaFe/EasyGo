package ua.nure.easygo.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import ua.nure.easygo.rest.EasyGoService;
import ua.nure.easygo.rest.RestService;

public class MapInfoActivity extends AppCompatActivity {

    public static final String EXTRA_MAP_ID = "map_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMapInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_map_info);

        final int mapId = getIntent().getIntExtra(EXTRA_MAP_ID, Constants.ID_NONE);
        if (mapId != Constants.ID_NONE) {
            RestService.get().getMap(mapId).enqueue(new Callback<Map>() {
                @Override
                public void onResponse(Call<Map> call, Response<Map> response) {
                    Map map = response.body();
                    binding.setMap(map);
                    binding.listAttributes.setAdapter(new MapAttributesAdapter(MapInfoActivity.this, map.mapAttributes.attributes));
                }

                @Override
                public void onFailure(Call<Map> call, Throwable t) {

                }
            });

        } else {
            Map map = new Map();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            //save
        }
        return super.onOptionsItemSelected(item);
    }
}
