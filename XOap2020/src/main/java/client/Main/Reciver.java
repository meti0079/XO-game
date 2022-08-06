package client.Main;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class Reciver extends Thread{
	DatagramSocket data;
	public Reciver(DatagramSocket daa) {
		data=daa;
	}
	@Override
	public void run() {

		while (true) {
			try {
				DatagramPacket packet= Client.readPacket(data);
				ByteArrayInputStream arrayInputStream=new ByteArrayInputStream(packet.getData());
				Scanner scan = new Scanner(arrayInputStream);
				String message=scan.nextLine();
				scan.close();
				MessageHandler.getInsisit().recivemessage(message);
			} catch (IOException e) {	e.printStackTrace();	}
		}
	}

}
