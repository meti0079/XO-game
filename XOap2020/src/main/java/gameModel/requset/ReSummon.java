package gameModel.requset;

import com.google.gson.Gson;

import gameModel.Place;
import server.Logic.Mapper;
import server.Logic.Game;

public class ReSummon {
	Place x;
	int athtoken;

	public Place getX() {
		return x;
	}
	public void setX(Place x) {
		this.x = x;
	}
	public int getAthtoken() {
		return athtoken;
	}
	public void setAthtoken(int athtoken) {
		this.athtoken = athtoken;
	}
	public ReSummon(Place x, int i) {
		this.x = x;
		this.athtoken=i;
	}
	public ReSummon() {
	}
	public void accept(Mapper map, Game game,Gson gson) {
		game.summon(x, gson);
	}

}
