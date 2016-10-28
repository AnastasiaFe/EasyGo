package ua.nure.easygo.model;

import ua.nure.easygo.model.attributes.AttributeValues;

/**
 * Created by Oleg on 18.10.2016.
 */

public class Point {
    public static int id;

    public float x, y;
    public String name;
    public int pointId;
    public int mapId;
    public AttributeValues attributeValues;

    public Point() {
    }

    public Point(float x, float y, String name, AttributeValues attributeValues, int mapId) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.attributeValues = attributeValues;
        this.mapId = mapId;
        this.pointId = id++;

    }

    /*
    public Point(float x, float y, String name, int pointId) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.pointId = pointId;
    }
*/

    public String getLoc()
    {
        return String.format("%f %f", x, y);
    }

    @Override
    public String toString() {
        return name;
    }
}
