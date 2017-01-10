package connection;

import core.Config;
import core.Main;
import core.Resources;
import gui.InfoWindows;

public abstract class Coders {

	/*
	 * Receive decoding
	 */

	public static void decode(String order) {
		switch (order.charAt(0)) {
		case 'R':
			gameIsReady(order);
			break;
		case 'A':
			recvAttackResult(order);
			break;
		case 'D':
			recvDefendPosition(order);
			break;
		case 'E':
			if (order.charAt(1) == 'S') {
				InfoWindows.error("Server ukon�il komunikaci, hra je ukon�ena.");
			} else {
				InfoWindows.info("Opponent opustil hru, zv�t�zil jste.");
			}
			Main.exit();
			break;
		default:
			sendErrorMessage();
			break;
		}
	}

	public static void gameIsReady(String message) {
		Resources.isOurTurn = message.charAt(1) == 0;
		Resources.gamePhase = 3;
	}

	public static void recvAttackResult(String message) {
		message = message.toUpperCase();
		Resources.enemy.shoot(message.charAt(1), message.charAt(2),
				message.charAt(3) > 0 ? true : false, message.charAt(4) > 0 ? true : false);
		if(message.charAt(4) == 2) {
			InfoWindows.info("Zv�t�zil jste!");
			Main.exit();
		}
	}

	public static void recvDefendPosition(String message) {
		message = message.toUpperCase();
		Resources.ally.shoot(message.charAt(1), message.charAt(2));
		Resources.isOurTurn = true;
	}

	/*
	 * Sending encoding
	 */

	public static void sendLoginMessage() {
		encodeAndSend("L", String.format("%02d", Config.nick.length()) + Config.nick);
	}

	public static void sendAttackPosition(int x, int y) {
		String coordinates = ""+(char) (x+65) + (char) (y+65);
		encodeAndSend("A", coordinates);
		Resources.isOurTurn = false;
	}

	public static void sendExitMessage() {
		Resources.writer.sendMessage("E");
	}

	public static void sendErrorMessage() {
		encodeAndSend("X", "Last message was incorrect, try again please.");
	}

	public static void encodeAndSend(String prefix, String body) {
		Resources.writer.sendMessage(prefix + body);
	}

}