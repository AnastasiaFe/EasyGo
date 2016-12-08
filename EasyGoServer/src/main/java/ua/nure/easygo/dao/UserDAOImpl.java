package ua.nure.easygo.dao;

import ua.nure.easygo.DBconnect.MySqlConnector;
import ua.nure.easygo.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

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
    public User createUser(User user) throws SQLException {
        if (getUser(user.login) == null) {

            final String queryInsert = "INSERT INTO " + DB_NAME + "." + TABLE_NAME + " (login, password, name, avatar) values ('"
                    + user.login + "','" + user.password + "','" + user.name + "','" + user.avatar + "');";
            MySqlConnector.execute(queryInsert);
            return user;
        }
        // user already exists
        return null;
    }

    @Override
    public User getUser(String login) throws SQLException {
        final String query = "SELECT * from " + DB_NAME + "." + TABLE_NAME + " where login = '" + login + "';";
        List<User> list = null;
        list = MySqlConnector.selectUser(query);
        if (list != null && list.size() > 0) {
            // such user exists
            return list.get(0);
        }
        return null;
    }

    @Override
    public User updateUser(User user) throws SQLException {
        if (getUser(user.login) != null) {
            final String queryUpdate = String.format(Locale.US,
                    "UPDATE %s.%s SET login='%s', password='%s', name='%s', avatar='%s' where login='%s'", DB_NAME, TABLE_NAME,
                    user.login, user.password, user.name, user.avatar, user.login);

            MySqlConnector.execute(queryUpdate);
            return user;
        }

        return null;
    }

    @Override
    public boolean removeUser(String login) throws SQLException {
        if (getUser(login) != null) {
            final String queryDelete = String.format(Locale.US, "delete FROM %s.%s" + " where login='%s'", DB_NAME,
                    TABLE_NAME, login);
            MySqlConnector.execute(queryDelete);
            return true;
        }
        return false;
    }

    @Override
    public boolean postUser(User user) throws Exception {
        if (getUser(user.login) == null) {
            try {
                createUser(user);
                return true;
            } catch (Exception ex) {
                return false;
            }
        } else {
            try {
                updateUser(user);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }
}
