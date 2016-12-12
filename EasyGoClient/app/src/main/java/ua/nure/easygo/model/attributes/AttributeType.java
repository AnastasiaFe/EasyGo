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
    INTEGER("Integer", true), STRING("Text", false), DATE_TIME("Time", true), DOUBLE("Real", true), RATING("Rating", true);

    public final String typeName;
    public final boolean filterable;

    AttributeType(String typeName, boolean filterable) {
        this.typeName = typeName;
        this.filterable = filterable;
    }


}
