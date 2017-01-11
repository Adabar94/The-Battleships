package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import core.Main;

/**
 * Reader of messages from server
 * 
 * @author Adam Barák
 *
 */
public class Reader extends Thread {
	Boolean runReader = true;
	BufferedReader reader;

	/**
	 * Initialize reader and start thread
	 * 
	 * @param s
	 */
	public Reader(Socket s) {
		try {
			reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			System.err.println("Nelze inicializovat reader!");
			Main.exit();
		}
		start();
	}

	/**
	 * Thread is waiting for messages and send them to decoder
	 */
	@Override
	public void run() {
		while (true) {
			Coders.decode(getMessage());
		}
	}

	/**
	 * Get message from server
	 * 
	 * @return message
	 */
	public String getMessage() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			Coders.decode("ES");
			return null;
		}
	}
}
