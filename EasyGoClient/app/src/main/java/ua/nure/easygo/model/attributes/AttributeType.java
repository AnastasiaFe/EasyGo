package ua.nure.easygo.model.attributes;

/**
 * Represents data types for map attributes
 * INTEGER      int
 * STRING       String
 * DATE_TIME    Date
 * DOUBLE       Double
 * RATING       int (usually in range [1-5], number of stars)
 */

public enum AttributeType {
    //DON`T CHANGE NAMES!!
    INTEGER("Integer"), STRING("Text"), DATE_TIME("Time"), DOUBLE("Real"), RATING("Rating");

    public final String typeName;

    AttributeType(String typeName) {
        this.typeName = typeName;
    }


}
