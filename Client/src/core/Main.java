package core;

import cli.CommandLine;
import gui.*;

public class Main {

	public static void main(String[] args) {
		Config.config();
		
		if(args.length == 0){
			CommandLine.parseArgs(args);
		}
		
		Thread communication = new Thread("Communication") {
			@Override
			public void run() {

			}
		};

		Thread gui = new Thread("GUI") {
			@Override
			public void run() {
				GUI.createNewGame();
			}
		};
		communication.start();
		gui.start();
	}
}
