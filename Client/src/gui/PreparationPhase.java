package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import core.Info;
import core.Resources;
import core.Resources.Constants;

@SuppressWarnings("serial")
public class PreparationPhase extends GameDialog {
	public static int actShipId = 0;
	public static int shipsLeft = 14;

	public PreparationPhase() {
		super();
		setMenu();
		setContentPane(contentPane());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JPanel contentPane() {
		JPanel content = new JPanel();
		content.setBackground(new Color(217, 217, 217));

		JPanel top = new JPanel(new GridBagLayout());
		top.setPreferredSize(new Dimension(top.getPreferredSize().width, 80));
		top.setBackground(new Color(217,217,217));
		JPanel title = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(new File(Constants.IMG_PATH + "rozestaveni_lodi" + Constants.IMG_FORMATE)), 0,
							0, getWidth(), getHeight(), this);
				} catch (IOException e) {
					System.err.println("Background not found!");
				}
			}
		};
		title.setPreferredSize(new Dimension(235, 24));
		top.add(title);
		
		JPanel center = new JPanel();
		JPanel leftPane = new JPanel();
		JPanel shipsToAdd = new JPanel();
		shipsToAdd.setLayout(new GridLayout(5, 2, 0, 0));
		shipsToAdd.add(addRow((new Ship(1, 1, Constants.SHIP_HEPT_PATH))));
		shipsToAdd.add(addRow((new Ship(2, 2, Constants.SHIP_QUAD_PATH))));
		shipsToAdd.add(addRow((new Ship(4, 3, Constants.SHIP_TRIO_PATH))));
		shipsToAdd.add(addRow((new Ship(7, 4, Constants.SHIP_DOUB_PATH))));
		shipsToAdd.add(addRow((new Ship(11, 4, Constants.SHIP_SUBM_PATH))));
		leftPane.add(shipsToAdd);
		JPanel midPane = new JPanel();
		midPane.add(Resources.ally);

		FlowLayout layout = new FlowLayout();
		layout.setHgap(80);
		layout.setVgap(20);
		center.setLayout(layout);

		center.add(leftPane);
		center.add(midPane);
		
		JPanel bot = new JPanel();
		bot.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		JButton randomButton = new JButton("Náhodné rozestavìní");
		randomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (shipsLeft == 14) {
					Resources.ally.placeShipsRandom();
				} else {
					Info.error("Nìkteré lodì již byli umístìny. Nelze náhodnì rozdìlit!");
				}
			}
		});
		bot.add(randomButton);
		JButton continueButton = new JButton("Pokraèovat");
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (shipsLeft == 0) {
					setVisible(false);
				} else {
					Info.error("Pøed pokraèováním musíte rozložit všechny lodì!");
				}
			}
		});
		bot.add(continueButton);

		content.setLayout(new BorderLayout());
		content.add(top, BorderLayout.NORTH);
		content.add(center, BorderLayout.CENTER);
		content.add(bot, BorderLayout.SOUTH);

		return content;
	}

	private JButton addRow(Ship ship) {
		JButton pane = new JButton();
		pane.setLayout(new BorderLayout());
		pane.setContentAreaFilled(false);
		pane.setBorderPainted(false);
		pane.setRolloverEnabled(false);
		pane.setBorder(BorderFactory.createEmptyBorder());
		JPanel counter = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(new File(Constants.IMG_PATH + ship.getCount() + Constants.IMG_FORMATE)), 0,
							0, getWidth(), getHeight(), this);
				} catch (IOException e) {
					System.err.println("Number background not found!");
				}
			}
		};
		counter.setPreferredSize(new Dimension(30, 40));
		counter.setBorder(new LineBorder(new Color(217, 217, 217), 0));
		JPanel image = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(new File(ship.getSetShipPath())), 0, 0, getWidth(), getHeight(), this);
				} catch (IOException e) {
					System.err.println("Set ships background not found!");
				}
			}
		};
		image.setPreferredSize(new Dimension(70, 40));
		image.setBorder(new LineBorder(new Color(217, 217, 217), 0));
		pane.add(counter, BorderLayout.LINE_START);
		pane.add(image, BorderLayout.LINE_END);
		pane.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (actShipId == 0 && ship.getCount() > 0) {
					actShipId = ship.getId();
					ship.setCount(ship.getCount() - 1);
					ship.setId(ship.getId() + 1);
				}
			}
		});
		return pane;
	}

	private class Ship {
		int id;
		int count;
		String setShipPath;

		public Ship(int id, int count, String setShipPath) {
			this.id = id;
			this.count = count;
			this.setShipPath = setShipPath;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public String getSetShipPath() {
			return setShipPath;
		}
	}
}
