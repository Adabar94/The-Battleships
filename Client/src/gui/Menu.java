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
	public Menu() {
		super();
		this.add(game());
		this.add(help());
	}

	private JMenu game() {
		JMenu game = new JMenu("Hra");
		game.add(new Item("Nov� hra", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Main.restart();
				System.out.println("TO DO");
			}
		}));
		game.addSeparator();
		game.add(new Item("Konec", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.exit();
			}
		}));
		return game;
	}

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

	private class Item extends JMenuItem {
		private Item(String name, ActionListener a) {
			this.setText(name);
			this.addActionListener(a);
		}
	}
}
