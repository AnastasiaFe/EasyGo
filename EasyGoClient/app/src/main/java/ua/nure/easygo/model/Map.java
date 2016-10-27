package ua.nure.easygo.model;

import android.graphics.Bitmap;
import android.graphics.drawable.StateListDrawable;

import java.util.List;

/**
 * Created by Oleg on 18.10.2016.
 */

public class Map {
    public static int id;

    private List<Point> points;
    private String name;
    private Bitmap icon;
    private int mapId;
    private boolean isPrivate;

    public Map() {
    }

    public Map(List<Point> points, String name, Bitmap icon) {
        this.points = points;
        this.name = name;
        this.icon = icon;
        this.mapId = id++;
    }

    public Map(List<Point> points, String name, Bitmap icon, int mapId) {
        this.points = points;
        this.name = name;
        this.icon = icon;
        this.mapId = mapId;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMapId() {
        return mapId;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    @Override
    public int hashCode() {
        return mapId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Map)) {
            return false;
        }
        return mapId == ((Map)obj).mapId;
    }
}
