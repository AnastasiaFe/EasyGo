package ua.nure.easygo.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import ua.nure.easygo.DBconnect.MySqlConnector;
import ua.nure.easygo.model.Entity;
import ua.nure.easygo.model.GoMap;
import ua.nure.easygo.model.Mark;

public class ServerUtils {

	/**
	 * 
	 * @param tagsForParse
	 *            String for parsing which contains tags (e.g. "#tag1 #tag2")
	 * @return array of Strings with tags
	 */
	private static String[] parseTags(String tagsForParse) {
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
					.selectGoMap("select * from map where map.tags like %" + tag + "% and map.access = `public`"));
		}
		return maps;
	}

	/**
	 * Getting all public maps except maps except maps created by user who
	 * searches
	 * 
	 * @param uID
	 *            id of map creator
	 * @return {@link List} of {@link GoMap}
	 * @throws SQLException
	 */
	public static synchronized List<GoMap> getAllPublicMaps(int uID) throws SQLException {
		List<GoMap> maps = new ArrayList<>();
		maps.addAll(
				MySqlConnector.selectGoMap("select * from map where map.access = `public` and creator_id <> " + uID));
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
	public static synchronized List<GoMap> getAllUserMaps(int uID) throws SQLException {
		List<GoMap> maps = new ArrayList<>();
		maps.addAll(
				MySqlConnector.selectGoMap("select * from map where creator_id = " + uID));
		return maps;
	}

	public static synchronized String toJSON(ArrayList<Entity> list) {
		Gson gson = new Gson();
		StringBuilder sb = new StringBuilder();
		for (Entity e : list) {
			sb.append(gson.toJson(e));

		}
		return sb.toString();
	}

	/**
	 * Changes {@link Mark} in th db
	 * 
	 * @param mk
	 *            mark to change
	 * @throws SQLException
	 */
	public static synchronized void editMark(Mark mk) throws SQLException {
		MySqlConnector.execute("update mark set x = " + mk.x + ", y = " + mk.y + ", name = " + mk.name
				+ ", description = " + mk.description + " where id = " + Integer.toString(mk.id));
	}
}
