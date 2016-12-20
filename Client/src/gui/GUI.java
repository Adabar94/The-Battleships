package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import actions.Events;
import constants.Constants;
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
		
		mainFrame = new Frame(Constants.START, startPanel, Constants.STARTWIDTH, Constants.STARTHEIGHT);
		mainFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                actions.Events.exit();
            }
        });
		mainFrame.finish();
	}
	
	public static void createMainFrame(){
		mainFrame = new Frame(Constants.TITLE, Constants.DEFWIDTH, Constants.DEFHEIGHT);
		mainFrame.setJMenuBar(new MenuBar());
		mainFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                actions.Events.exit();
            }
        });
		mainFrame.finish();
	}
}
