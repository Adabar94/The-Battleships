package gui;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.WindowConstants;

import core.Info;
import core.Resources.Constants;

/**
 * Abstract class with default Dialogue configuration
 * @author Adam
 *
 */
@SuppressWarnings("serial")
public abstract class GameDialog extends JDialog{
	
	/**
	 * Default constructor
	 */
	public GameDialog(){
		super(new GameFrame());
		setTitle(Constants.TITLE);
		setIcon();
		setCloseOperation();
		setModal(true);
		setResizable(false);
	}
	
	/**
	 * Constructor with size
	 * @param width
	 * @param height
	 */
	public GameDialog(int width, int height){
		this();
		setSize(new Dimension(width, height));
	}
	
	/**
	 * Set default game icon
	 */
	private void setIcon(){
		try {
			setIconImage(ImageIO.read(new File(Constants.ICON_PATH)));
		} catch (IOException e) {
			System.err.println("Icon not found!");
		}
	}
	
	

	/**
	 * Set default close operation
	 */
	private void setCloseOperation(){
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				Info.doYouWannaExit();
			}
		});
	}
	
	/**
	 * Sets menu to dialog
	 */
	protected void setMenu(){
		setJMenuBar(new Menu());
	}
	
	/**
	 * Set visibility of Dialogue
	 */
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (!visible) {
			((GameFrame) getParent()).dispose();
		}
	}
}
