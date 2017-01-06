package cli;

import core.Config;
import core.Resources.Errors;

public abstract class CommandLine {
	public static void parseArgs(String[] args) {
		for (String arg : args) {
			String[] item = arg.split("=");
			switch (item[0]) {
			case "nick":
				Config.nick = item[1];
				break;
			case "lang":
				Config.lang = item[1];
				break;
			case "serverIP":
				Config.serverIP = item[1];
				break;
			case "serverPort":
				try {
					Config.serverPort = Integer.parseInt(item[1]);
				} catch (NumberFormatException e) {
					System.err.println(Errors.PORT_NOT_NUM);
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
