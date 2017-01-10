package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import core.Resources;

public class Reader extends Thread {
	Boolean runReader = true;
	BufferedReader reader;
	
	public Reader(Socket s){
		try {
			reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			System.err.println("Cannot initialize reader!");
			Resources.isError = true;
		}
		start();
	}
	
	@Override
	public void run(){
		while(true){
			Coders.decode(getMessage());
		}
	}
	
	/**
	 * Get message from server
	 * @return message
	 */
	public String getMessage() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			System.err.println("Read error");
			Resources.isError = true;
			return null;
		}
	}
}
