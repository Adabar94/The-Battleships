package connection;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import core.Resources;

public class Writer extends Thread {
	Socket s;
	PrintWriter writer;
	
	public Writer(Socket s){
		this.s = s;
		start();
	}
	
	@Override
	public void run(){
		try {
			writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			while(!Resources.action.equals("exit")){
				Resources.messToSendSem.acquire();
				sendMessage(Resources.messToSend);
			}
			writer.close();
			if(!s.isClosed()){
				s.close();
			}
		} catch (IOException e) {
			System.err.println("Cannot initialize writer!");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.err.println();
			e.printStackTrace();
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
		}
	}
}
