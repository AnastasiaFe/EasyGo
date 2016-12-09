package ua.nure.easygo.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import ua.nure.easygo.DBconnect.MySqlConnector;
import ua.nure.easygo.model.Entity;
import ua.nure.easygo.model.GoMap;
import ua.nure.easygo.model.Point;

public class ServerUtils {

	/**
	 * 
	 * @param tagsForParse
	 *            String for parsing which contains tags (e.g. "#tag1 #tag2")
	 * @return array of Strings with tags
	 */
	static String[] parseTags(String tagsForParse) {
		String[] tags = tagsForParse.split("#");
		for (int i = 0; i < tags.length; i++) {
			for (int j = 0; j < tags[i].length(); j++) {
				if (tags[i].charAt(j) == ' ') {
					tags[i] = new String(tags[i].substring(0, j));
				}
			}
		}
		return tags;
	}

	/**
	 * Getting public maps by tags
	 * 
	 * @param tagsForSearch
	 *            String like \"#tag1 #tag2\"
	 * @return {@link List} of {@link GoMap}
	 * @throws SQLException
	 */
	public static synchronized List<GoMap> searchByTags(String tagsForSearch) throws SQLException {
		List<GoMap> maps = new ArrayList<>();
		String[] tags = parseTags(tagsForSearch);
		for (String tag : tags) {
			maps.addAll(MySqlConnector
					.selectGoMap("select * from `gomaps` where tags like %" + tag + "% and is_private = 0"));
		}
		return maps;
	}

	/**
	 * Getting all public maps except maps created by user who searches
	 * 
	 * @param uID
	 *            id of map creator
	 * @return {@link List} of {@link GoMap}
	 * @throws SQLException
	 */
	public static synchronized List<GoMap> getAllPublicMaps(String login) throws SQLException {
		List<GoMap> maps = new ArrayList<>();
		maps.addAll(MySqlConnector
				.selectGoMap("select * from gomaps where is_private = 0 and owner_login <> '" + login + "';"));
		return maps;
	}

	/**
	 * Getting all user`s maps
	 * 
	 * @param uID
	 *            id of map creator
	 * @return {@link List} of {@link GoMap}
	 * @throws SQLException
	 */
	public static synchronized List<GoMap> getAllUserMaps(String login) throws SQLException {
		List<GoMap> maps = null;
		maps = MySqlConnector.selectGoMap("select * from `gomaps` where owner_login = " + login);
		return maps;
	}



	/**
	 * Changes {@link Point} in th db
	 * 
	 * @param mk
	 *            mark to change
	 * @throws SQLException
	 */
	@Deprecated
	public static synchronized void editPoint(Point mk) throws SQLException {
		MySqlConnector.execute("update point set x = " + mk.x + ", y = " + mk.y + ", name = " + mk.name
				+ ", attribute_values = " + mk.attributeValues + " where id = " + Long.toString(mk.pointId));
	}
}
