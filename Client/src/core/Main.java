package core;

import cli.CommandLine;
import connection.Coders;
import connection.Connection;
import connection.Reader;
import connection.Writer;
import gui.*;

public class Main {

	public static void main(String[] args) {
		Config.config();

		if (args.length > 0) {
			CommandLine.parseArgs(args);
		}

		new LogIn();
		GameFrame frame = new GameFrame();
		frame.setShips();

		while(Resources.gamePhase == 1);
		
		Resources.TCPClient = new Connection(Config.serverIP, Config.serverPort);

		if (Resources.isError) return;
		
		Resources.writer = new Writer(Resources.TCPClient.getSocket());
		Resources.reader = new Reader(Resources.TCPClient.getSocket());
		
		Coders.sendLoginMessage();
		
		if(Resources.gamePhase == 2){
			InfoWindows.info("Èekání na protihráèe");
			while(Resources.gamePhase == 2);
		}
		
		frame.playGame();
	}
	
	public static void closeCom(){
		try{
			Resources.reader.interrupt();
		} catch (NullPointerException e) {
			System.err.println("Reader already interrupted");
		}
		try{
			Coders.sendExitMessage();
			Resources.writer.close();
			Resources.TCPClient.close();
		} catch (NullPointerException e){
			System.err.println("Comunication already terminated");
		}
	}
	
	public static void exit(){
		closeCom();
		System.exit(0);
	}
}
