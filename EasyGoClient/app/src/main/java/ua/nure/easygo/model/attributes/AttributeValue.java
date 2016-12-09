package ua.nure.easygo.model.attributes;

/**
 * Created by Oleg on 28.10.2016.
 */

public class AttributeValue {
    /**
     *  Index of corresponding map attribute
     */
    public int attributeId;
    public String value;


    public AttributeValue() {
    }

    public AttributeValue(int attributeId, String value) {
        this.attributeId = attributeId;
        this.value = value;

    }

}
