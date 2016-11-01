package ua.nure.easygo.dao;

import java.util.List;

import ua.nure.easygo.model.GoMap;


public interface IGoMapDAO {





	
	
	GoMap createMap(GoMap map);
	GoMap getMap(String id);	
	GoMap updateMap(GoMap map);	
	boolean removeMap(String id);	
	List<GoMap> getCustomersList(String parameters);
}
