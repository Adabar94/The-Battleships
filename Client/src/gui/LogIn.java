package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Config;
import core.Resources.Constants;

@SuppressWarnings("serial")
public class LogIn extends JDialog {
	String nick;
	String server;
	int port;

	JTextField nicknameField = new JTextField(Config.nick);
	JTextField serverField = new JTextField(Config.serverIP);
	JTextField portField = new JTextField(Integer.toString(Config.serverPort));

	public LogIn() {
		setTitle(Constants.TITLE);
		try {
			setIconImage(ImageIO.read(new File(Constants.ICON_PATH)));
		} catch (IOException e) {
			System.err.println("Icon not found!");
		}
		setSize(new Dimension(Constants.START_WIDTH, Constants.START_HEIGHT));
		setLocationRelativeTo(null);
		add(getContent());
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				dispose();
				System.exit(0);
		    }
		});
		setModal(true);
		setVisible(true);
	}

	private JPanel getContent() {
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(4, 2, 10, 10));
		content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		content.setOpaque(true);

		JLabel nick = new JLabel(Constants.NICKNAME);
		JLabel serv = new JLabel(Constants.SERVER_IP);
		JLabel port = new JLabel(Constants.PORT);

		content.add(nick);
		content.add(nicknameField);
		content.add(serv);
		content.add(serverField);
		content.add(port);
		content.add(portField);
		content.add(getCancelButton());
		content.add(getContinueButton());

		return content;
	}

	/**
	 * Creates cancel button
	 * 
	 * @return cancel button
	 */
	private JButton getCancelButton() {
		JButton button = new JButton(Constants.EXIT);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		return button;
	}

	/**
	 * Creates continue button
	 * 
	 * @return continue button
	 */
	private JButton getContinueButton() {
		JButton button = new JButton(Constants.CONTINUE);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateValues()) {
					Config.nick = nick;
					Config.serverIP = server;
					Config.serverPort = port;
					dispose();
				}
			}
		});
		return button;
	}

	/**
	 * Method for validating each inserted variable
	 * @return true if everything is ok
	 */
	private boolean validateValues(){
		// Validate nickname
		nick = nicknameField.getText();
		if(nick.isEmpty() || nick.length()>13){
			InfoWindows.error(Constants.NICKNAME_INVALID);
			return false;
		}
		// Validate serverIP
		server = serverField.getText();
		if(!server.equals("localhost")){
			String[] itemsOfIp = server.split("\\.");	
			if(itemsOfIp.length == 4){
				for(String item: itemsOfIp){
					try{
						Integer.parseInt(item);
					} catch (NumberFormatException e){
						InfoWindows.error(Constants.SERVER_IP_INVALID);
						return false;
					}
				}
			} else {
				InfoWindows.error(Constants.SERVER_IP_INVALID);
				return false;
			}
		}
		
		
		// Validate port
		try{
			port = Integer.parseInt(portField.getText());
		} catch (NumberFormatException e){
			InfoWindows.error(Constants.PORT_INVALID);
			return false;
		}
		if(port < 0 || port > 9999){
			InfoWindows.error(Constants.PORT_INVALID);
			return false;
		}
		
		return true;
	}
}
