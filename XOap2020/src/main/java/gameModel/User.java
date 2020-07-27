package gameModel;

public class User {
	private String name;
	private String pass;
	private int won;
	private int lose;
	private int XO;
	private int AthToken;
	private boolean online;
	private TV lastmatch;
	public User() {
	}
	public TV getLastmatch() {
		return lastmatch;
	}
	public void setLastmatch(TV lastmatch) {
		this.lastmatch = lastmatch;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public int getXO() {
		return XO;
	}
	public void setXO(int xO) {
		XO = xO;
	}
	public int getAthToken() {
		return AthToken;
	}
	public void setAthToken(int athToken) {
		AthToken = athToken;
	}
	public int getScore() {
		return won-lose;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getWon() {
		return won;
	}
	public void setWon(int won) {
		this.won = won;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
}