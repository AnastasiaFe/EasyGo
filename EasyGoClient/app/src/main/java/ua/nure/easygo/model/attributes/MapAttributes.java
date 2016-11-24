package ua.nure.easygo.model.attributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 24.11.2016.
 */

public class MapAttributes {
    public List<MapAttribute> attributes;

    public MapAttributes() {
        attributes = new ArrayList<>();
    }

    public MapAttributes(List<MapAttribute> attributes) {
        this.attributes = attributes;
    }
}
