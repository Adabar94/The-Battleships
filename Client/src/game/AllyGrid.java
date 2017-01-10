package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import core.Resources.Constants;

@SuppressWarnings("serial")
public class AllyGrid extends JPanel {
	Cell[][] coordinate = new Cell[15][15];
	
	public AllyGrid(){
		setLayout(new GridLayout(15, 15, 0, 0));
		setBorder(new LineBorder(Color.WHITE, 1));
		setOpaque(true);
		
		for(Cell[] buttonLine: coordinate){
			for(Cell button: buttonLine){
				button = new Cell();
				add(button);
			}
		}
	}
	
	public void shoot(char X, char Y){
		int x = (int) (X)-65;
		int y = (int) (Y)-65;
		coordinate[x][y].setShoot();
		coordinate[x][y].setBackgroundImage();
		coordinate[x][y].repaint();
	}
	
	public class Cell extends JButton{
		int ship;
		int part;
		boolean shoot;
		String imagePath;
		
		public int getShip() {
			return ship;
		}

		public void setShip(int ship) {
			this.ship = ship;
		}

		public int getPart() {
			return part;
		}

		public void setPart(int part) {
			this.part = part;
		}

		public boolean isShoot() {
			return shoot;
		}

		public void setShoot() {
			this.shoot = true;
		}

		public Cell(int ship, int part){
			this();
			this.ship = ship;
			this.part = part;
		}
		
		public Cell(){
			super();
			setPreferredSize(new Dimension(19, 19));
			setBorder(new LineBorder(Color.WHITE, 1));
			setBackgroundImage();
			shoot = false;
		}
		
		public void setBackgroundImage(){
			if(ship == 0){
				if(shoot){
					imagePath = Constants.WATER_SH_EN_PATH;
				} else {
					imagePath = Constants.WATER_PATH;
				}
			} else if (shoot) {
				imagePath = Constants.HIT_PATH;
			} else {
				if(ship == 1){
					switch(part){
					case 0: imagePath = Constants.SHIP_HEPT0_PATH ;break;
					case 1: imagePath = Constants.SHIP_HEPT1_PATH ;break;
					case 2: imagePath = Constants.SHIP_HEPT2_PATH ;break;
					case 3: imagePath = Constants.SHIP_HEPT3_PATH ;break;
					case 4: imagePath = Constants.SHIP_HEPT4_PATH ;break;
					case 5: imagePath = Constants.SHIP_HEPT5_PATH ;break;
					case 6: imagePath = Constants.SHIP_HEPT6_PATH ;break;
					case 7: imagePath = Constants.SHIP_HEPT7_PATH ;break;
					}
					return;
				}
				if(ship < 4){
					switch(part){
					case 0: imagePath = Constants.SHIP_QUAD0_PATH ;break;
					case 1: imagePath = Constants.SHIP_QUAD1_PATH ;break;
					case 2: imagePath = Constants.SHIP_QUAD2_PATH ;break;
					case 3: imagePath = Constants.SHIP_QUAD3_PATH ;break;
					}
					return;
				}
				if(ship < 7){
					switch(part){
					case 0: imagePath = Constants.SHIP_TRIPLE0_PATH ;break;
					case 1: imagePath = Constants.SHIP_TRIPLE1_PATH ;break;
					case 2: imagePath = Constants.SHIP_TRIPLE2_PATH ;break;
					}
					return;
				}
				if(ship < 11){
					switch(part){
					case 0: imagePath = Constants.SHIP_DOUBLE0_PATH ;break;
					case 1: imagePath = Constants.SHIP_DOUBLE1_PATH ;break;
					}
					return;
				}
				imagePath = Constants.SHIP_SUB_PATH;
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
