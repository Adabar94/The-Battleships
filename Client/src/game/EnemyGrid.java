package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import connection.Coders;
import core.Resources;
import core.Resources.Constants;

/**
 * Enemy grid
 * 
 * @author Adam Barák
 *
 */
@SuppressWarnings("serial")
public class EnemyGrid extends JPanel {
	Cell[][] coordinate = new Cell[15][15];

	/**
	 * Constructor for enemy grid
	 */
	public EnemyGrid() {
		setLayout(new GridLayout(15, 15, 0, 0));
		setBorder(new LineBorder(Color.WHITE, 1));
		setOpaque(true);

		for (int i = 0; i < coordinate.length; i++) {
			for (int j = 0; j < coordinate[i].length; j++) {
				coordinate[j][i] = new Cell(j, i);
				add(coordinate[j][i]);
			}
		}
	}

	/**
	 * Shooting enemy grid - response from server
	 * @param X - x coordinate of shooting
	 * @param Y - y coordinate of shooting
	 * @param isShip - is there ship on position
	 * @param isSink - is the ship sinked
	 */
	public void shoot(char X, char Y, boolean isShip, boolean isSink) {
		int x = (int) (X) - 65;
		int y = (int) (Y) - 65;
		coordinate[x][y].setShip(isShip);
		coordinate[x][y].setShoot();
		coordinate[x][y].setBackgroundImage();
		coordinate[x][y].repaint();
		if (isSink) {
			sinked(x, y);
		}
	}

	/**
	 * Ship on x, y is sinked reveal coordinates around
	 * @param x coordinate of part of sinked ship
	 * @param y coordinate of part of sinked ship
	 */
	public void sinked(int x, int y) {
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (coordinate[i][j].isShip()) {
					sinked(i, j);
				} else {
					coordinate[i][j].setShip(false);
					coordinate[i][j].setShoot();
					coordinate[x][y].setBackgroundImage();
					coordinate[x][y].repaint();
				}
			}
		}
	}

	/**
	 * Cell of enemy grid
	 * @author Adam Barák
	 *
	 */
	public class Cell extends JButton {
		boolean ship;
		boolean shoot;
		String imagePath;

		/**
		 * Is there ship on cell
		 * @return
		 */
		public boolean isShip() {
			return ship;
		}

		/**
		 * Set if there is ship on cell
		 * @param isShip
		 */
		public void setShip(boolean isShip) {
			this.ship = isShip;
		}

		/**
		 * Set area wrecked
		 */
		public void setShoot() {
			shoot = true;
		}

		/**
		 * Constructor
		 * @param x - coordinate
		 * @param y - coordinate
		 */
		public Cell(int x, int y) {
			super();
			setPreferredSize(new Dimension(19, 19));
			setBorder(new LineBorder(Color.WHITE, 1));
			setBackground(new Color(0, 163, 211));
			imagePath = Constants.FOG_PATH;
			shoot = false;
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (Resources.isOurTurn) {
						Coders.sendAttackPosition(x, y);
					}
				}
			});
		}
		/**
		* Select background
		*/
		public void setBackgroundImage() {
			if (shoot) {
				if (ship) {
					imagePath = Constants.HIT_PATH;
				} else {
					imagePath = Constants.WATER_PATH;
				}
			} else {
				imagePath = Constants.FOG_PATH;
			}
		}

		/**
		 * Paint background
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			try {
				g.drawImage(ImageIO.read(new File(imagePath)), 0, 0, null);
			} catch (IOException e) {
				System.err.println("Background not found in images folder!");
			}
		}
	}
}
