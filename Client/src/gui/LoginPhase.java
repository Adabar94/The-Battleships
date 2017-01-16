package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Config;
import core.Info;

/**
 * Login dialog
 * @author Adam Barák
 *
 */
@SuppressWarnings("serial")
public class LoginPhase extends GameDialog {
	String nick;
	String server;
	int port;

	JTextField nicknameField = new JTextField(Config.nick);
	JTextField serverField = new JTextField(Config.serverIP);
	JTextField portField = new JTextField(Integer.toString(Config.serverPort));

	/**
	 * Constructor
	 */
	public LoginPhase() {
		super(300, 200);
		setContentPane(getContent());
        setLocationRelativeTo(null);
		setVisible(true);
	} 

	/**
	 * Method getContent creates and returns content panel of dialog 
	 * @return content pane
	 */
	private JPanel getContent() {
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(4, 2, 10, 10));
		content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		content.setOpaque(true);

		JLabel nick = new JLabel("Pøezdívka");
		JLabel serv = new JLabel("IP Serveru:");
		JLabel port = new JLabel("Port:");

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
		JButton button = new JButton("Ukonèit");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
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
		JButton button = new JButton("Pokraèovat");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateValues()) {
					Config.nick = nick;
					Config.serverIP = server;
					Config.serverPort = port;
					setVisible(false);
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
		if(nick.isEmpty() || nick.length()>12){
			Info.error("Pøezdívka není validní. Zadejte pøezdívku o velikosti mezi 1 a 12 znaky.");
			return false;
		}
		// Validate serverIP
		final String SERVER_IP_INVALID = "IP serveru není validní. Zadejte IP ve tvaru x.x.x.x kde x je èíslo mezi 0 a 255.";
		server = serverField.getText();
		if(!server.equals("localhost")){
			String[] itemsOfIp = server.split("\\.");	
			if(itemsOfIp.length == 4){
				for(String item: itemsOfIp){
					try{
						Integer.parseInt(item);
					} catch (NumberFormatException e){
						Info.error(SERVER_IP_INVALID);
						return false;
					}
				}
			} else {
				Info.error(SERVER_IP_INVALID);
				return false;
			}
		}
		
		
		// Validate port
		final String PORT_INVALID = "Port serveru není validní. Zadejte port ve tvaru xxxx kde x je èíslo.";
		try{
			port = Integer.parseInt(portField.getText());
		} catch (NumberFormatException e){
			Info.error(PORT_INVALID);
			return false;
		}
		if(port < 0 || port > 9999){
			Info.error(PORT_INVALID);
			return false;
		}
		
		return true;
	}
}
