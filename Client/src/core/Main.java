package core;

import gui.*;

public class Main {

	public static void main(String[] argc) {
		Config.config();
		
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
