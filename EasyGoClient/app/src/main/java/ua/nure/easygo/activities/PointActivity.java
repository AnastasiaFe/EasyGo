package ua.nure.easygo.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import easygo.nure.ua.easygoclient.R;
import easygo.nure.ua.easygoclient.databinding.ActivityPointBinding;
import ua.nure.easygo.model.Point;
import ua.nure.easygo.rest.ControllerStub;

public class PointActivity extends AppCompatActivity {

    public static final String EXTRA_POINT_ID="point_id";
public static final int MASK_MAP = 0xffff0000;
    public static final int MASK_POINT=~MASK_MAP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPointBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_point);
        Point p;

        int id = getIntent().getIntExtra(EXTRA_POINT_ID, 0);

        int map=(id&MASK_MAP)>>>16, point=id&MASK_POINT;

        p = new ControllerStub().getMap(map).getPoints().get(point);
        binding.setPoint(p);
        binding.setMap(new ControllerStub().getMap(map));

    }
}
