package core;

import javax.swing.JDialog;

import connection.Coder;
import connection.Connection;
import connection.Reader;
import connection.Writer;
import game.AllyGrid;
import game.EnemyGrid;
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

		new LoginPhase();
		
		Resources.ally = new AllyGrid();
		Resources.enemy = new EnemyGrid();
		
		connection = new Connection(Config.serverIP, Config.serverPort);
		Resources.writer = new Writer(connection.getSocket());
		Resources.reader = new Reader(connection.getSocket());
		
		new PreparationPhase();
		
		Coder.sendLoginMessage();
		
		JDialog wait = Info.waitingForSecondPlayer();
		try {
			Resources.gameIsReady.acquire();
		} catch (InterruptedException e) {
			System.err.println("Semafor narušen!");
		}
		
		wait.dispose();
		new WarPhase();
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
			Coder.sendExitMessage();
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
}
