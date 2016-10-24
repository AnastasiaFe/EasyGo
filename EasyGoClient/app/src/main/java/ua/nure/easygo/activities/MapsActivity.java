package ua.nure.easygo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import easygo.nure.ua.easygoclient.BR;
import easygo.nure.ua.easygoclient.R;
import ua.nure.easygo.adapters.BaseBindableAdapter;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.rest.ControllerStub;

public class MapsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final String EXTRA_MAP_ID = "map_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ListView listView = (ListView) findViewById(R.id.list_maps);
        listView.setAdapter(new BaseBindableAdapter<Map>(this, new ControllerStub().getMaps().getMaps(), R.layout.map_item, BR.map));
        listView.setOnItemClickListener(this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add)
        {
            Intent intent = new Intent(this, MapInfoActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
