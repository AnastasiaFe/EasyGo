package ua.nure.easygo.dao;

import ua.nure.easygo.model.User;

import java.util.List;

/**
 * Created by Анна on 23.11.2016.
 */
public interface IUserDAO {
    User createUser(User user);
    User getUser(int id);
    User updateUser(User user);
    boolean removeUser(int id);
    List<User> getUserList(String parameters);
}
