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

import core.Info;
import core.Resources;
import core.Resources.Constants;
import gui.PreparationPhase;
import gui.WarPhase;

/**
 * Ally grid
 * 
 * @author Adam Barák
 *
 */
@SuppressWarnings("serial")
public class AllyGrid extends JPanel {
	Cell[][] coordinate = new Cell[15][15];

	/**
	 * Constructor of Ally grid
	 */
	public AllyGrid() {
		setLayout(new GridLayout(15, 15, 0, 0));
		setBorder(new LineBorder(new Color(0, 163, 211), 3));
		setOpaque(true);

		for (int i = 0; i < coordinate.length; i++) {
			for (int j = 0; j < coordinate[i].length; j++) {
				coordinate[j][i] = new Cell(j, i);
				add(coordinate[j][i]);
			}
		}
	}

	/**
	 * Receive hit from opponent
	 * 
	 * @param X
	 *            - x coordinate
	 * @param Y
	 *            - y coordinate
	 */
	public void shoot(char X, char Y) {
		int x = (int) (X) - 65;
		int y = (int) (Y) - 65;
		coordinate[x][y].setShoot();
		coordinate[x][y].setBackgroundImage();
		coordinate[x][y].repaint();

		if (coordinate[x][y].getShip() != 0 && isSinked(x, y, 2)) {
			WarPhase.shipCounter.removeAllyShip(getSinkedShipSize(x, y));
		}
	}

	public boolean isSinked(int x, int y, int cont) {
		if (x < 0 || x > 15 || y < 0 || y > 15 || coordinate[x][y].ship == 0) {
			return true;
		}
		if (!coordinate[x][y].shoot) {
			return false;
		}
		boolean topNbot = true;
		if (coordinate[x][y].ship == 1) {
			topNbot = (y == 0 || coordinate[x][y - 1].ship == 0 || coordinate[x][y - 1].shoot) && isSinked(x, y + 1, 2);
			if (y != 0 && coordinate[x][y - 1].ship != 0 && coordinate[x][y - 1].shoot) {
			}
		}
		if (cont == 0) {
			return isSinked(x - 1, y, 0) && topNbot;
		}
		if (cont == 1) {
			return isSinked(x + 1, y, 1) && topNbot;
		}
		return isSinked(x - 1, y, 0) && isSinked(x + 1, y, 1) && topNbot;
	}

	public int getSinkedShipSize(int x, int y){
		int size = 0;
		coordinate[x][y].setSink();
		size++;
		for(int i = x-1; i <= x+1; i++){
			for(int j = y-1; j <= y+1; j++){
				if((i == x && j == y) || i < 0 || i > 15 || j < 0 || j > 15){
					continue;
				}
				if(coordinate[i][j].getShip() != 0 && !coordinate[i][j].sinked){
					size+=getSinkedShipSize(i, j);
				}
				if(coordinate[i][j].getShip() == 0){
					coordinate[i][j].setShoot();
					coordinate[i][j].setBackgroundImage();
					coordinate[i][j].repaint();
				}
			}
		}
		return size;
	}

	/**
	 * Add ship on ally grid
	 * 
	 * @param shipID
	 *            - ship id
	 * @param x
	 *            - x coordinate of ship core
	 * @param y
	 *            - y coordinate of ship core
	 */
	public void addShip(int shipID, int x, int y) {
		if (shipID == 1) {
			if (!isOccupied(x - 3, x + 3, y - 1, y + 1)) {
				return;
			}
			if (!isReadyForShip(x - 2, x + 2, y, y)) {
				return;
			}
			if (!isOccupied(x - 2, x + 2, y - 1, y - 2)) {
				return;
			}
			if (!isReadyForShip(x - 1, x + 1, y - 1, y - 1)) {
				return;
			}
			coordinate[x - 2][y].setShip(shipID, 0);
			coordinate[x - 1][y].setShip(shipID, 1);
			coordinate[x][y].setShip(shipID, 2);
			coordinate[x + 1][y].setShip(shipID, 3);
			coordinate[x + 2][y].setShip(shipID, 4);
			coordinate[x - 1][y - 1].setShip(shipID, 5);
			coordinate[x + 1][y - 1].setShip(shipID, 6);
		} else if (shipID < 4) {
			if (!isOccupied(x - 2, x + 3, y - 1, y + 1)) {
				return;
			}
			if (!isReadyForShip(x - 1, x + 2, y, y)) {
				return;
			}
			coordinate[x - 1][y].setShip(shipID, 0);
			coordinate[x][y].setShip(shipID, 1);
			coordinate[x + 1][y].setShip(shipID, 2);
			coordinate[x + 2][y].setShip(shipID, 3);

		} else if (shipID < 7) {
			if (!isOccupied(x - 2, x + 2, y - 1, y + 1)) {
				return;
			}
			if (!isReadyForShip(x - 1, x + 1, y, y)) {
				return;
			}
			coordinate[x - 1][y].setShip(shipID, 0);
			coordinate[x][y].setShip(shipID, 1);
			coordinate[x + 1][y].setShip(shipID, 2);

		} else if (shipID < 11) {
			if (!isOccupied(x - 1, x + 2, y - 1, y + 1)) {
				return;
			}
			if (!isReadyForShip(x, x + 1, y, y)) {
				return;
			}
			coordinate[x][y].setShip(shipID, 0);
			coordinate[x + 1][y].setShip(shipID, 1);
		} else if (shipID < 15) {
			if (!isOccupied(x - 1, x + 1, y - 1, y + 1)) {
				return;
			}
			if (!isReadyForShip(x, x, y, y)) {
				return;
			}
			coordinate[x][y].setShip(shipID);
		} else {
			return;
		}
		PreparationPhase.actShipId = 0;
		PreparationPhase.shipsLeft--;

	}

	/**
	 * Checks if it's possible to place ship on
	 * 
	 * @param fromX
	 * @param toX
	 * @param fromY
	 * @param toY
	 * @return true if ship is placable, false if not placable
	 */
	public boolean isReadyForShip(int fromX, int toX, int fromY, int toY) {
		for (int x = fromX; x <= toX; x++) {
			for (int y = fromY; y <= toY; y++) {
				if (x < 0 || y < 0 || x > 14 || y > 14 || coordinate[x][y].getShip() > 0) {
					Info.error("Zde loï nelze umístit. Pøekrývala by se s jinou lodí, nebo by byla mimo herní møížku.");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks if place with coordinates is occupied or not
	 * 
	 * @param fromX
	 * @param toX
	 * @param fromY
	 * @param toY
	 * @return true if place is not occupied, false if place is occupied
	 */
	public boolean isOccupied(int fromX, int toX, int fromY, int toY) {
		for (int x = fromX; x <= toX; x++) {
			for (int y = fromY; y <= toY; y++) {
				if (x < 0 || y < 0 || x > 14 || y > 14) {
					continue;
				}
				if (coordinate[x][y].getShip() > 0) {
					Info.error("Lodì se nesmìjí dotýkat.");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * One cell of ally grid
	 * 
	 * @author Adam Barák
	 *
	 */
	public class Cell extends JButton {
		int ship;
		int part;
		boolean shoot;
		boolean sinked;
		String imagePath;

		/**
		 * Getter of ship
		 * 
		 * @return ship id
		 */
		public int getShip() {
			return ship;
		}

		/**
		 * Setter of ship
		 * 
		 * @param ship
		 *            - ship id
		 */
		public void setShip(int ship) {
			setShip(ship, 0);
		}

		/**
		 * Setter of ship and part
		 * 
		 * @param ship
		 *            - ship id
		 * @param part
		 *            - part of ship
		 */
		public void setShip(int ship, int part) {
			this.part = part;
			this.ship = ship;
			setBackgroundImage();
			repaint();

		}

		/**
		 * Set cell as wrecked
		 */
		public void setShoot() {
			this.shoot = true;
		}

		public void setSink() {
			sinked = true;
		}

		/**
		 * Constructor
		 * 
		 * @param x
		 *            coordinate
		 * @param y
		 *            coordinate
		 */
		public Cell(int x, int y) {
			super();
			ship = 0;
			part = 0;
			setPreferredSize(new Dimension(19, 19));
			setBorder(new LineBorder(Color.WHITE, 1));
			setBackgroundImage();
			shoot = false;
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (PreparationPhase.actShipId != 0) {
						Resources.ally.addShip(PreparationPhase.actShipId, x, y);
					}
				}
			});
		}

		/**
		 * Sets background of cell according to ship and part
		 */
		public void setBackgroundImage() {
			if (ship == 0) {
				if (shoot) {
					imagePath = Constants.WATER_SH_EN_PATH;
				} else {
					imagePath = Constants.WATER_PATH;
				}
			} else if (shoot) {
				imagePath = Constants.HIT_PATH;
			} else {
				if (ship == 1) {
					switch (part) {
					case 0:
						imagePath = Constants.SHIP_NOSE_PATH;
						break;
					case 1:
					case 3:
						imagePath = Constants.SHIP_BODY_CHIMNEY_PATH;
						break;
					case 2:
						imagePath = Constants.SHIP_BODY_PATH;
						break;
					case 4:
						imagePath = Constants.SHIP_STERN_PATH;
						break;
					case 5:
					case 6:
						imagePath = Constants.SHIP_CHIMNEY_PATH;
						break;
					}
					return;
				}
				if (ship < 4) {
					switch (part) {
					case 0:
						imagePath = Constants.SHIP_NOSE_PATH;
						break;
					case 1:
					case 2:
						imagePath = Constants.SHIP_BODY_PATH;
						break;
					case 3:
						imagePath = Constants.SHIP_STERN_PATH;
						break;
					}
					return;
				}
				if (ship < 7) {
					switch (part) {
					case 0:
						imagePath = Constants.SHIP_NOSE_PATH;
						break;
					case 1:
						imagePath = Constants.SHIP_BODY_PATH;
						break;
					case 2:
						imagePath = Constants.SHIP_STERN_PATH;
						break;
					}
					return;
				}
				if (ship < 11) {
					switch (part) {
					case 0:
						imagePath = Constants.SHIP_NOSE_PATH;
						break;
					case 1:
						imagePath = Constants.SHIP_STERN_PATH;
						break;
					}
					return;
				}
				imagePath = Constants.SHIP_SUB_PATH;
			}
		}

		/**
		 * Paints cell with ship background
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			try {
				g.drawImage(ImageIO.read(new File(imagePath)), 0, 0, null);
			} catch (IOException e) {
				System.err.println("Nenalezen obrázek lodì!");
			}
		}
	}
}
