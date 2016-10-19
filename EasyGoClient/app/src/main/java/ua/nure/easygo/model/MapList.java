package ua.nure.easygo.model;

import java.util.List;

/**
 * Created by Oleg on 19.10.2016.
 */

public class MapList {
    private List<Map> maps;

    public MapList() {
    }

    public MapList(List<Map> maps) {
        this.maps = maps;
    }

    public List<Map> getMaps() {
        return maps;
    }

    public void setMaps(List<Map> maps) {
        this.maps = maps;
    }
}
