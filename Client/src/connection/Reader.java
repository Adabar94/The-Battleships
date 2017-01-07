package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import core.Resources;

public class Reader extends Thread {
	Socket s;
	BufferedReader reader;
	
	public Reader(Socket s){
		this.s = s;
		start();
	}
	
	@Override
	public void run(){
		try {
			reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			while(!Resources.action.equals("exit")){
				getMessage();
			}
			reader.close();
			if(!s.isClosed()){
				s.close();
			}
		} catch (IOException e) {
			System.err.println("Cannot initialize reader!");
			e.printStackTrace();
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
			return null;
		}
	}
}
