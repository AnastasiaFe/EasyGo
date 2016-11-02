package ua.nure.easygo.model.attributes;

/**
 * Created by Oleg on 28.10.2016.
 */

public class AttributeValue {
    public int attributeId;
    public String value;
    public int pointId;

    public AttributeValue() {
    }

    public AttributeValue(int attributeId, String value) {
        this.attributeId = attributeId;
        this.value = value;
        //this.pointId = pointId;
    }

}
