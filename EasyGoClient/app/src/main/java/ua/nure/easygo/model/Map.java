package ua.nure.easygo.model;

import java.util.List;

/**
 * Created by Oleg on 18.10.2016.
 */

public class Map {
    private List<Point> points;
    private String name;

    public Map() {
    }

    public Map(List<Point> points, String name) {
        this.points = points;
        this.name = name;
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
