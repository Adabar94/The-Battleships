package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;

import core.Main;
import core.Resources;
import core.Resources.Constants;
import game.EnemyGrid;

@SuppressWarnings("serial")
public class Play extends JDialog{
	public Play(GameFrame frame) {
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
	
	private JPanel contentPane(){
		JPanel pane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(new File(Constants.PLAY_GAME_BG_PATH)), 0, 0, getWidth(), getHeight(),
							this);
				} catch (IOException e) {
					System.err.println("Set ships background not found!");
				}
			}
		};
		pane.add(Resources.ally);
		Resources.enemy = new EnemyGrid();
		pane.add(Resources.enemy);
		return pane;
	}
}
