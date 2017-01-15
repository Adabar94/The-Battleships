package gui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import core.Resources.Constants;

/**
 * Dummy frame for all game phases
 * @author Adam
 *
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	/**
	 * Constructor
	 */
	public GameFrame() {
		super(Constants.TITLE);
        setUndecorated(true);
        setVisible(true);
        setResizable(false);
		try {
			setIconImage(ImageIO.read(new File(Constants.ICON_PATH)));
		} catch (IOException e) {
			System.err.println("Icon not found!");
		}
    }
}
