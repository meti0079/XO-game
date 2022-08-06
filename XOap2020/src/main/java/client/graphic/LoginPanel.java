package client.graphic;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

import client.Main.Client;
import client.Main.MessageHandler;
import gameModel.requset.ReLogin;
import gameModel.requset.ReSingup;
public class LoginPanel extends JPanel{
	JTextField name;
	JPasswordField password;
	JButton login;
	JTextField newname;
	JPasswordField newpassword;
	JButton singup;
	JButton digitalWatch;
	public LoginPanel() {
		initial();
		makeNameField();
		makePassword();
		buttons();
	}
	private void initial() {
		setSize(700, 700);
		setLayout(null);
		digitalWatch=new DigitalWatch().b;
		digitalWatch.setBounds(0,0,100,30);
	}
	private void makeNameField() {
		name=new JTextField(15);
		name.setBounds(300, 100, 150, 40);
		add(name);
		newname=new JTextField(15);
		newname.setBounds(300, 500, 150, 40);
		add(newname);
		add(digitalWatch);
	}

	private void makePassword() {
		password=new JPasswordField();
		password.setBounds(300, 150, 150, 40);
		add(password);
		newpassword=new JPasswordField();
		newpassword.setBounds(300, 550, 150, 40);
		add(newpassword);
		JCheckBox checkBox=new JCheckBox();
		checkBox.setBounds(300,600,20,20);
		add(checkBox);
		checkBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBox.isSelected()){
					newpassword.setEchoChar((char)0);
				}else{
					newpassword.setEchoChar('*');

				}

			}
		});
	}

	private void buttons() {
		login=new JButton("LOGIN");
		login.setBounds(550,200,100,70);
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mess="login>"+MessageHandler.getInsisit().getGson().toJson(new ReLogin(name.getText(), password.getText()), ReLogin.class);
				try {
					Client.WriteMessage(mess);
				} catch (IOException e1) {e1.printStackTrace();	}
			}				
		});
		add(login);
		singup=new JButton("SING UP");
		singup.setBounds(550,600,100,70);
		singup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String mess="singup>"+MessageHandler.getInsisit().getGson().toJson(new ReSingup(newname.getText(), newpassword.getText()), ReSingup.class);
				try {
					Client.WriteMessage(mess);
				} catch (IOException e1) {	e1.printStackTrace();	}
			}
		});
		add(singup);
	}
	@Override
	protected void paintComponent(Graphics g) {
		drawLine(g);
		writeField(g);
	}
	private void drawLine(Graphics g) {
		g.drawRoundRect(10, 10, 680, 750, 10, 10);
		g.drawLine(10, 400, 690, 400);
	}
	private void writeField(Graphics g) {
		g.drawString("LOGIN", 25, 25);
		g.drawString("SING UP", 20, 420);
		g.drawString("NAME :  ", 200, 120);
		g.drawString("NAME :  ", 200, 520);
		g.drawString("PASSWORD :  ", 200, 170);
		g.drawString("PASSWORD :  ", 200, 570);
	}

}
