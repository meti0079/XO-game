package server.ClientHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import gameModel.requset.ReLogin;
import gameModel.requset.RePlay;
import gameModel.requset.ReSingup;
import gameModel.requset.ReSummon;
import server.Logic.Mapper;
import server.Logic.Game;

public class Handler {
	public static ArrayList<Client> clients= new ArrayList<Client>();
	public static HashMap<Integer, Client> online=new HashMap<>();
	public static ArrayList<Client> waiting= new ArrayList<Client>();
	public static ArrayList<Game> games=new  ArrayList<>();
	private Gson gson;
	private Mapper map;
	public Handler() {
		gson= new GsonBuilder().setLenient().create();
		map=new Mapper();
	}
	public void recivePacket(DatagramPacket packet) {
		ByteArrayInputStream arrayInputStream=new ByteArrayInputStream(packet.getData());
		Scanner scanner = new Scanner(arrayInputStream);
		String message=scanner.nextLine();
		whatWant(message, packet);
	}
	public void whatWant(String message, DatagramPacket packet) {
		String what="";
		for(int i=0 ;i<message.length();i++) {
			if(message.charAt(i)=='>') {
				what=message.substring(0, i);
				message=message.substring(i+1,message.length());
				break;
			}
		}
		switch (what) {
		case "login":
			login(message, packet);
			break;	
		case "singup":
			singup(message , packet);
			break;
		case "exit":
			exit(message);
			break;
		case "play":
			play(message, packet);
			break;
		case "summon":
			summon(message);
			break;
		default:
			break;
		}
	}

	private void summon(String x) {
		StringReader reader= new StringReader(x);
		ReSummon re=gson.fromJson(new JsonReader(reader), ReSummon.class);
		for(int i=0 ;i<games.size();i++) {
			if(games.get(i).getPlayer1().getUser().getAthToken()==re.getAthtoken() || games.get(i).getPlayer2().getUser().getAthToken()==re.getAthtoken()) {
				re.accept(map,games.get(i),gson);				
			}
		}
	}

	private void play(String x, DatagramPacket packet) {
		for(int i=0;i<x.length();i++)
			if(x.charAt(i)=='}') {
				x=x.substring(0,i+1);
			}
		RePlay re=gson.fromJson(x, RePlay.class);
		re.accept(map, packet, gson);
	}
	private void exit(String x) {
		for(int i=0;i<x.length();i++)
			if(x.charAt(i)=='}') {
				x=x.substring(0,i);
			}
		for (int i=0;i< clients.size();i++) {
			if(x.equals(clients.get(i).getUser().getAthToken()+"")) {
				Handler.online.remove(clients.get(i).getUser().getAthToken());
				clients.get(i).getUser().setAthToken(0);
				clients.get(i).getUser().setOnline(false);
				clients.get(i).getUser().setXO(-2);
				try {
					map.makeProfile(clients.get(i).getUser());
				} catch (IOException e) {}
				if(waiting.contains(clients.get(i)))
					waiting.remove(clients.get(i));
				clients.remove(i);
			}
		}
	}
	private void singup(String message, DatagramPacket packet) {
		for(int i=0;i<message.length();i++)
			if(message.charAt(i)=='}') {
				message=message.substring(0,i+1);
			}
		ReSingup re=gson.fromJson(message, ReSingup.class);
		for (Client client : clients) {
			if(re.getName().equals(client.getUser().getName())) {
				return;
			}
		}
		re.accept(map,packet,gson);	
	}
	private void login(String message, DatagramPacket packet) {
		for(int i=0;i<message.length();i++)
			if(message.charAt(i)=='}') {
				message=message.substring(0,i+1);
			}
		ReLogin re=gson.fromJson(message, ReLogin.class);
		for (Client client : clients) {
			if(re.getName().equals(client.getUser().getName())) {
				return;
			}
		}
		re.accept(map,packet,gson);
	}
}