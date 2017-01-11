package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import core.Info;
import core.Main;
import core.Resources;
import core.Resources.Constants;
import game.AllyGrid;
import game.Ship;

@SuppressWarnings("serial")
public class SetShips extends JDialog {
	public static int actShipId = 0;
	public static int shipsLeft = 14;
	
	public SetShips(GameFrame frame) {
		super(frame);
		setLocationRelativeTo(frame);
		setTitle(Constants.TITLE);
		setJMenuBar(new Menu());
		setSize(new Dimension(Constants.DEF_WIDTH + 16, Constants.DEF_HEIGHT + 62));
		setContentPane(contentPane());
		setModal(true);
		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				Main.exit();
			}
		});
		setVisible(true);
	}

	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (!visible) {
			((GameFrame) getParent()).dispose();
		}
	}

	public JPanel contentPane() {
		JPanel content = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(new File(Constants.SET_SHIPS_BG_PATH)), 0, 0, getWidth(), getHeight(),
							this);
				} catch (IOException e) {
					System.err.println("Set ships background not found!");
				}
			}
		};
		JPanel shipsToAdd = new JPanel();
		shipsToAdd.setLayout(new GridLayout(5, 2, 0, 0));
		shipsToAdd.add(addRow((new Ship(1, 1, Constants.SHIP_HEPT_PATH))));
		shipsToAdd.add(addRow((new Ship(2, 2, Constants.SHIP_QUAD_PATH))));
		shipsToAdd.add(addRow((new Ship(4, 3, Constants.SHIP_TRIO_PATH))));
		shipsToAdd.add(addRow((new Ship(7, 4, Constants.SHIP_DOUB_PATH))));
		shipsToAdd.add(addRow((new Ship(11, 4, Constants.SHIP_SUBM_PATH))));
		content.add(shipsToAdd);
		
		Resources.ally = new AllyGrid();
		content.add(Resources.ally);
		JButton cont = new JButton("Pokraèovat");
		cont.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(shipsLeft == 0){
					setVisible(false);
				} else {
					Info.error("Pøed pokraèováním musíte rozložit všechny lodì!");
				}
			}
		});
		content.add(cont);
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
					g.drawImage(ImageIO.read(new File(Constants.IMG_PATH + ship.getCount() + Constants.IMG_FORMATE)), 0, 0, getWidth(), getHeight(), this);
				} catch (IOException e) {
					System.err.println("Number background not found!");
				}
			}
		};
		counter.setPreferredSize(new Dimension(32, 51));
		counter.setBorder(new LineBorder(new Color(217, 217, 217), 5));
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
		image.setPreferredSize(new Dimension(79, 51));
		image.setBorder(new LineBorder(new Color(217, 217, 217), 5));
		pane.add(counter, BorderLayout.LINE_START);
		pane.add(image, BorderLayout.LINE_END);
		pane.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(actShipId == 0 && ship.getCount() > 0){
					actShipId = ship.getId();
					ship.setCount(ship.getCount()-1);
					ship.setId(ship.getId()+1);
				}
			}
		});
		return pane;
	}
}
