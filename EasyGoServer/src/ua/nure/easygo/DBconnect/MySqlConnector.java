package ua.nure.easygo.DBconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.easygo.model.GoMap;
import ua.nure.easygo.model.Point;
import ua.nure.easygo.model.User;

/**
 * Class for connection to MySQL database on localhost and running SELECT and
 * other queries
 * 
 * @author Alexander Shylin
 * 
 */

public class MySqlConnector {
	// JDBC URL, username and password of MySQL server
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/easygodb?user=" + USER + "&password="
			+ PASSWORD + "&useSSL=false";

	// JDBC variables for opening and managing connection
	private static Connection connection;
	private static Statement statement;
	private static ResultSet result;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for executing any SQL query except SELECT
	 * 
	 * @param query
	 *            SQL query
	 * @throws SQLException
	 */
	public static synchronized void execute(final String query) throws SQLException {
		try {
			// opening database connection to MySQL server
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			// getting Statement object to execute query
			statement = connection.createStatement();

			// executing query
			statement.executeUpdate(query);
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			connection.close();
			statement.close();
			result.close();
		}
	}

	/**
	 * Selects columns from table `user`
	 * 
	 * @param query
	 *            SQL statement
	 * @return list of columns
	 * @throws SQLException
	 */
	public static synchronized List<User> selectUser(final String query) throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				list.add(new User(result.getString(1), result.getString(2), result.getString(3)));
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			connection.close();
			statement.close();
			result.close();
		}
		return list;
	}

	/**
	 * Selects columns from table `map`
	 * 
	 * @param query
	 *            SQL statement
	 * @return list of columns
	 * @throws SQLException
	 */
	public static synchronized List<GoMap> selectGoMap(final String query) throws SQLException {
		ArrayList<GoMap> list = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				list.add(new GoMap(result.getLong(1), result.getString(2), result.getString(3), result.getString(4),
						result.getString(5), result.getBoolean(5)));
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			connection.close();
			statement.close();
			result.close();
		}
		return list;
	}
	
	/**
	 * Selects columns from table `mark`
	 * 
	 * @param query
	 *            SQL statement
	 * @return list of columns
	 * @throws SQLException
	 */
	public static synchronized List<Point> selectPoint(final String query) throws SQLException {
		ArrayList<Point> list = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				list.add(new Point(result.getLong(1), result.getFloat(2), result.getFloat(3), result.getString(4),
						result.getLong(5), result.getString(5)));
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			connection.close();
			statement.close();
			result.close();
		}
		return list;
	}
	

}
