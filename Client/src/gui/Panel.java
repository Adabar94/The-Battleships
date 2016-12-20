package gui;

import java.awt.Dimension;

import javax.swing.JPanel;

import main.Resources.Constants;
import main.Resources;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	
	public Panel(){
		super();
		updateSize(Constants.DEFWIDTH, Constants.DEFHEIGHT);
	}
	
	public Panel(int w, int h){
		super();
		setPreferredSize(new Dimension(w,h));
	}
	
	/**
	 * Updates width and height and checks if they are in bounds and in ratio
	 * @param w - Width to update
	 * @param h - Height to update
	 */
	public void updateSize(int w, int h){
		int actRatio = w/h;
		int expRatio = Constants.DEFWIDTH/Constants.DEFHEIGHT;
		
		if(Resources.inBetween(w, Constants.MINWIDTH, Constants.MAXWIDTH) && Resources.inBetween(h, Constants.MINHEIGHT, Constants.MAXHEIGHT) && 
				actRatio == expRatio){
			setPreferredSize(new Dimension(w,h));
			return;
		}
		System.err.print("Wrong size! Width must be bet");
	}
}
