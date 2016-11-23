package ua.nure.easygo.dao;

import ua.nure.easygo.DBconnect.MySqlConnector;
import ua.nure.easygo.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Анна on 23.11.2016.
 */
public class UserDAO implements IUserDAO {
    public final String DB_NAME = "stub";
    public final String TABLE_NAME = "users";
    private IUserDAO userDAO;

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /*
    Fields:
    	public final int id;
	public final String login;
	public final String password;
	public final String email;
	public final String status;
     */

    @Override
    public User createUser(User user) {
        if (getUser(user.id)==null){


            final String queryInsert = "INSERT INTO " + DB_NAME+"."+TABLE_NAME+
                    " (id, login, password, email, status) values '"+
                    user.id +"','" + user.login +"','"+user.password+
                    "','" + user.email+"','" + user.status+"';";
            try {
                MySqlConnector.execute(queryInsert);
                return user;
            }
            catch(SQLException e) {
                e.printStackTrace();

            }}
        //user already exists
        return null;
    }

    @Override
    public User getUser(int id) {
        final String query = "SELECT * from" + DB_NAME+"."+TABLE_NAME+" where id='"+id+"';";
        ArrayList<User> list = new ArrayList<>();
        try {
            list = MySqlConnector.selectUser(query);}
        catch (SQLException e){
            e.printStackTrace();
        }
        if(list!=null) {
            //such user exists
            return list.get(0);
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        if (getUser(user.id)!=null){


            final String queryUpdate = String.format("UPDATE %s.%s" +
                            " SET login=%s, password=%s, email=%s, status=%s where id=%d",
                    DB_NAME,TABLE_NAME,
                    user.login, user.password,
                    user.email, user.status, user.id);

            try {
                MySqlConnector.execute(queryUpdate);
                return user;
            }
            catch(SQLException e) {
                e.printStackTrace();

            }}

        return null;
    }

    @Override
    public boolean removeUser(int id) {
        if (getUser(id)!=null){

            final String queryDelete = String.format("REMOVE FROM %s.%s" +
                            " where id=%d",
                    DB_NAME,TABLE_NAME, id);
            try {
                MySqlConnector.execute(queryDelete);
                return true;
            }
            catch(SQLException e) {
                e.printStackTrace();

            }}
        return false;
    }

    @Override
    public List<User> getUserList(String parameters) {
        List<User> list = new ArrayList<>();
        final String querySelect = String.format("SELECT * FROM %s.%s",
                DB_NAME,TABLE_NAME);
        try{
            list=MySqlConnector.selectUser(querySelect);}
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
