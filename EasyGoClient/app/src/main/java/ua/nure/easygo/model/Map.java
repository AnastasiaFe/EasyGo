package ua.nure.easygo.model;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Oleg on 18.10.2016.
 */

public class Map {
    private List<Point> points;
    private String name;
    private Bitmap icon;

    public Map() {
    }

    public Map(List<Point> points, String name, Bitmap icon) {
        this.points = points;
        this.name = name;
        this.icon = icon;
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
}
