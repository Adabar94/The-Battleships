package core;

import cli.CommandLine;
import connection.Connect;
import gui.*;

public class Main {

	public static void main(String[] args) {
		Config.config();

		if (args.length > 0) {
			CommandLine.parseArgs(args);
		}

		GUI.createNewGame();
		// TO DO: postaveni lodi GUI.createPlayground();

		if(!Connect.open(Config.serverIP, Config.serverPort)){
			//exit
		};
	}
}
