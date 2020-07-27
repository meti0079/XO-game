package gameModel.requset;

import java.io.IOException;
import java.net.DatagramPacket;
import java.security.SecureRandom;
import java.util.ArrayList;

import com.google.gson.Gson;

import gameModel.User;
import server.ClientHandler.Client;
import server.ClientHandler.Handler;
import server.Logic.Mapper;
import server.Main.ServerMain;

public class ReSingup {

	private String name;
	private String pass;

	public ReSingup() {
	}
	public ReSingup(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	public void accept(Mapper map, DatagramPacket packet, Gson gson) {
		try {
			if(!map.checkValid(name)) {
				map.writeName(name, pass);
				User x= new User();
				x.setName(name);
				x.setPass(pass);
				x.setAthToken(new SecureRandom().nextInt());
				x.setLose(0);
				x.setWon(0);
				x.setOnline(true);
				map.makeProfile(x);
				Client cl= new Client(packet.getPort(), packet.getSocketAddress(), x);
				Handler.clients.add(cl);
				Handler.online.put(x.getAthToken(), cl);
				String mes1="set user>"+gson.toJson(x);
				ArrayList<User> a=new ArrayList<>();
				for (Client cli : Handler.clients) {
					a.add(cli.getUser());
				}
				String mes2="set users>"+gson.toJson(map.sort(a));
				String mes3="change panel>menu";
				ServerMain.WriteMessage(mes1, packet.getSocketAddress());
				ServerMain.WriteMessage(mes2, packet.getSocketAddress());
				ServerMain.WriteMessage(mes3, packet.getSocketAddress());
				map.makeProfile(x);
			}else {
				ServerMain.WriteMessage("try>", packet.getSocketAddress());
				System.out.println("ignor");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

}
