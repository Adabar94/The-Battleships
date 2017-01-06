package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import core.Resources.Errors;

public abstract class Config {
	public static String nick = "player";
	public static String lang = "english";
	public static String serverIP = "0.0.0.0";
	public static int serverPort = 0000;

	/**
	 * Method config reads config file and save config into variables. If config
	 * file doesn't exist, it creates it
	 */
	public static void config() {
		File configFile = new File("Config.conf");
		if (configFile.exists() && configFile.isFile()) {
			try {
				Scanner scan = new Scanner(configFile);
				while (scan.hasNextLine()) {
					String items[] = scan.nextLine().split("=");
					switch (items[0]) {
					case "nick":
						nick = items[1];
						break;
					case "lang":
						lang = items[1];
						break;
					case "serverIP":
						serverIP = items[1];
						break;
					case "serverPort":
						serverPort = Integer.parseInt(items[1]);
						break;
					}
				}
				scan.close();
			} catch (FileNotFoundException e) {
				System.err.println();
			}
		} else {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				System.err.println(Errors.CANNOT_CREATE_CONFIG);
				e.printStackTrace();
			}
			;
		}
	}

	/**
	 * Method saveConfig saves configuration into config.conf file
	 */
	public static void saveConfig() {
		try {
			PrintWriter writer = new PrintWriter("Config.conf", "UTF-8");
			writer.println("nick=" + nick);
			writer.println("lang=" + lang);
			writer.println("serverIP=" + serverIP);
			writer.println("serverPort=" + serverPort);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.err.println(Errors.CANNOT_WRITE_CONFIG);
			e.printStackTrace();
		}
	}

}
