package ua.nure.easygo.model;

/**
 * Created by Oleg on 18.10.2016.
 */

public class Point {
    public static int id;

    private float x, y;
    private String name;
    private int pointId;

    public Point() {
    }

    public Point(float x, float y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.pointId = id++;
    }

    public Point(float x, float y, String name, int pointId) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.pointId = pointId;
    }

    public float getX() {
        return x;
    }

    public String getLoc()
    {
        return String.format("%f %f", x, y);
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPointId() {
        return pointId;
    }

    @Override
    public String toString() {
        return name;
    }
}
