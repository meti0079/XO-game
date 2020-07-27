package gameModel;

import java.util.ArrayList;

public class TV {
	private ArrayList<Board> TV;
	public TV() {
		TV=new ArrayList<>();
	}
	public void setPic(Board x) {
		TV.add(x);
	}
	public ArrayList<Board> getTV() {
		return TV;
	}

	public void setTV(ArrayList<Board> tV) {
		TV = tV;
	}

}
