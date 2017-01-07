package connection;

import java.io.IOException;

import java.net.Socket;

import core.Resources;

public class Connect {

	/**
	 * Open connection with server with IP address host and port port
	 * @param host
	 * @param port
	 * @return true if connection is established, false if not
	 */
	public static boolean open(String host, int port) {
		try {
			Socket s = new Socket(host, port);
			Resources.writer = new Writer(s); 
			Resources.reader = new Reader(s);
			return true;
		} catch (IOException e) {
			System.out.println("Connection to " + host + ":" + port + " refused");
			return false;
		}
	}
}