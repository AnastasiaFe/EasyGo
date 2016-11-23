package ua.nure.easygo.dao;

import java.sql.SQLException;
import java.util.List;

import ua.nure.easygo.DBconnect.MySqlConnector;
import ua.nure.easygo.model.User;

/**
 * Created by Анна on 23.11.2016.
 */
public class UserDAOImpl implements UserDAO {
	public final String DB_NAME = "EasyGoDB";
	public final String TABLE_NAME = "users";
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public User createUser(User user) {
		if (getUser(user.login) == null) {
			final String queryInsert = "INSERT INTO " + DB_NAME + "." + TABLE_NAME + " (login, password, name) values '"
					+ user.login + "','" + user.password + "','" + user.name + "';";
			try {
				MySqlConnector.execute(queryInsert);
				return user;
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		// user already exists
		return null;
	}

	@Override
	public User getUser(String login) {
		final String query = "SELECT * from" + DB_NAME + "." + TABLE_NAME + " where login = '" + login + "';";
		List<User> list = null;
		try {
			list = MySqlConnector.selectUser(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list != null) {
			// such user exists
			return list.get(0);
		}
		return null;
	}

	@Override
	public User updateUser(User user) {
		if (getUser(user.login) != null) {
			final String queryUpdate = String.format(
					"UPDATE %s.%s SET login='%s', password='%s', name='%s' where login='%s'", DB_NAME, TABLE_NAME,
					user.login, user.password, user.name, user.login);

			try {
				MySqlConnector.execute(queryUpdate);
				return user;
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		return null;
	}

	@Override
	public boolean removeUser(String login) {
		if (getUser(login) != null) {
			final String queryDelete = String.format("REMOVE FROM %s.%s" + " where login='%s'", DB_NAME, TABLE_NAME,
					login);
			try {
				MySqlConnector.execute(queryDelete);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		return false;
	}
}
