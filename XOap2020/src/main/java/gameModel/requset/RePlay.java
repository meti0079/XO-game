package gameModel.requset;

import java.io.IOException;
import java.net.DatagramPacket;

import com.google.gson.Gson;

import server.ClientHandler.Handler;
import server.Logic.Mapper;
import server.Logic.Game;
import server.Main.ServerMain;

public class RePlay {
	String name;
	int athtocken;

	public RePlay() {

	}
	public RePlay(String name, int athtocken) {
		this.name = name;
		this.athtocken = athtocken;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAthtocken() {
		return athtocken;
	}
	public void setAthtocken(int athtocken) {
		this.athtocken = athtocken;
	}
	public void accept(Mapper map, DatagramPacket packet, Gson gson) {
		try {
			if(Handler.waiting.contains(Handler.online.get(athtocken))) {
				System.out.println(name +" is in waiting");
			}else {		
				Handler.waiting.add(Handler.online.get(athtocken));
				if(Handler.waiting.size()>=2) {
					Game ga=new Game(Handler.waiting.get(0), Handler.waiting.get(1));
					Handler.waiting.get(0).getUser().setXO(1);
					Handler.waiting.get(1).getUser().setXO(0);
					String mes1="set user>"+gson.toJson(Handler.waiting.get(0).getUser());
					String mes2="set user>"+gson.toJson(Handler.waiting.get(1).getUser());					
					ServerMain.WriteMessage(mes1, Handler.waiting.get(0).getAddress());
					ServerMain.WriteMessage(mes2, Handler.waiting.get(1).getAddress());
					String mes="change panel>play>"+gson.toJson(ga.getBoard());
					ServerMain.WriteMessage(mes, Handler.waiting.get(0).getAddress());
					ServerMain.WriteMessage(mes, Handler.waiting.get(1).getAddress());
					Handler.waiting.remove(0);
					Handler.waiting.remove(0);
					Handler.games.add(ga);
				}
			}
		} catch (IOException e) {	e.printStackTrace();}
	}

}
