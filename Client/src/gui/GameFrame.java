package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import core.Main;
import core.Resources;
import core.Resources.Constants;
import game.AllyGrid;
import game.EnemyGrid;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	public GameFrame() {
		setTitle(Constants.TITLE);
		try {
			setIconImage(ImageIO.read(new File(Constants.ICON_PATH)));
		} catch (IOException e) {
			System.err.println("Icon not found!");
		}
		setSize(new Dimension(Constants.DEF_WIDTH + 16, Constants.DEF_HEIGHT + 62));
		setJMenuBar(new Menu());
		setLocationRelativeTo(null);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        Main.exit();
		    }
		});
	}

	public void setShips() {
		Resources.gamePhase = 1;
		setVisible(false);
		ContentPanel content = new ContentPanel(Constants.SET_SHIPS_BG_PATH);
		Resources.ally = new AllyGrid();
		content.add(Resources.ally);
		JButton cont = new JButton("Pokraèovat");
		cont.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Resources.gamePhase = 2;
				setVisible(false);
			}
		});
		content.add(cont);
		setContentPane(content);
		setVisible(true);
	}

	public void playGame() {
		setVisible(false);
		ContentPanel content = new ContentPanel(Constants.PLAY_GAME_BG_PATH);
		content.add(Resources.ally);
		Resources.enemy = new EnemyGrid();
		content.add(Resources.enemy);
		setContentPane(content);
		setVisible(true);
	}

	private class ContentPanel extends JPanel {
		String bgImPath;
		
		public ContentPanel(String backgroundPath){
			super();
			bgImPath = backgroundPath;
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			try {
				g.drawImage(ImageIO.read(new File(bgImPath)), 0, 0, null);
			} catch (IOException e) {
				System.err.println("Background not found in images folder!");
			}
		}	
	}
}
