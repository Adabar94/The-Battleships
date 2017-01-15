package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import core.Info;
import core.Main;

/**
 * Menu bar
 * 
 * @author Adam Bar�k
 *
 */

@SuppressWarnings("serial")
public class Menu extends JMenuBar {
	
	/**
	 * Constructor of menu
	 */
	public Menu() {
		super();
		this.add(game());
		this.add(help());
	}

	/**
	 * Construct game menu bar item
	 * @return
	 */
	private JMenu game() {
		JMenu game = new JMenu("Hra");
		game.add(new Item("Ukon�it", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.exit();
			}
		}));
		return game;
	}
	
	/**
	 * Construct help menu bar item
	 * @return
	 */
	private JMenu help() {
		JMenu help = new JMenu("N�pov�da");
		help.add(new Item("Jak hr�t", null));
		help.add(new Item("Pravidla", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Info.info("Hra se skl�d� ze dvou ��st�: \n" + "1) Umis�ov�n� vlastn�ch lod�\n"
						+ "2) Hled�n� a pot�p�n� soupe�ov�ch lod�\n"
						+ "		Hr��i se st��daj� a postupn� st��l� do poziv na oponentov� hrac� m��ce dokud jeden z nich nepotop� ve�ker� oponentovy lod�.\n");

			}
		}));
		help.add(new Item("O h�e", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Info.info(
						"Hra byla vytvo�ena studentem Z�pado�ek� univezity v Plzni v r�mci semestr�ln� pr�ce z p�edm�tu �vod do po��ta�ov�ch s�t�.\n Autorem je Adam Bar�k");
			}
		}));
		return help;
	}

	/**
	 * Class of menu item
	 * @author Adam Bar�k
	 *
	 */
	private class Item extends JMenuItem {
		/**
		 * Constructor for Item class
		 * @param name - name of item
		 * @param a - action on click
		 */
		private Item(String name, ActionListener a) {
			this.setText(name);
			this.addActionListener(a);
		}
	}
}
