package gui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import core.Resources.Constants;

public abstract class InfoWindows {
	public static void error(String text) {
		JDialog dialog = new JOptionPane(text, JOptionPane.ERROR_MESSAGE).createDialog("Error");
		create(dialog);
	}
	
	public static void info(String text) {
		JDialog dialog = new JOptionPane(text, JOptionPane.INFORMATION_MESSAGE).createDialog(Constants.TITLE);
		create(dialog);
	}

	private static void create(JDialog dialog) {
		dialog.setAlwaysOnTop(true);
		try {
			dialog.setIconImage(ImageIO.read(new File(Constants.ICON_PATH)));
		} catch (IOException e) {
			System.err.println("Icon not found!");
		}
		dialog.setVisible(true);
	}
}
