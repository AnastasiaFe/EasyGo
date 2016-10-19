package ua.nure.easygo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent res = new Intent();
        res.putExtra(EXTRA_MAP_ID, position);
        setResult(RESULT_OK, res);
        finish();
    }
}
