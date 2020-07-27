package client.graphic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.google.gson.Gson;

import client.Main.Client;
import client.Main.MessageHandler;
import client.Main.TVShow;
import gameModel.User;
import gameModel.requset.RePlay;

public class MenuPanel extends JPanel{
	private	JButton play;
	private	JButton exit;
	private	JButton video;
	private Frame frame;
	private User user;
	private ArrayList<User> allUser;
	public MenuPanel(Frame frame) {
		this.frame=frame;
		initial();
		initialButton();
	}	
	private void initial() {
		this.setSize(new  Dimension(700,700));
		this.setLayout(null);
	}
	private void initialButton() {
		play=new JButton("PLAY");
		exit=new JButton("EXIT");
		play.setBounds(50, 600, 100, 50);
		video=new JButton("VIDEO");
		video.setBounds(200, 600, 100, 50);
		exit.setBounds(50, 650, 100, 50);
		add(exit);
		add(play);
		add(video);
		video.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TVShow x= new TVShow(user.getLastmatch(), frame, user.getXO(),MenuPanel.this);
				x.start();
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String mess="exit>"+MessageHandler.getInsisit().getUser().getAthToken()+"}";
				try {
					Client.WriteMessage(mess);
					System.exit(0);
				} catch (IOException e1) {e1.printStackTrace();	}
			}
		});	
		
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String mess="play>"+new Gson().toJson(new RePlay(user.getName(),user.getAthToken()));
				try {
					Client.WriteMessage(mess);
				} catch (IOException e1) {e1.printStackTrace();	}
			}
		});
	}

	public void initialTable(Graphics g , ArrayList<User > users) {
		g.drawRect(10, 10, 600, users.size()*20);
		for (int i = 0; i < users.size(); i++) {
			if(i>15)
				break;
			g.drawLine(10, 10+20*i, 610,10+ 20*i);
			writeUserTable(users.get(i), g, 25+20*i);
			for (int l = 0; l < 6; l++) {
				g.drawLine(10+l*100, 10,10+ l*100, 10+users.size()*20);
			}
		}
	}

	public void writeUser( Graphics g, User user) {
		g.drawString("name : "+user.getName(), 50, 400);
		g.drawString("win : "+user.getWon(), 50, 430);
		g.drawString("lose : "+user.getLose(), 50, 460);
		g.drawString("score : "+user.getScore(), 50, 490);
		}
	private void writeUserTable(User user, Graphics g, int y) {
		g.drawString("name : "+user.getName(), 13, y);
		g.drawString("win : "+user.getWon(), 113, y);
		g.drawString("lose : "+user.getLose(), 213, y);
		g.drawString("score : "+user.getScore(), 313, y);
		g.drawString("state : "+user.isOnline(), 413, y);
	}
	@Override
	protected void paintComponent(Graphics g) {
		initialTable(g, allUser);
		writeUser(g, user);
	}
	public void update(User user, ArrayList<User> allUser) {
		repaint();
		revalidate();
	}	
	public void setUser(User user) {
		this.user = user;
	}
	public void setAllUser(ArrayList<User> allUser) {
		this.allUser = allUser;
	}
}