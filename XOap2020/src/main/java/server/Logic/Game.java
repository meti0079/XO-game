package server.Logic;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.plaf.SliderUI;

import com.google.gson.Gson;

import gameModel.Board;
import gameModel.Place;
import gameModel.TV;
import gameModel.User;
import server.ClientHandler.Client;
import server.ClientHandler.Handler;
import server.Main.ServerMain;

public class Game {
	private Client player1;
	private Client player2;
	private Board playBoard;
	private TV tv;
	public Game(Client player1, Client player2) {
		System.out.println("game new");
		this.player1 = player1;
		this.player2 = player2;
		playBoard=new Board();
		tv=new TV();
	}
	public void summon(Place x, Gson gson) {
		try {
			if(playBoard.getBox(x.getX(), x.getY()).getXO()==2) {
				if(playBoard.getTurn()==x.getXO()) {
					playBoard.getBox(x.getX(), x.getY()).setXO(x.getXO());
					playBoard.setTurn(changTurn(x.getXO()));
					String message="summon>"+gson.toJson(playBoard);;
					ServerMain.WriteMessage(message, player1.getAddress());
					ServerMain.WriteMessage(message, player2.getAddress());
					tv.getTV().add(playBoard.Clone());
					player1.getUser().setLastmatch(tv);
					player2.getUser().setLastmatch(tv);
				}
				chekFinish(gson);
			}
		} catch (IOException e) {e.printStackTrace();}
	}
	public int changTurn(int x) {
		if(x==1)
			return 0;
		return 1;
	}
	private void chekFinish(Gson gson) {
		int x=playBoard.check();
		if(x==0) {
			matchFinished(0, gson);
		}else if(x==1) {
			matchFinished(1, gson);			
		}	
	}
	private void matchFinished(int x, Gson gson) {
		synchronized (playBoard) {
			try {
				String message="";
				if(player1.getUser().getXO()==x) {
					message="finish>"+player1.getUser().getName();
					player1.getUser().setWon(player1.getUser().getWon()+1);
					player2.getUser().setLose(player2.getUser().getLose()+1);
				}else {
					message="finish>"+player2.getUser().getName();
					player2.getUser().setWon(player2.getUser().getWon()+1);
					player1.getUser().setLose(player1.getUser().getLose()+1);
				}
				Mapper map =new Mapper();
				map.makeProfile(player1.getUser());
				map.makeProfile(player2.getUser());
				ServerMain.WriteMessage(message, player1.getAddress());
				ServerMain.WriteMessage(message, player2.getAddress());
				String mes11="set user>"+gson.toJson(player1.getUser());
				String mes12="set user>"+gson.toJson(player2.getUser());
				ArrayList<User> a=new ArrayList<>();
				for (Client cli : Handler.clients) {
					a.add(cli.getUser());
				}
				playBoard.wait(1000);
				String mes2="set users>"+gson.toJson(map.sort(a));
				String mes3="change panel>menu";
				ServerMain.WriteMessage(mes11, player1.getAddress());
				ServerMain.WriteMessage(mes12, player2.getAddress());
				ServerMain.WriteMessage(mes2, player1.getAddress());
				ServerMain.WriteMessage(mes2, player2.getAddress());
				ServerMain.WriteMessage(mes3, player1.getAddress());
				ServerMain.WriteMessage(mes3, player2.getAddress());
				Handler.games.remove(this);	
			} catch (Exception e) {e.printStackTrace();	}
		}
	}
	public Client getPlayer1() {
		return player1;
	}
	public void setPlayer1(Client player1) {
		this.player1 = player1;
	}
	public Client getPlayer2() {
		return player2;
	}
	public void setPlayer2(Client player2) {
		this.player2 = player2;
	}
	public Board getBoard() {
		return playBoard;
	}
	public void setBoard(Board board) {
		this.playBoard = board;
	}

}
