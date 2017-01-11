package core;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import core.Resources.Constants;

/**
 * Class with pop up info windows
 * @author Adam Barák
 *
 */
public abstract class Info {
	
	/**
	 * Method for errors calls
	 * @param text text of error
	 */
	public static void error(String text) {
		JDialog dialog = new JOptionPane(text, JOptionPane.ERROR_MESSAGE).createDialog("Error");
		create(dialog);
	}
	
	/**
	 * Method for informational calls
	 * @param text of information
	 */
	public static void info(String text) {
		JDialog dialog = new JOptionPane(text, JOptionPane.INFORMATION_MESSAGE).createDialog(Constants.TITLE);
		create(dialog);
	}

	/**
	 * Finalize dialog and shows it
	 * @param dialog
	 */
	private static void create(JDialog dialog) {
		dialog.setAlwaysOnTop(true);
		try {
			dialog.setIconImage(ImageIO.read(new File(Constants.ICON_PATH)));
		} catch (IOException e) {
			System.err.println("Nenalezen soubor s ikonou!");
		}
		dialog.setVisible(true);
	}
}
