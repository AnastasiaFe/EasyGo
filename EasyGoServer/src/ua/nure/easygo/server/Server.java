package ua.nure.easygo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ua.nure.easygo.DBconnect.*;

/**
 * Server build on Sockets and ServerSockets. When launched waits for
 * connection. When new connection is accepted creates new threat for user, puts
 * connected users into ArrayList (from Java Collection Framework) and launches
 * it.
 * 
 * @author Alex Shylin
 * @see java.net.Socket;
 * @see java.net.ServerSocket;
 * @since 1.8
 */
public class Server {
	public static List<ConnectedUser> users = Collections.synchronizedList(new ArrayList<ConnectedUser>());
	public static MySqlConnector connector = new MySqlConnector();
	
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(8086);
		System.out.println("Server is online");
		while (true) {
			Socket s = ss.accept();
			ConnectedUser user = new ConnectedUser(s, connector);
			users.add(user);
			new Thread(user).start();
		}
	}
}
