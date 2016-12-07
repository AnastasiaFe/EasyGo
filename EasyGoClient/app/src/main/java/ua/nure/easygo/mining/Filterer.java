package ua.nure.easygo.mining;

import java.util.List;

import ua.nure.easygo.model.Point;
import ua.nure.easygo.model.attributes.MapAttributes;

/**
 * Created by Oleg on 07.12.2016.
 */

public class Filterer {

    public static void filter(List<Point> points, List<FilterParam> filterParams, MapAttributes mapAttributes) {


        //в FilterParam min/max хранятся в виде объектов
        //в point.attributeValues значения хранятся в виде строки
/*пройтись по всем точкам
        для каждого FilterParam
        если соответствующий атрибут не удовлетворяет условию фильтра, удалить точку из списка
 */

        /*EXAMPLE
            if(mapAttributes[filterParam.mapAttrIndex].type==INTEGER){
                int min = (int)filterParam.min, max = (int)filterParam.max;
                int val = Integer.valueOf(point.attributeValues.getValueOfAttribute(filterParam.mapAttrIndex));
                if(val<min || val > max){
                    points.remove(point);
                }
            }
         */

    }

}
