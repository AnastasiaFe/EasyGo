package ua.nure.easygo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ua.nure.easygo.entity.*;
import ua.nure.easygo.DBconnect.MySqlConnector;

public class ConnectedUser implements Runnable {

	// MySql database connector
	private MySqlConnector connector;

	// Socket for communication between two users
	private Socket s;

	// Input/output streams
	private BufferedReader in;
	private PrintWriter out;

	// Data
	private String userLogin;
	private String incomingData;

	public ConnectedUser(Socket s, MySqlConnector connector) throws IOException {
		this.connector = connector;
		this.s = s;
		this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.out = new PrintWriter(s.getOutputStream(), true);
	}

	public void run() {
		getFromStream();
	}

	/**
	 * Transfer data to client
	 * 
	 * @param data
	 */
	public void putToStream(String data) {
		out.flush();
		out.println(data);
		out.flush();
	}

	/**
	 * Get data from client
	 */
	public void getFromStream() {
		try {
			// got some data to BufferedReader
			while ((incomingData = in.readLine()) != null) {
				//
			}
		} catch (IOException ex) {
			// exception describes situation when user disconnects
			/* do nothing */
		} finally {
			try {
				s.close();
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param tagsForParse
	 *            String for parsing which contains tags (e.g. "#tag1 #tag2")
	 * @return array of Strings with tags
	 */
	private String[] parseTags(String tagsForParse) {
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

	public List<GoMap> searchByTags(String tagsForSearch) throws SQLException {
		List<GoMap> maps = new ArrayList<>();
		String[] tags = parseTags(tagsForSearch);
		for (String tag : tags) {
			synchronized (connector) {
				maps.addAll(connector.selectGoMap("select * from map where map.tags like %" + tag + "%"));
			}
		}
		return maps;
	}

	public void editMark(Mark mk) throws SQLException {
		// without possibility to move a mark
		if (!(userLogin == null) || "".equals(userLogin)) {
			synchronized (connector) {
				connector.execute("update mark set x = " + mk.x + ", y = " + mk.y + ", name = " + mk.name
						+ ", description = " + mk.description + " where id = " + Integer.toString(mk.id));
			}
		}
	}

	public Socket getSocket() {
		return s;
	}
}
