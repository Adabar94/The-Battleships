package core;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

import connection.Coders;
import connection.Connection;
import connection.Reader;
import connection.Writer;
import gui.*;

/**
 * Main class and core of application
 * @author Adam Barák
 *
 */
public class Main {
	static Connection connection;

	/**
	 * Main method and core of application
	 * @param args arguments from command line are not used
	 */
	public static void main(String[] args) {
		Config.config();

		new LogIn(new GameFrame());
		new SetShips(new GameFrame());
		
		connection = new Connection(Config.serverIP, Config.serverPort);
		Resources.writer = new Writer(connection.getSocket());
		Resources.reader = new Reader(connection.getSocket());
		
		Coders.sendLoginMessage();
		
		Info.info("Èekání na protihráèe");
		try {
			Resources.gameIsReady.acquire();
		} catch (InterruptedException e) {
			System.err.println("Semafor narušen!");
		}
		new Play(new GameFrame());
	}
	
	/**
	 * Terminates communication with server
	 */
	public static void closeCom(){
		try{
			Resources.reader.interrupt();
		} catch (NullPointerException e) {
			System.err.println("Reader je již uzavøen");
		}
		try{
			Coders.sendExitMessage();
			Resources.writer.close();
			connection.close();
		} catch (NullPointerException e){
			System.err.println("Komunikace je již ukonèena");
		}
	}
	
	/**
	 * End application
	 */
	public static void exit(){
		closeCom();
		Config.saveConfig();
		System.exit(0);
	}
	
	/*public static void restart()
	{
	  final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
	  final File currentJar = new File(MyClassInTheJar.class.getProtectionDomain().getCodeSource().getLocation().toURI());

	  if(!currentJar.getName().endsWith(".jar"))
	    return;

	  final ArrayList<String> command = new ArrayList<String>();
	  command.add(javaBin);
	  command.add("-jar");
	  command.add(currentJar.getPath());

	  final ProcessBuilder builder = new ProcessBuilder(command);
	  builder.start();
	  System.exit(0);
	}*/
}
