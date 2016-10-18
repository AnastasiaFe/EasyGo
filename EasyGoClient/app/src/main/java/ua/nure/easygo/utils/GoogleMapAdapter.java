package ua.nure.easygo.utils;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.Point;

/**
 * Created by Oleg on 18.10.2016.
 */

public class GoogleMapAdapter {

    public void addPoint(GoogleMap googleMap, Point p)
    {

        LatLng sydney = new LatLng(p.getX(), p.getY());
        googleMap.addMarker(new MarkerOptions().position(sydney).title(p.getName()));
       // googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void fill(GoogleMap googleMap, Map map)
    {
        for (Point p:map.getPoints())
        {
            addPoint(googleMap, p);
        }
    }
}
