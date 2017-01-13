package core;

import javax.swing.JDialog;

import connection.Coders;
import connection.Connection;
import connection.Reader;
import connection.Writer;
import game.AllyGrid;
import game.EnemyGrid;
import gui.*;

/**
 * Main class and core of application
 * @author Adam Bar�k
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

		new LoginPhase(new GameFrame());
		
		Resources.ally = new AllyGrid();
		Resources.enemy = new EnemyGrid();
		
		connection = new Connection(Config.serverIP, Config.serverPort);
		Resources.writer = new Writer(connection.getSocket());
		Resources.reader = new Reader(connection.getSocket());
		
		new PreparationPhase(new GameFrame());
		
		Coders.sendLoginMessage();
		
		JDialog wait = Info.waitingForSecondPlayer();
		try {
			Resources.gameIsReady.acquire();
		} catch (InterruptedException e) {
			System.err.println("Semafor naru�en!");
		}
		
		wait.dispose();
		new WarPhase(new GameFrame());
	}
	
	/**
	 * Terminates communication with server
	 */
	public static void closeCom(){
		try{
			Resources.reader.interrupt();
		} catch (NullPointerException e) {
			System.err.println("Reader je ji� uzav�en");
		}
		try{
			Coders.sendExitMessage();
			Resources.writer.close();
			connection.close();
		} catch (NullPointerException e){
			System.err.println("Komunikace je ji� ukon�ena");
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
