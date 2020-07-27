package server.Main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

import server.config.ConfigReader;
public class ServerMain {
	public static int serverPort=8000;
	public static int maxLenght=1000;
	public static DatagramSocket datagramSocket;

	public static DatagramPacket readPacket() throws IOException {
		DatagramPacket datagramPacket = new DatagramPacket(new byte[maxLenght], maxLenght);
		datagramSocket.receive(datagramPacket);
		return datagramPacket;
	}
	public static void WriteMessage(String message, SocketAddress address) throws IOException {
		byte[] data=message.getBytes();
		DatagramPacket datagramPacket= new DatagramPacket(data, data.length, address );
		datagramSocket.send(datagramPacket);	
	}
	public static void main(String[] args) throws Exception {
		serverPort=ConfigReader.getinsisit().getServer();
		datagramSocket= new DatagramSocket(serverPort);
		System.out.println("server run on port : "+serverPort);
		Reciver re= new Reciver();
		re.start();
	}
}
