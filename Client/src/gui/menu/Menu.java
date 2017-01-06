package gui.menu;

import javax.swing.JMenu;

import main.Resources.Constants;
import main.Events;

@SuppressWarnings("serial")
public class Menu extends JMenu{
	public Menu(String name){
		super(name);
		switch(name){
			case Constants.GAME:
				add(new MenuItem(Constants.START, Events.START));
				addSeparator();
				add(new MenuItem(Constants.EXIT, Events.EXIT));
				;break;
			case Constants.OPTIONS: 
				add(new MenuItem(Constants.GRAPHIC, Events.GRAPHIC));
				add(new MenuItem(Constants.SOUND, Events.SOUND));
				addSeparator();
				add(new MenuItem(Constants.CONNECTION, Events.CONNECTION));
				;break;
			case Constants.HELP:
				add(new MenuItem(Constants.HOWPLAY, Events.HOWPLAY));
				add(new MenuItem(Constants.RULES, Events.RULES));
				add(new MenuItem(Constants.ABOUT, Events.ABOUT));				
				;break;
		}
	}
}
