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

@SuppressWarnings("serial")
public class EnemyGrid extends JPanel {
	Cell[][] coordinate = new Cell[15][15];

	public EnemyGrid() {
		setLayout(new GridLayout(15, 15, 0, 0));
		setBorder(new LineBorder(Color.WHITE, 1));
		setOpaque(true);

		for (int i = 0; i < coordinate.length; i++) {
			for (int j = 0; j < coordinate[i].length; j++) {
				coordinate[i][j] = new Cell(i, j);
				add(coordinate[i][j]);
			}
		}
	}

	public void shoot(char X, char Y, boolean isShip, boolean isSink) {
		int x = (int) (X) - 65;
		int y = (int) (Y) - 65;
		coordinate[x][y].setShip(isShip);
		coordinate[x][y].setShoot();
		coordinate[x][y].setBackgroundImage();
		if (isSink) {
			sinked(x, y);
		}
		coordinate[x][y].repaint();
	}

	public void sinked(int x, int y) {
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (coordinate[i][j].isShip()) {
					sinked(i, j);
				} else {
					coordinate[i][j].setShip(false);
					coordinate[i][j].setShoot();
				}
			}
		}
	}

	public class Cell extends JButton {
		boolean ship;
		boolean shoot;
		String imagePath;

		public boolean isShip() {
			return ship;
		}

		public void setShip(boolean isShip) {
			this.ship = isShip;
		}

		public boolean isShoot() {
			return shoot;
		}

		public void setShoot() {
			shoot = true;
		}

		public Cell(int x, int y) {
			super();
			setPreferredSize(new Dimension(19, 19));
			setBorder(new LineBorder(Color.WHITE, 1));
			setBackground(new Color(0, 163, 211));
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(Resources.isOurTurn){
						Coders.sendAttackPosition(x, y);
					}
				}
			});
			imagePath = Constants.FOG_PATH;
			shoot = false;
		}
		
		

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
