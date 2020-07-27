package client.graphic;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Frame extends JFrame {

	public Frame() {
		LoginPanel p=new LoginPanel();
		this.add(p);
		initial();
	}
	private void initial() {
		this.setSize(new Dimension(700, 800));
		this.setCursor(HAND_CURSOR);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
