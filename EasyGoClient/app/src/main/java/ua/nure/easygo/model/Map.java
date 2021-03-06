package ua.nure.easygo.model;

import ua.nure.easygo.model.attributes.MapAttributes;

/**
 * Created by Oleg on 18.10.2016.
 */

public class Map {

    public MapAttributes mapAttributes;
    public String name;
    public long mapId;
    public String ownerLogin;
    public boolean isPrivate;
    public String tags;

    public Map() {
        mapAttributes = new MapAttributes();
    }

    public Map(long mapId, String name, MapAttributes mapAttributes) {
        this.name = name;
        this.mapAttributes = mapAttributes;
        this.mapId = mapId;
    }

    public String getIcon() {
        return "map" + mapId;
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
        return (int) mapId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Map)) {
            return false;
        }
        return mapId == ((Map) obj).mapId;
    }
}
