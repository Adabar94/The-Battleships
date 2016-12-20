package gui;

import java.awt.Dimension;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Frame extends JFrame {

	public Frame(String name, JPanel contentPane, int w, int h, WindowListener listener, JMenuBar menu) {
		super(name);
		setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(w, h));
		addWindowListener(listener);
		setJMenuBar(menu);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public Frame(String name, JPanel contentPane, int w, int h, WindowListener listener) {
		super(name);
		setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(w, h));
		addWindowListener(listener);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public Frame(String name, JPanel contentPane, int w, int h) {
		super(name);
		setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(w, h));
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public Frame(String name, int w, int h) {
		this(name, new Panel(), w, h);
	}

	public void closeFrame() {
		dispose();
	}
}
