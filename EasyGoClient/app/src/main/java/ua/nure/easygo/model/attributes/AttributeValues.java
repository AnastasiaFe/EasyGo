package ua.nure.easygo.model.attributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 28.10.2016.
 */

public class AttributeValues {
    public List<AttributeValue> values;

    public AttributeValues(List<AttributeValue> values) {
        this.values = values;
    }

    public AttributeValues() {
        values = new ArrayList<>();
    }

    public void setValue(int attributeId, String value) {
        for (AttributeValue v : values) {
            if (v.attributeId == attributeId) {
                v.value = value;
                return;
            }
        }
        //if no value found
        values.add(new AttributeValue(attributeId, value));
    }

    public String getStringValue(int attrIndex) {
        AttributeValue value = getValueOfAttribute(attrIndex);
        return value == null ? null : value.value;
    }

    public AttributeValue getValueOfAttribute(int attrIndex) {
        for (AttributeValue v : values) {
            if (v.attributeId == attrIndex) {
                return v;
            }
        }
        return null;
    }
}
