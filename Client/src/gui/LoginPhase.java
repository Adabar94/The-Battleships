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
import core.Info;
import core.Main;
import core.Resources.Constants;

@SuppressWarnings("serial")
public class LoginPhase extends JDialog {
	String nick;
	String server;
	int port;

	JTextField nicknameField = new JTextField(Config.nick);
	JTextField serverField = new JTextField(Config.serverIP);
	JTextField portField = new JTextField(Integer.toString(Config.serverPort));

	public LoginPhase(GameFrame frame) {
		super(frame);
		setLocationRelativeTo(frame);
		setTitle(Constants.TITLE);

		setContentPane(getContent());
		setModal(true);
		setResizable(false);
		try {
			setIconImage(ImageIO.read(new File(Constants.ICON_PATH)));
		} catch (IOException e) {
			System.err.println("Icon not found!");
		}
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				Main.exit();
		    }
		});
		setSize(new Dimension(300, 200));
        setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (!visible) {
			((GameFrame) getParent()).dispose();
		}
	}

	private JPanel getContent() {
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(4, 2, 10, 10));
		content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		content.setOpaque(true);

		JLabel nick = new JLabel("P�ezd�vka");
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
		JButton button = new JButton("Ukon�it");
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
		JButton button = new JButton("Pokra�ovat");
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
			Info.error("P�ezd�vka nen� validn�. Zadejte p�ezd�vku o velikosti mezi 1 a 12 znaky.");
			return false;
		}
		// Validate serverIP
		final String SERVER_IP_INVALID = "IP serveru nen� validn�. Zadejte IP ve tvaru x.x.x.x kde x je ��slo mezi 0 a 255.";
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
		final String PORT_INVALID = "Port serveru nen� validn�. Zadejte port ve tvaru xxxx kde x je ��slo.";
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
