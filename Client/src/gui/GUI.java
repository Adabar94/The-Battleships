package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import core.Events;
import core.Resources.Constants;
import gui.menu.MenuBar;

public abstract class GUI {
	public static Frame mainFrame;
	
	public static void createNewGame(){
		Panel startPanel = new Panel();
		Panel innerStartPanel = new Panel();

		
		innerStartPanel.setPreferredSize(new Dimension(Constants.STARTWIDTH-Constants.STARTHGAP,Constants.STARTHEIGHT-Constants.STARTVGAP));
		
		GridLayout innerLayout = new GridLayout(0,2);
		
		innerStartPanel.setLayout(innerLayout);
		innerLayout.setHgap(Constants.STARTHGAP);
		innerLayout.setVgap(Constants.STARTVGAP);
		
		Label label[] = {
				new Label(Constants.NICKNAMEADD), 
				new Label(Constants.SERVERNAMEADD), 
				new Label(Constants.PORTNUMBERADD)};
		TextField field[] = {
				new TextField(), 
				new TextField(), 
				new TextField()};
		Button button[] = {
				new Button(Constants.EXIT, Events.EXIT), 
				new Button(Constants.CONTINUE, Events.BEGIN, field, 3)};
		
		for(int i = 0; i < label.length; i++){
			innerStartPanel.add(label[i]);
			innerStartPanel.add(field[i]);
		}
		
		for(int i = 0; i < button.length; i++){
			innerStartPanel.add(button[i]);
		}
		
		startPanel.add(innerStartPanel, BorderLayout.CENTER);
		
		mainFrame = new Frame(Constants.START, startPanel, Constants.STARTWIDTH, Constants.STARTHEIGHT, Events.exitOnClose());
	}
	
	public static void createMainFrame(){
		mainFrame = new Frame(Constants.TITLE, new Panel(), Constants.DEFWIDTH, Constants.DEFHEIGHT, Events.exitOnClose(), (JMenuBar) new MenuBar());
	}
}
