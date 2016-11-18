package ua.nure.easygo.model;

import android.graphics.Bitmap;
import android.graphics.drawable.StateListDrawable;

import java.util.LinkedList;
import java.util.List;

import ua.nure.easygo.model.attributes.MapAttributes;

/**
 * Created by Oleg on 18.10.2016.
 */

public class Map {
    public static int id;

    public List<Point> points;
    public MapAttributes mapAttributes;
    public String name;
    public Bitmap icon;
    public int mapId;
    public boolean isPrivate;

    public Map() {
        points=new LinkedList<>();
    }

    public Map(List<Point> points, String name, Bitmap icon, MapAttributes mapAttributes) {
        this.points = new LinkedList<>(points);
        this.name = name;
        this.icon = icon;
        this.mapAttributes = mapAttributes;
        this.mapId = id++;
    }

    /*
    public Map(List<Point> points, String name, Bitmap icon, int mapId, MapAttributes mapAttributes) {
        this.points = points;
        this.name = name;
        this.icon = icon;
        this.mapId = mapId;
        this.mapAttributes = mapAttributes;
    }
*/

    @Override
    public int hashCode() {
        return mapId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Map)) {
            return false;
        }
        return mapId == ((Map) obj).mapId;
    }
}
