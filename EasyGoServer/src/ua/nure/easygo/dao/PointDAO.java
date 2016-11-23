package ua.nure.easygo.dao;

import ua.nure.easygo.model.Point;

/**
 * Created by Анна on 23.11.2016.
 */
public interface PointDAO {
	Point createPoint(Point point)  throws Exception;

	Point getPoint(long id)  throws Exception;

	Point updatePoint(Point point)  throws Exception;

	boolean removePoint(long id)  throws Exception;

	boolean postPoint(Point map) throws Exception;
}
