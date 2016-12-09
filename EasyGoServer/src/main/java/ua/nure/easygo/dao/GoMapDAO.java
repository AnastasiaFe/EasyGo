package ua.nure.easygo.dao;

import ua.nure.easygo.model.GoMap;


public interface GoMapDAO {	
	public boolean postGoMap(GoMap map) throws Exception;
	GoMap createMap(GoMap map) throws Exception;
	GoMap getMap(long id) throws Exception;	
	GoMap updateMap(GoMap map) throws Exception;	
	boolean removeMap(long id) throws Exception;
}
