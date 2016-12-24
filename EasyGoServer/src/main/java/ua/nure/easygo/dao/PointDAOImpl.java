package ua.nure.easygo.dao;

import ua.nure.easygo.DBconnect.MySqlConnector;
import ua.nure.easygo.model.Point;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Анна on 23.11.2016.
 */
public class PointDAOImpl implements PointDAO {
    public final String DB_NAME = "EasyGoDB";
    public final String TABLE_NAME = "points";

    private PointDAO pointDAO;

    public PointDAO getPointDAO() {
        return pointDAO;
    }

    public void setPointDAO(PointDAO pointDAO) {
        this.pointDAO = pointDAO;
    }

    @Override
    public Point createPoint(Point point) throws SQLException {
        // if no such point in DB
        //if (getPoint(point.pointId) == null) {
        final String queryInsert = "INSERT INTO " + DB_NAME + "." + TABLE_NAME
                + " (x, y, name, map_id, attribute_values) values (" + point.x + ", " + point.y + ", '" + point.name
                + "', " + point.mapId + ", '" + point.attributeValues + "');";

        MySqlConnector.execute(queryInsert);
        return point;
        //}
        // point already exists
        //return null;
    }

    @Override
    public Point getPoint(long id) throws SQLException {

        final String query = "SELECT * from " + DB_NAME + "." + TABLE_NAME + " where point_id=" + id + ";";


        List<Point> list = null;
        list = MySqlConnector.selectPoint(query);
        if (list != null) {
            // such point exists
            return list.get(0);
        }
        // else point not exists
        return null;
    }

    @Override
    public Point updatePoint(Point point) throws SQLException {
        if (getPoint(point.pointId) != null) {

            final String queryUpdate = String.format(Locale.US,
                    "UPDATE %s.%s SET x=%f, y=%f, name='%s', map_id=%d, attribute_values='%s' where point_id=%d", DB_NAME,
                    TABLE_NAME, point.x, point.y, point.name, point.mapId, point.attributeValues, point.pointId);


            MySqlConnector.execute(queryUpdate);
            return point;
        }
        // point already exists
        return null;
    }

    @Override
    public boolean removePoint(long id) throws SQLException {
        if (getPoint(id) != null) {
            final String queryDelete = String.format(Locale.US, "delete FROM %s.%s" + " where point_id=%d", DB_NAME,
                    TABLE_NAME, id);

            MySqlConnector.execute(queryDelete);
            return true;
        }
        return false;
    }

    @Override
    public boolean postPoint(Point point) throws Exception {
        if (point.pointId == 0) {
            try {
                createPoint(point);
                return true;
            } catch (Exception ex) {
                return false;
            }
        } else {
            try {
                updatePoint(point);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    @Override
    public List<Point> getPoints(long[] ids) throws SQLException {
        List<Point> points = new LinkedList<>();
        for (long id : ids) {
            points.add(getPoint(id));
        }
        return points;
    }

    public  void main(String[] args) throws Exception {
        PointDAOImpl impl = new PointDAOImpl();
        impl.postPoint(new Point(1L, 2.2f, 3.3f, "asd", 0, "val"));
    }
}
