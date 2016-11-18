package ua.nure.easygo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import easygo.nure.ua.easygoclient.BR;
import easygo.nure.ua.easygoclient.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.easygo.adapters.BaseBindableAdapter;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.MapList;
import ua.nure.easygo.rest.EasyGoService;
import ua.nure.easygo.rest.RestService;

public class MapsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final String EXTRA_MAP_ID = "map_id";
    public static final int REQUEST_CREATE_MAP = 1;
    EasyGoService service;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        listView = (ListView) findViewById(R.id.list_maps);

        service = RestService.get();

        service.getMaps().enqueue(new Callback<MapList>() {
            @Override
            public void onResponse(Call<MapList> call, Response<MapList> response) {
                RestService.mapList = response.body();
                listView.setAdapter(new BaseBindableAdapter<Map>(MapsActivity.this, response.body().maps, R.layout.map_item, BR.map));
            }

            @Override
            public void onFailure(Call<MapList> call, Throwable t) {
//TODO: Add error handling
            }
        });

        listView.setOnItemClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Maps list");
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent res = new Intent();
        res.putExtra(EXTRA_MAP_ID, position);
        setResult(RESULT_OK, res);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CREATE_MAP) {
            service.getMaps().enqueue(new Callback<MapList>() {
                @Override
                public void onResponse(Call<MapList> call, Response<MapList> response) {
                    RestService.mapList = response.body();
                    listView.setAdapter(new BaseBindableAdapter<Map>(MapsActivity.this, response.body().maps, R.layout.map_item, BR.map));
                }

                @Override
                public void onFailure(Call<MapList> call, Throwable t) {
                    //TODO: Add error handling
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, MapInfoActivity.class);
            startActivityForResult(intent, REQUEST_CREATE_MAP);
        }
        return super.onOptionsItemSelected(item);
    }
}
