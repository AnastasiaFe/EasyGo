package ua.nure.easygo.dao;

import ua.nure.easygo.model.User;

/**
 * Created by Анна on 23.11.2016.
 */
public interface UserDAO {
	User createUser(User user) throws Exception;

	User getUser(String login) throws Exception;

	User updateUser(User user) throws Exception;

	boolean removeUser(String login) throws Exception;
	
	boolean postUser(User user) throws Exception;
}
