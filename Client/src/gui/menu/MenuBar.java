package gui.menu;

import javax.swing.JMenuBar;

import constants.Constants;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	public MenuBar(){
		super();
		this.add(new Menu(Constants.GAME));
		this.add(new Menu(Constants.OPTIONS));
		this.add(new Menu(Constants.HELP));
	}
}
