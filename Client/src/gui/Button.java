package gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import actions.Events;

@SuppressWarnings("serial")
public class Button extends JButton {
	public Button(String name, ActionListener a, Object[] o, int argc){
		super(name);
		addActionListener(a);
		for(int i = 0; i < argc; i++){
			Events.object[i] = o[i];
		}
	}
	
	public Button(String name, ActionListener a){
		super(name);
		addActionListener(a);
	}
}
