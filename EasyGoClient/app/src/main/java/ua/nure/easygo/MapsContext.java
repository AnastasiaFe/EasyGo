package ua.nure.easygo;

import java.util.HashSet;
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
    private MapsContextListener listener;

    public MapsContext() {
        maps = new HashSet<>();
    }

    public void add(Map map) throws MapAlreadyAddedException {
        if (maps.contains(map)) {
            throw new MapAlreadyAddedException();
        }
        maps.add(map);
        listener.mapsContextChanged(this);
    }

    public Iterable<Point> getPoints() {
        List<Point> pointList = new LinkedList<>();
        for (Map m : maps) {
            pointList.addAll(m.points);
        }
        return pointList;
    }

    public Set<Map> getMaps() {
        return maps;
    }

    public void remove(Map map) {
        maps.remove(map);
        listener.mapsContextChanged(this);
    }

    public void removeAll() {
        maps.clear();
        listener.mapsContextChanged(this);
    }

    public void replace(Map map) {
        removeAll();
        //is safe because context is empty
        try {
            add(map);
        } catch (MapAlreadyAddedException e) {
            e.printStackTrace();
        }
        listener.mapsContextChanged(this);
    }

    public boolean contains(Map m) {
        return maps.contains(m);
    }

    public boolean isEmpty() {
        return maps.isEmpty();
    }

    public void setListener(MapsContextListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
    }

    public interface MapsContextListener {
        void mapsContextChanged(MapsContext mapsContext);
    }

    public static class MapAlreadyAddedException extends Exception {
    }
}
