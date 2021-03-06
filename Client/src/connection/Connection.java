package connection;

import java.io.IOException;
import java.net.Socket;

/**
 * Connection to server
 * 
 * @author Adam Bar�k
 */
public class Connection {
	Socket s;

	/**
	 * Getter of socket
	 * 
	 * @return socket
	 */
	public Socket getSocket() {
		return s;
	}

	/**
	 * Open connection with server with IP address host and port port
	 * 
	 * @param host
	 * @param port
	 * @return true if connection is established, false if not
	 */
	public Connection(String host, int port) {
		try {
			s = new Socket(host, port);
		} catch (IOException e) {
			Coder.decode("EN");
		}
	}

	/**
	 * End writer - close socket
	 */
	public void close() {
		try {
			s.close();
		} catch (IOException e) {
			System.err.println("Nelze uzav��t socket!");
		}
	}
}