package ua.nure.easygo.mining;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ua.nure.easygo.model.Point;
import ua.nure.easygo.model.attributes.AttributeValue;
import ua.nure.easygo.model.attributes.MapAttributes;

/**
 * Created by Oleg on 07.12.2016.
 */

public class Filterer {
/*
    static final Date DEFAULT_MIN_DATE = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse("January 1, 2000");
	static final Date DEFAULT_MIN_DATE = new Date();
	static final int MIN_RATING=0;
	static final int MAX_RATING=0;
*/


    public static void filter(List<Point> points, List<FilterParam> filterParams, MapAttributes mapAttributes) {
        if (filterParams == null) {
            return;
        }
        Set<Point> pointsToRemove = new HashSet<>();

        for (Point point : points) {
            for (FilterParam filterParam : filterParams) {
                Object min = filterParam.getMin(), max = filterParam.getMax();
                AttributeValue attributeValue = point.attributeValues.getValueOfAttribute(filterParam.getMapAttrIndex());
                if (attributeValue == null) {
                    continue;
                }
                String val = attributeValue.value;
                //keep point if it has no value for this attr
                if (val == null) {
                    continue;
                }

                switch (mapAttributes.attributes.get(filterParam.getMapAttrIndex()).type) {
                    case INTEGER: {
                        try {
                            int intVal = Integer.valueOf(val);
                            try {
                                if ((min != null && ((int) min) > intVal) || (max != null && ((int) max) < intVal)) {
                                    pointsToRemove.add(point);
                                }
                            } catch (Exception e) {
                                if ((min != null && ((double) min) > intVal) || (max != null && ((double) max) < intVal)) {
                                    pointsToRemove.add(point);
                                }
                            }
                        } catch (NumberFormatException e) {

                        }
                    }


                    break;
                    case RATING:
                    case DOUBLE: {
                        try {
                            double doubleVal = Double.valueOf(val);
                            if ((min != null && ((double) min) > doubleVal) || (max != null && ((double) max) < doubleVal)) {
                                pointsToRemove.add(point);
                            }
                        } catch (NumberFormatException e) {

                        }
                    }
                    break;



                  /*    case STRING :
                          String key=filterParam.min;
                    	  //filterParam.max field is not avaible for String attributes
                    	  String val=point.attributeValues.getValueOfAttribute(filterParam.mapAttrIndex);
                    	  if(!val.contains(key)) points.remove(point);
                          break;
                    	  
                      break;*/
                    /*  case DATE_TIME:
                          String strMinDate=filterParam.min, strManDate = filterParam.max;
                    	  //What data format is used in input field?
                    	  DateFormat formatter  = new SimpleDateFormat("dd/MM/yyyy");
                    	  Date date1, date2; 
                    	     try{
	                    	     date1 = formatter.parse(strMinDate);
	                    	     date2 = formatter.parse(strManDate); }
                    	     catch(ParseException e) {
	                    	    //if could not convert string to date object, or strings are empty
	                    	    //set default values
	                    	    date1 = DEFAULT_MIN_DATE;
	                    	    date2 = DEFAULT_MAX_DATE;
                    	     }
                    	 if(date2<date1 || date1<DEFAULT_MIN_DATE || date2>DEFAULT_MAX_DATE) {
                    	       	//unchecked exception
                    		 throw new IllegalArgumentException("Date shoul be in range from "
                    	 +DEFAULT_MIN_DATE+" to "+ DEFAULT_MAX_DATE);
                    		 
                    	 }
                          Date val = formatter.parse(point.attributeValues.getValueOfAttribute(filterParam.mapAttrIndex)));
                          if(val<date1 || val > date2) points.remove(point);
                          break;
                    	  */


                }

            }

        }

        points.removeAll(pointsToRemove);

    }
}
