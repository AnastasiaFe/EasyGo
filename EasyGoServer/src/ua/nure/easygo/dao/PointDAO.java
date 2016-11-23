package ua.nure.easygo.dao;

import ua.nure.easygo.model.Point;

/**
 * Created by Анна on 23.11.2016.
 */
public interface PointDAO {
	Point createPoint(Point point);

	Point getPoint(long id);

	Point updatePoint(Point point);

	boolean removePoint(long id);
}
