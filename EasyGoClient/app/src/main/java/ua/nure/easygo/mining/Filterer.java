package ua.nure.easygo.mining;

import java.util.List;

import ua.nure.easygo.model.Point;
import ua.nure.easygo.model.attributes.MapAttributes;

/**
 * Created by Oleg on 07.12.2016.
 */

public class Filterer {
	static final Date DEFAULT_MIN_DATE = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse("January 1, 2000");
	static final Date DEFAULT_MIN_DATE = new Date();
	static final int MIN_RATING=0;
	static final int MAX_RATING=0;
}

    public static void filter(List<Point> points, List<FilterParam> filterParams, MapAttributes mapAttributes) {

    	 for(Point point: points) {
             for (FilterParam filterParam: filterParams) {
                 switch(mapAttributes[filterParam.mapAttrIndex].type){
                     case INTEGER :
                    
                          int min = (int)filterParam.min, max = (int)filterParam.max;
                
                          int val = Integer.valueOf(point.attributeValues.getValueOfAttribute(filterParam.mapAttrIndex));
                          if(val<min || val > max)  points.remove(point);
                          break;
                      case STRING :
                    	  String key=filterParam.min;
                    	  //filterParam.max field is not avaible for String attributes
                    	  String val=point.attributeValues.getValueOfAttribute(filterParam.mapAttrIndex);
                    	  if(!val.contains(key)) points.remove(point);
                          break;
                    	  
                      break;
                      case DATE_TIME:
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
                    	  
                      case DOUBLE:  
                    	  
                    double min = (double)filterParam.min, max = (double)filterParam.max;                      
                      double val = Double.valueOf(point.attributeValues.getValueOfAttribute(filterParam.mapAttrIndex));
                      if(val<min || val > max)  points.remove(point);
                      break;
                    	  
                      case RATING:
                    	  int min = (int)filterParam.min, max = (int)filterParam.max;
                    	  if(min<MIN_RATING) min=MIN_RATING;
                    	  if(max>MAX_RATING) max=MAX_RATING;
                          int val = Integer.valueOf(point.attributeValues.getValueOfAttribute(filterParam.mapAttrIndex));
                          if(val<min || val > max)  points.remove(point);
                          break;
                    	  
                       
                     }
                         
                 }

    }

}
