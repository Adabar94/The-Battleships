package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	public JPanel contentPane;
	
	public Frame(String name, JPanel contentPane, int w, int h){
		super(name);		
		
		this.contentPane = contentPane;
		setContentPane(contentPane);
		
		contentPane.setPreferredSize(new Dimension(w,h));
	}
	
	public Frame(String name, int w, int h){
		this(name, new Panel(), w, h);
	}

	public void finish(){
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void closeFrame(){
		dispose();
	}
}
