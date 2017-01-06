package core;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import core.Resources.Constants;
import core.Resources.Errors;
import gui.*;

public abstract class Events {
	
	public static Object[] object = new Object[8];
	
	/**
	 * Nová hra
	 */
	public static ActionListener START = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			GUI.mainFrame.closeFrame();
			GUI.createNewGame();
		}
	};
	/**
	 * 
	 */
	public static ActionListener BEGIN = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			GUI.mainFrame.closeFrame();
			
			int numberOfInputs = 3;
			
			TextField[] textField = new TextField[numberOfInputs];
			String[] input = new String[numberOfInputs];
			
			// object -> textfield
			for(int i = 0; i < 3; i++){
				textField[i] = (TextField) object[i];
				input[i] = textField[i].getText();
			}
			
			// Check nickname
			for(int i = 0; i < input[0].length(); i++){
				if(!Resources.inBetween((int) input[0].charAt(i), 48, 57) && 
						!Resources.inBetween((int) input[0].charAt(i), 65, 90) &&
						!Resources.inBetween((int) input[0].charAt(i), 97, 122) &&
						(int) input[0].charAt(i) != 45 && 
						(int) input[0].charAt(i) != 46
						){
					System.out.println(Errors.NOT_SUPPORTED_YET+": Error, nepovolene znaky ve jmene");
					exit();
				}
			}
			
			// Check IP Server
			for(int i = 0; i < input[1].length(); i++){
				if(input[1].charAt(i)!='.' && !Resources.inBetween((int) input[1].charAt(i), 48, 57)){
					System.out.println("Not supported yet: Error, nepovolene znaky v IP serveru");
					exit();
				}
			}
			
			// set NickName
			String name;
			if(input[0].length() == 0){
				name = input[1]+"-"+input[2];
			} else {
				name = input[0];
			}
			
			// set IP server
			String IP = input[1];
			if(input[1].length() == 0){
				System.out.println("Not supported yet: Error, zadani IP serveru je povinne");
				exit();
			}
			
			// check & set port
			int port = 0;
			try {
				port = Integer.parseInt(input[2]);
			} catch (NumberFormatException exc) {
				System.out.println("Not supported yet: Error, spatne zadany port");
				exit();
			}
			
			// Todo poslat variables do connection
			System.out.println(name+", "+IP+":"+port);
			
			GUI.mainFrame.closeFrame();
			GUI.createMainFrame();
		}
	};
	
	
	/**
	 * Ukonèení aplikace
	 */
	public static ActionListener EXIT = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			exit();
		}
	};
	public static void exit(){	
		System.exit(0);
	}
	
	public static WindowAdapter exitOnClose(){
		return new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                core.Events.exit();
            }
		};
	}
	
	public static ActionListener GRAPHIC = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			Panel contentPane = new Panel();
			
			GridLayout layout = new GridLayout(1,2);
			contentPane.setLayout(layout);
			layout.setHgap(Constants.STARTHGAP);
			layout.setVgap(Constants.STARTVGAP);
			
			Label resolution = new Label(Constants.RESOLUTION);
			
			contentPane.add(resolution);
			
			Frame frame = new Frame(Constants.GRAPHIC, contentPane, Constants.OPTWIDTHFOR2COLS, Constants.OPTHEIGHTFORROW);
			
			frame.finish();
			
			
		}};
	public static ActionListener SOUND = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			System.out.println("Not supported yet");
		}
	};
	public static ActionListener CONNECTION = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			System.out.println("Not supported yet");
		}
	};
	public static ActionListener HOWPLAY = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			System.out.println("Not supported yet");
		}
	};
	public static ActionListener RULES = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			System.out.println("Not supported yet");
		}
	};
	public static ActionListener ABOUT = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			System.out.println("Not supported yet");
		}
	};

}
