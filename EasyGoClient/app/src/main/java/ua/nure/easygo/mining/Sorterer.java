package ua.nure.easygo.mining;

import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ua.nure.easygo.model.Point;
import ua.nure.easygo.model.attributes.AttributeType;
import ua.nure.easygo.model.attributes.MapAttributes;

/**
 * Created by Oleg on 12.12.2016.
 */

public class Sorterer {


    public static void sort(List<Point> points, MapAttributes mapAttributes, final SortingInfo sortingInfo) {
        if (sortingInfo == null) {
            return;
        }

        final int attrIndex = sortingInfo.getAttributeIndex();
        final AttributeType type = mapAttributes.attributes.get(attrIndex).type;
        final int asc = sortingInfo.isAscending() ? 1 : -1;

        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {


                Object c1 = AttributeUtil.getAttrValueAsObject(o1.attributeValues.getStringValue(attrIndex), type),
                        c2 = AttributeUtil.getAttrValueAsObject(o2.attributeValues.getStringValue(attrIndex), type);
                Log.d("qaz", c1 + "     " + c2 + "   " );
                if (c1 == null) {
                    return 1;
                } else if (c2 == null) {
                    return -1;
                } else {
                    return ((Comparable) c2).compareTo(c1) * asc;


                }


            }
        });
    }
}
