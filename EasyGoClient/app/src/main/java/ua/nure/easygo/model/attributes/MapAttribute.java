package ua.nure.easygo.model.attributes;

import java.io.Serializable;

/**
 * Created by Oleg on 28.10.2016.
 */

public class MapAttribute implements Serializable{
    public String name;
    public AttributeType type;

    public MapAttribute(String name, AttributeType type) {
        this.name = name;
        this.type = type;
    }

    public MapAttribute() {
    }

    @Override
    public String toString() {
        return name;
    }
}
