package ua.nure.easygo.model.attributes;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oleg on 28.10.2016.
 */

public class MapAttributes {
    public List<MapAttribute> attributes;

    public MapAttributes(List<MapAttribute> attributes) {
        this.attributes = new LinkedList<>(attributes);
    }

    public MapAttributes() {
        attributes = new LinkedList<>();
    }


}
