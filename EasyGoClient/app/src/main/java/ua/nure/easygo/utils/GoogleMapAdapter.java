package ua.nure.easygo.utils;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.Point;

/**
 * Created by Oleg on 18.10.2016.
 */

public class GoogleMapAdapter {

    public Marker addPoint(GoogleMap googleMap, Point p, BitmapDescriptor icon) {

        LatLng sydney = new LatLng(p.getX(), p.getY());
        return googleMap.addMarker(new MarkerOptions().position(sydney).title(p.getName()).icon(icon));
        // googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void fill(GoogleMap googleMap, Map map) {
        BitmapDescriptor icon;
        if (map.getIcon() == null) {
            icon = BitmapDescriptorFactory.defaultMarker();
        }
        else
        {
            icon = BitmapDescriptorFactory.fromBitmap(map.getIcon());
        }


        for (Point p : map.getPoints()) {
            int id = p.getPointId() | (map.getMapId() << 16);
            addPoint(googleMap, p, icon).setTag(id);

        }
    }
}
