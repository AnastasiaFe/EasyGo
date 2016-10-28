package ua.nure.easygo;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.Point;

/**
 * Describes which maps are currently showed on the screen
 */
public class MapsContext {
    private Set<Map> maps;

    public MapsContext() {
        maps = new HashSet<>();
    }

    public void add(Map map) {
        if (maps.contains(map)) {
            throw new IllegalArgumentException("This map is already added");
        }
        maps.add(map);
    }

    public Iterable<Point> getPoints() {
        List<Point> pointList = new LinkedList<>();
        for (Map m : maps) {
            pointList.addAll(m.points);
        }
        return pointList;
    }

    public void remove(Map map) {
        maps.remove(map);
    }

    public void removeAll() {
        maps.clear();
    }

    public void replace(Map map) {
        removeAll();
        add(map);
    }

    public boolean contains(Map m) {
        return maps.contains(m);
    }

    public boolean isEmpty() {
        return maps.isEmpty();
    }
}
