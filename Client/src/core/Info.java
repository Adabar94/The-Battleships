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
	 * Waiting for second player
	 */
	public static JDialog waitingForSecondPlayer() {
		final JDialog dialog = new JDialog();
		dialog.setTitle(Constants.TITLE);
		dialog.setContentPane(new JOptionPane("Èekání na druhého hráèe", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null));
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				Info.doYouWannaExit();
			}
		});
		try {
			dialog.setIconImage(ImageIO.read(new File(Constants.ICON_PATH)));
		} catch (IOException e) {
			System.err.println("Icon not found!");
		}
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		return dialog;
	}
	
	/**
	 * Method for show confirmation exit window
	 */
	public static void doYouWannaExit() {
		int reply =  JOptionPane.showConfirmDialog(null, "Opravdu chcete ukonèit hru?", Constants.TITLE, JOptionPane.YES_NO_OPTION);
		if(reply == JOptionPane.YES_OPTION){
			Main.exit();
		}
	}
	
	/**
	 * Method for errors calls
	 * @param text text of error
	 */
	public static void error(String text) {
		create(new JOptionPane(text, JOptionPane.ERROR_MESSAGE).createDialog("Error"));
	}
	
	/**
	 * Method for informational calls
	 * @param text of information
	 */
	public static void info(String text) {
		create(new JOptionPane(text, JOptionPane.INFORMATION_MESSAGE).createDialog(Constants.TITLE));
	}

	/**
	 * Finalize dialog and shows it
	 * @param dialog
	 */
	private static void create(JDialog dialog) {
		try {
			dialog.setIconImage(ImageIO.read(new File(Constants.ICON_PATH)));
		} catch (IOException e) {
			System.err.println("Nenalezen soubor s ikonou!");
		}
		dialog.setVisible(true);
	}
}
