package server.ClientHandler;

import java.net.InetAddress;
import java.net.SocketAddress;

import gameModel.User;

public class Client {
	private int potr;
	private SocketAddress address;
	private User user;

	public Client(int potr, SocketAddress address, User user) {
		this.potr = potr;
		this.address = address;
		this.user = user;
	}
	public int getPotr() {
		return potr;
	}
	public void setPotr(int potr) {
		this.potr = potr;
	}
	public SocketAddress getAddress() {
		return address;
	}
	public void setAddress(SocketAddress address) {
		this.address = address;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
