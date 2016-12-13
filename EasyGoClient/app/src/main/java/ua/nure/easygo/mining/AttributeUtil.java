package ua.nure.easygo.mining;

import ua.nure.easygo.model.attributes.AttributeType;

/**
 * Created by Oleg on 13.12.2016.
 */

public class AttributeUtil {
    public static Object getAttrValueAsObject(String attrVal, AttributeType type) {
        if (attrVal == null) {
            return null;
        }
        switch (type) {
            case INTEGER:
                try {
                    return Integer.valueOf(attrVal);
                } catch (NumberFormatException e) {
                }
                break;
            case STRING:
                return attrVal;
            //case DATE_TIME:

            case RATING:
            case DOUBLE:
                try {
                    return Double.valueOf(attrVal);
                } catch (NumberFormatException e) {
                }
                break;

        }
        return null;
    }
}
