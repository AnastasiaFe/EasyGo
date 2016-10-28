package ua.nure.easygo.model.attributes;

/**
 * Created by Oleg on 28.10.2016.
 */

public enum AttributeType {
    //DON`T CHANGE NAMES!!
    INTEGER("Integer"), STRING("Text"), DATE_TIME("Time"), DOUBLE("Real"), RATING("Rating");

    public final String typeName;

    AttributeType(String typeName) {
        this.typeName = typeName;
    }


}
