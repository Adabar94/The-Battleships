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
 * @author Adam Barák
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
		game.add(new Item("Nová hra", new ActionListener() {
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
		JMenu help = new JMenu("Nápovìda");
		help.add(new Item("Jak hrát", null));
		help.add(new Item("Pravidla", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Info.info("Hra se skládá ze dvou èástí: \n" + "1) Umisování vlastních lodí\n"
						+ "2) Hledání a potápìní soupeøovıch lodí\n"
						+ "		Hráèi se støídají a postupnì støílí do poziv na oponentovì hrací møíce dokud jeden z nich nepotopí veškeré oponentovy lodì.\n");

			}
		}));
		help.add(new Item("O høe", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Info.info(
						"Hra byla vytvoøena studentem Západoèeké univezity v Plzni v rámci semestrální práce z pøedmìtu Úvod do poèítaèovích sítí.\n Autorem je Adam Barák");
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
