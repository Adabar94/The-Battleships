package gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import core.Resources.Constants;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {
	public Menu() {
		super();
		this.add(game());
		this.add(help());
	}

	private JMenu game() {
		JMenu game = new JMenu(Constants.GAME);
		game.add(new Item(Constants.START, null));
		game.addSeparator();
		game.add(new Item(Constants.EXIT, null));
		return game;
	}

	private JMenu help() {
		JMenu help = new JMenu(Constants.HELP);
		help.add(new Item(Constants.HOWPLAY, null));
		help.add(new Item(Constants.RULES, null));
		help.add(new Item(Constants.ABOUT, null));
		return help;
	}

	private class Item extends JMenuItem {
		private Item(String name, ActionListener a) {
			this.setText(name);
			this.addActionListener(a);
		}
	}
}
