package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import core.Resources;
import core.Resources.Constants;

@SuppressWarnings("serial")
public class WarPhase extends GameDialog {
	public static BotInfoPane botInfoPane;
	public static TurnInfoPane turnInfoPane;
	public static ShipCounter shipCounter;

	public WarPhase() {
		super();
		setMenu();
		setContentPane(contentPane());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JPanel contentPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(top(), BorderLayout.NORTH);
		pane.add(mid(), BorderLayout.CENTER);
		pane.add(bot(), BorderLayout.SOUTH);
		return pane;
	}

	private JPanel top() {
		JPanel pane = new JPanel(new GridBagLayout());
		pane.setPreferredSize(new Dimension(pane.getPreferredSize().width, 80));
		pane.setBackground(new Color(217, 217, 217));
		JPanel content = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(new File(Constants.IMG_PATH + "valecna_faze" + Constants.IMG_FORMATE)), 0,
							0, getWidth(), getHeight(), this);
				} catch (IOException e) {
					System.err.println("Background not found!");
				}
			}
		};
		content.setPreferredSize(new Dimension(184, 23));
		pane.add(content);
		return pane;
	}

	private JPanel mid() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		JPanel ally = new JPanel();
		ally.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		ally.add(Resources.ally);
		pane.add(ally, BorderLayout.WEST);
		JPanel mid = new JPanel(new BorderLayout());
		turnInfoPane = new TurnInfoPane();
		mid.add(turnInfoPane, BorderLayout.NORTH);
		shipCounter = new ShipCounter();
		mid.add(shipCounter, BorderLayout.SOUTH);
		pane.add(mid, BorderLayout.CENTER);
		JPanel enemy = new JPanel();
		enemy.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		enemy.add(Resources.enemy);
		pane.add(enemy, BorderLayout.EAST);
		return pane;
	}

	private JPanel bot() {
		JPanel pane = new JPanel(new GridBagLayout());
		pane.setPreferredSize(new Dimension(pane.getPreferredSize().width, 80));
		botInfoPane = new BotInfoPane();
		pane.add(botInfoPane);
		return pane;
	}

	public class TurnInfoPane extends JPanel {
		String turnImg;

		public TurnInfoPane() {
			getTurnImg();
			setPreferredSize(new Dimension(142, 54));
		}

		public void getTurnImg() {
			if (Resources.isOurTurn) {
				turnImg = "yourturn";
			} else {
				turnImg = "enemyturn";
			}
			repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			try {
				g.drawImage(ImageIO.read(new File(Constants.IMG_PATH + turnImg + Constants.IMG_FORMATE)), 0, 0,
						getWidth(), getHeight(), this);
			} catch (IOException e) {
				System.err.println("Background not found!");
			}
		}
	}

	public class BotInfoPane extends JPanel {
		public JPanel botInfoPane;
		public String botInfoPaneImg;

		public BotInfoPane() {
			botInfoPaneImg = "game_ready";
			setPreferredSize(new Dimension(242, 29));
		}

		public void sync(String event) {
			switch (event) {
			case "miss":
				botInfoPaneImg = "missed_shot";
				break;
			case "hit":
				botInfoPaneImg = "hit_shot";
				break;
			case "sink":
				botInfoPaneImg = "sinked_shot";
				break;
			}
			repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			try {
				g.drawImage(ImageIO.read(new File(Constants.IMG_PATH + botInfoPaneImg + Constants.IMG_FORMATE)), 0, 0,
						getWidth(), getHeight(), this);
			} catch (IOException e) {
				System.err.println("Background not found!");
			}
		}
	}

	public class ShipCounter extends JPanel {
		public Row row[] = new Row[5];

		public ShipCounter() {
			super(new GridLayout(5, 1, 0, 5));
			row[0] = new Row(1, Constants.SHIP_HEPT_PATH);
			row[1] = new Row(2, Constants.SHIP_QUAD_PATH);
			row[2] = new Row(3, Constants.SHIP_TRIO_PATH);
			row[3] = new Row(4, Constants.SHIP_DOUB_PATH);
			row[4] = new Row(4, Constants.SHIP_SUBM_PATH);

			for (Row item : row) {
				add(item);
			}
		}

		public void removeAllyShip(int shipSize) {
			removeShip(shipSize, "ally");
		}

		public void removeEnemyShip(int shipSize) {
			removeShip(shipSize, "enemy");
		}

		public void removeShip(int shipSize, String grid) {
			int shipID;
			switch (shipSize) {
			case 1:
				shipID = 4;
				break;
			case 2:
				shipID = 3;
				break;
			case 3:
				shipID = 2;
				break;
			case 4:
				shipID = 1;
				break;
			case 7:
				shipID = 0;
				break;
			default:
				return;
			}
			if(grid == "ally"){
				row[shipID].ally.decreaseAndRepaint();
			} else {
				row[shipID].enemy.decreaseAndRepaint();
			}
		}

		public class Row extends JPanel {
			public Number ally;
			public Number enemy;

			public Row(int numberOfShips, String shipImage) {
				super(new BorderLayout());

				ally = new Number(numberOfShips);
				add(ally, BorderLayout.WEST);
				JPanel shipPane = new JPanel() {
					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);
						try {
							g.drawImage(ImageIO.read(new File(shipImage)), 0, 0, getWidth(), getHeight(), this);
						} catch (IOException e) {
							System.err.println("Background not found!");
						}
					}
				};
				shipPane.setPreferredSize(new Dimension(70, 40));
				add(shipPane, BorderLayout.CENTER);
				enemy = new Number(numberOfShips);
				add(enemy, BorderLayout.EAST);
			}

			public void repaintNumbers() {
				ally.repaint();
				enemy.repaint();
			}

			public class Number extends JPanel {
				public int number;
				
				public Number(int initNum){
					super();
					setPreferredSize(new Dimension(30, 40));
					number = initNum;
				}
				
				public void decreaseAndRepaint(){
					number--;
					repaint();
				}
				
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					try {
						g.drawImage(
								ImageIO.read(new File(Constants.IMG_PATH + number + Constants.IMG_FORMATE)),
								0, 0, getWidth(), getHeight(), this);
					} catch (IOException e) {
						System.err.println("Background not found!");
					}
				}
			}
		}
	}

}
