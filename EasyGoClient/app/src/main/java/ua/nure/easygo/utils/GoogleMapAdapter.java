package ua.nure.easygo.utils;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.easygo.MapsContext;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.Point;
import ua.nure.easygo.rest.RestService;

/**
 * Created by Oleg on 18.10.2016.
 */

public class GoogleMapAdapter {

    private GoogleMap googleMap;
    private List<Point> points;

    public GoogleMapAdapter(GoogleMap googleMap) {
        points = new LinkedList<>();
        this.googleMap = googleMap;
    }

    public Marker addPoint(Point p, BitmapDescriptor icon) {

        points.add(p);
        LatLng sydney = new LatLng(p.x, p.y);
        return googleMap.addMarker(new MarkerOptions().position(sydney).title(p.name).icon(icon));

    }

    public void fill(MapsContext mapsContext) {
        for (Map m : mapsContext.getMaps()) {
            fill(m);
        }
    }

    public List<Point> getPoints() {
        return points;
    }

    public void fill(final Map map) {

        final BitmapDescriptor icon;
        if (map.icon == null) {
            icon = BitmapDescriptorFactory.defaultMarker();
        } else {
            icon = BitmapDescriptorFactory.fromBitmap(map.icon);
        }

        RestService.get().getPoints(map.mapId).enqueue(new Callback<List<Point>>() {
            @Override
            public void onResponse(Call<List<Point>> call, Response<List<Point>> response) {
                for (Point p : response.body()) {
                    addPoint(p, icon).setTag(p);
                }
            }

            @Override
            public void onFailure(Call<List<Point>> call, Throwable t) {

            }
        });

    }

    public void clear() {
        googleMap.clear();
        points.clear();
    }
}
