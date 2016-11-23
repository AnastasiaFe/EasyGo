package ua.nure.easygo.model;

import ua.nure.easygo.model.attributes.AttributeValues;

/**
 * Created by Oleg on 18.10.2016.
 */

public class Point {


    public float x, y;
    public String name;
    public long pointId;
    public long mapId;
    public AttributeValues attributeValues;

    public Point() {
        attributeValues = new AttributeValues();
    }

    public Point(long pointId, float x, float y, String name, AttributeValues attributeValues, long mapId) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.attributeValues = attributeValues;
        this.mapId = mapId;
        this.pointId = pointId;
    }


    public String getLoc() {
        return String.format("%f %f", x, y);
    }

    @Override
    public String toString() {
        return name;
    }
}
