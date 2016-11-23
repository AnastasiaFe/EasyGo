package ua.nure.easygo.dao;

import ua.nure.easygo.model.User;

import java.util.List;

/**
 * Created by Анна on 23.11.2016.
 */
public class UserDAO implements IUserDAO {
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
        return null;
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public boolean removeUser(int id) {
        return false;
    }

    @Override
    public List<User> getUserList(String parameters) {
        return null;
    }
}
