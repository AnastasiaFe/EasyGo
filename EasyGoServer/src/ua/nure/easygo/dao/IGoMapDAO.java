package ua.nure.easygo.dao;

import java.util.List;

import ua.nure.easygo.model.GoMap;


public interface IGoMapDAO {	
	
	GoMap createMap(GoMap map);
	GoMap getMap(int id);	
	GoMap updateMap(GoMap map);	
	boolean removeMap(int id);	
	List<GoMap> getGoMapList(String parameters);
}
