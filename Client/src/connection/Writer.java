package connection;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import core.Resources;

public class Writer{
	PrintWriter writer;
	
	/**
	 * Create writer on socket s
	 * @param s
	 */
	public Writer(Socket s){
		try {
			writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
		} catch (IOException e) {
			System.err.println("Cannot initialize writer!");
			Resources.isError = true;
		}
	}
	
	/**
	 * Send message to server
	 * @param msg - message
	 */
	public void sendMessage(String msg) {
		try {
			writer.println(msg);
			writer.flush();
		} catch (Exception e) {
			System.err.println("Write error");
			Resources.isError = true;
		}
	}
	
	/**
	 * End writer - close PrintWriter
	 */
	public void close() {
		writer.close();
	}
}
