package ua.nure.easygo.dao;

import ua.nure.easygo.model.GoMap;


public interface GoMapDAO {	
	GoMap createMap(GoMap map) throws Exception;
	GoMap getMap(long id) throws Exception;	
	GoMap updateMap(GoMap map) throws Exception;	
	boolean removeMap(long id) throws Exception;
}
