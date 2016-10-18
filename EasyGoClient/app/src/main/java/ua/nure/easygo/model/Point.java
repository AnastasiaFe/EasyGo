package ua.nure.easygo.model;

/**
 * Created by Oleg on 18.10.2016.
 */

public class Point {
    private float x, y;
    private String name;

    public Point() {
    }

    public Point(float x, float y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
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
}
