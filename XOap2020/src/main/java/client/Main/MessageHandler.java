package client.Main;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.common.reflect.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import client.graphic.Frame;
import client.graphic.MainPanel;
import client.graphic.MenuPanel;
import gameModel.Board;
import gameModel.User;

public class MessageHandler {
	private Frame frame;
	private MenuPanel menuPanel;
	private MainPanel mainPanel;
	private String state;
	private Gson gson;
	private User user;
	private ArrayList<User> allUser;
	public static MessageHandler handler;
	private MessageHandler() {
		frame= new Frame();
		menuPanel= new MenuPanel(frame);
		mainPanel = new MainPanel();
		state="login";
		gson=new GsonBuilder().setLenient().create();
	}
	public static MessageHandler getInsisit() {
		if(handler==null)
			handler= new MessageHandler();
		return handler;
	}
	public void recivemessage(String x) throws IOException {
		String what="";
		for(int i=0 ;i<x.length();i++) {
			if(x.charAt(i)=='>') {
				what=x.substring(0, i);
				x=x.substring(i+1,x.length());
				break;
			}
		}
		switch (what) {
		case "change panel":
			changPanel(x);
			break;
		case "set user":
			setUser(x);
			break;
		case "set users":
			setUsers(x);
			break;
		case "summon":
			summon(x);
			break;
		case "finish":
			Finish(x);
			break;
		default:
			System.out.println("not valid request  : "+x);
			break;
		}
	}
	private void setUsers(String x) {
		Type listOfMyClassObject = new TypeToken<ArrayList<User>>() {}.getType();
		StringReader reader=new StringReader(x);
		allUser = gson.fromJson(new JsonReader(reader), listOfMyClassObject);
	}

	private void Finish(String x) {
		mainPanel.finish(x);
	}
	private void summon(String x) {
		StringReader R=new StringReader(x);
		Board b= gson.fromJson(new JsonReader(R), Board.class);
		mainPanel.updte(b,user.getXO(), true);
	}
	public void setUser(String x) {
		StringReader stringReader=new StringReader(x+" ");
		user=gson.fromJson(new JsonReader(stringReader), User.class);
	}
	private void changPanel(String x) {
		if(x.contains("menu")) {
			menuPanel.setUser(user);
			menuPanel.setAllUser(allUser);
			frame.setContentPane(menuPanel);
			menuPanel.setVisible(true);
			state="menu";
			menuPanel.update(user, allUser);	
		}else if(x.contains("play")) {
			for(int i=0 ;i<x.length();i++) {
				if(x.charAt(i)=='>') {
					x=x.substring(i+1,x.length());
					break;
				}
			}
			StringReader R=new StringReader(x);
			Board board=gson.fromJson(new JsonReader(R), Board.class);
			frame.setContentPane(mainPanel);
			mainPanel.setVisible(true);
			mainPanel.updte(board, user.getXO(), true);
			state="play";
		}
	}

	public User getUser() {
		return user;
	}
	public Gson getGson() {
		return gson;
	}

}
