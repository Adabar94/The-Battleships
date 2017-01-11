package connection;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class for sending messages to server
 * 
 * @author Adam Barák
 *
 */
public class Writer {
	PrintWriter writer;

	/**
	 * Create writer on socket s
	 * 
	 * @param s
	 */
	public Writer(Socket s) {
		try {
			writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
		} catch (IOException e) {
			Coders.decode("ES");
		}
	}

	/**
	 * Send message to server
	 * 
	 * @param msg
	 *            - message
	 */
	public void sendMessage(String msg) {
		writer.println(msg);
		writer.flush();
	}

	/**
	 * End writer - close PrintWriter
	 */
	public void close() {
		writer.close();
	}
}
