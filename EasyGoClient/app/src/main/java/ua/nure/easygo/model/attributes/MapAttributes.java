package ua.nure.easygo.model.attributes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 24.11.2016.
 */

public class MapAttributes implements Serializable {
    public List<MapAttribute> attributes;

    public MapAttributes() {
        attributes = new ArrayList<>();
    }

    public MapAttributes(List<MapAttribute> attributes) {
        this.attributes = attributes;
    }

    public int getId(MapAttribute attribute) {
        for (int i = 0; i < attributes.size(); i++) {
            if (attribute == attributes.get(i)) {
                return i;
            }
        }
        return -1;
    }
}
