package gui.menu;

import java.awt.event.*;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MenuItem extends JMenuItem {
	public MenuItem(String name, ActionListener a){
		this.setText(name);
		this.addActionListener(a);
	}
}
