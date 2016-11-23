package ua.nure.easygo.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;

import easygo.nure.ua.easygoclient.R;
import easygo.nure.ua.easygoclient.databinding.ActivityMapInfoBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.easygo.Constants;
import ua.nure.easygo.adapters.MapAttributesAdapter;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.attributes.AttributeType;
import ua.nure.easygo.model.attributes.MapAttribute;
import ua.nure.easygo.rest.EasyGoService;
import ua.nure.easygo.rest.RestService;

public class MapInfoActivity extends AppCompatActivity {

    public static final String EXTRA_MAP_ID = "map_id";
    EasyGoService service;


    ListAdapter adapter;

    ActivityMapInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_map_info);

        service = RestService.get();
        final int mapId = getIntent().getIntExtra(EXTRA_MAP_ID, Constants.ID_NONE);
        if (mapId != Constants.ID_NONE) {

            service.getMap(mapId).enqueue(new Callback<Map>() {
                @Override
                public void onResponse(Call<Map> call, Response<Map> response) {
                    binding.setMap(response.body());

                    adapter = new MapAttributesAdapter(MapInfoActivity.this, response.body().mapAttributes);
                    binding.listAttributes.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<Map> call, Throwable t) {

                }
            });

        } else {
            binding.setMap(new Map());
            adapter = new MapAttributesAdapter(MapInfoActivity.this, binding.getMap().mapAttributes);
            binding.listAttributes.setAdapter(adapter);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        binding.buttonAddAttribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.getMap().mapAttributes.add(new MapAttribute("new", AttributeType.values()[(int) (Math.random() * AttributeType.values().length)]));
                binding.listAttributes.invalidateViews();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            service.postMap(binding.getMap()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}