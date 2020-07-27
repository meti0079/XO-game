package gameModel;

public class Board {
	Place [][] board=new Place[7][7];
	int turn;
	public Board() {
		initial();
		turn=1;
	}
	private void initial() {	
		for (int i = 0; i <7 ; i++) {
			for (int j = 0; j < 7; j++) {
				board[i][j]=new Place(i, j, 2);
			}	
		}
	}
	public Place getBox(int x, int y) {
		return board[x][y]; 
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public Place[][] getBoard() {
		return board;
	}
	public void setBoard(Place[][] board) {
		this.board = board;
	}

	public int check() {
		int sum=1;
		for (int i = 0; i < 7; i++) {
			sum=1;
			for (int j = 0; j <6 ; j++) {
				if(getBox(i, j).getXO()==getBox(i, j+1).getXO() && getBox(i, j).getXO()!=2) {
					sum++;
				}else {
					sum=1;
				}
				if(sum==4) {
					return getWiner(this.getBox(i, j).getXO());
				}
			}
		}
		for (int i = 0; i < 7; i++) {
			sum=1;
			for (int j = 0; j <6 ; j++) {
				if(this.getBox(j+1, i).getXO()==this.getBox(j, i).getXO()&& getBox(j, i).getXO()!=2) {
					sum++;
				}else {
					sum=1;
				}
				if(sum==4) {
					return getWiner(this.getBox(j, i).getXO());
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			int y=0;
			int x=i;
			sum=1;
			for (int j = 0; j < 6; j++) {
				if(x+1<7 &&y+1<7) {
					if(this.getBox(x,y).getXO()==this.getBox(x+1,y+1 ).getXO()&& getBox(x, y).getXO()!=2) {
						sum++;
					}else {
						sum=1;
					}
					if(sum==4) {
						return getWiner(this.getBox(x, y).getXO());
					}
					x++;y++;	
				}else
					break;
			}	
		}
		for (int i = 1; i < 4; i++) {
			int y=i;
			int x=0;
			sum=1;
			for (int j = 0; j < 6; j++) {
				if(x+1<7 &&y+1<7) {
					if(this.getBox(x,y).getXO()==this.getBox(x+1,y+1 ).getXO()&& getBox(x, y).getXO()!=2) {
						sum++;
					}else {
						sum=1;
					}
					if(sum==4) {
						return getWiner(this.getBox(x, y).getXO());
					}
					x++;y++;	
				}else
					break;
			}	
		}
		for (int i = 6; i > 2; i--) {
			int y=0;
			int x=i;
			sum=1;
			for (int j = 0; j < 6; j++) {
				if(x-1>=0 &&y+1<7) {
					if(this.getBox(x,y).getXO()==this.getBox(x-1,y+1 ).getXO()&& getBox(x, y).getXO()!=2) {
						sum++;
					}else {
						sum=1;
					}
					if(sum==4) {
						return getWiner(this.getBox(x, y).getXO());
					}
					x--;y++;	
				}else
					break;
			}	
		}
		for (int i = 1; i < 4; i++) {
			int y=i;
			int x=6;
			sum=1;
			for (int j = 0; j < 6; j++) {
				if(x-1>=0 &&y+1<7) {
					if(this.getBox(x,y).getXO()==this.getBox(x-1,y+1).getXO()&& getBox(x,y).getXO()!=2) {
						sum++;
					}else {
						sum=1;
					}
					if(sum==4) {
						return getWiner(this.getBox(x, y).getXO());
					}
					x--;y++;	
				}else
					break;
			}	
		}	
		return 2;
	}

	public int getWiner(int i) {
		if(i==1)
			return 1;
		else
			return 0;
	}
	public Board Clone() {
		Board x= new Board();
		x.setTurn(turn);
		for (int i = 0; i <7 ; i++) {
			for (int j = 0; j < 7; j++) {
				x.getBox(i, j).setXO(this.getBox(i, j).getXO());
			}	
		}
		return x;	
	}
}
