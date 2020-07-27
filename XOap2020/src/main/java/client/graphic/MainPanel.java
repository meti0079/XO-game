package client.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JPanel;
import client.Main.Client;
import client.Main.MessageHandler;
import gameModel.Board;
import gameModel.Place;
import gameModel.requset.ReSummon;

public class MainPanel extends JPanel {
	boolean isfinish=false;
	String name;
	public MainPanel() {
		initial();
	}
	private void initial() {
		this.setLayout(new GridLayout(8,7));
		this.setPreferredSize(new Dimension(700,700));
	}
	public void finish(String name) {
		JButton finishButton= new JButton("winer \n"+name);
		finishButton.setBackground(Color.GREEN);
		add(finishButton);
	}

	public  void updte(Board board, int XO, Boolean flag) {
		removeAll();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				final int xx=i;
				final int yy=j;
				JButton button =new JButton();
				button.setFont(new Font("Tahoma", Font.BOLD, 35));
				if(board.getBox(i, j).getXO()==2) {	
					button.setText("");
				}else {
					button.setText(setXOO(board.getBox(i, j).getXO()));
					if(board.getBox(i, j).getXO()==1) {	
						button.setBackground(Color.red);
						button.setEnabled(false);
					}else {
						button.setBackground(Color.blue);
						button.setEnabled(false);						
					}	
				}
				button.addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
						Place ne=new Place(xx, yy, XO);
						String mess="summon>"+MessageHandler.getInsisit().getGson().toJson(new ReSummon(ne,MessageHandler.getInsisit().getUser().getAthToken()));
						try {
							Client.WriteMessage(mess);
						} catch (IOException e1) {}
					}
				});
				if(!flag)
					button.setEnabled(false);
				add(button);
			}
		}
		JButton turn=new JButton("TURN :"+setXOO(board.getTurn()));
		turn.setFont(new Font("Tahoma", Font.BOLD, 15));
		turn.setEnabled(false);
		add(turn);	
		JButton  x=new JButton( "you are : "+setXOO(XO));
		x.setFont(new Font("Tahoma", Font.BOLD, 10));
		x.setEnabled(false);
		add(x);
		JButton exit=new JButton("exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mess="exit>"+MessageHandler.getInsisit().getUser().getAthToken()+"}";
				try {
					Client.WriteMessage(mess);
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}
		});
		add(exit);
		repaint();
		revalidate();
	}
	private String setXOO(int x) {
		if(x==1)
			return "X";
		if(x==0)
			return "O";
		return "";
	}
	
}