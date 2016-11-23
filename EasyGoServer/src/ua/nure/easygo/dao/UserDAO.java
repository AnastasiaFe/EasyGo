package ua.nure.easygo.dao;

import ua.nure.easygo.model.User;

/**
 * Created by Анна on 23.11.2016.
 */
public interface UserDAO {
	User createUser(User user);

	User getUser(String login);

	User updateUser(User user);

	boolean removeUser(String login);
}
