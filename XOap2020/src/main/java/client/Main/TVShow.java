package client.Main;

import com.google.gson.Gson;

import client.graphic.Frame;
import client.graphic.MainPanel;
import client.graphic.MenuPanel;
import gameModel.TV;

public class TVShow extends Thread{

	private TV tv;
	private Frame farme;
	private int XO;
	private MenuPanel menu;
	public TVShow(TV tv, Frame fram , int s, MenuPanel men) {
		this.tv=tv;
		this.menu=men;
		this.farme=fram;
		this.XO=s;
	}
	@Override
	public void run() {
		MainPanel p=new MainPanel();	
		farme.setContentPane(p);
		p.setVisible(true);
		for(int i=0;i<tv.getTV().size();i++) {
			p.updte(tv.getTV().get(i), XO, false);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {	e.printStackTrace();}	
		}
		farme.setContentPane(menu);
	}


}
