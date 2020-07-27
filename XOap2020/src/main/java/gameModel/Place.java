package gameModel;

public class Place {
		private int XO;
		private int x; 
		private int y;

		public Place(int x, int y, int xo) { 
			this.XO = xo;
			this.x = x;
			this.y = y;
		} 
		public int getXO() { 
			return this.XO;
		} 
		public void setXO(int xo) { 
			this.XO = xo; 
		}
		public int getX() { 
			return this.x; 
		} 
		public void setX(int x) { 
			this.x = x; 
		} 
		public int getY() { 
			return this.y; 
		} 
		public void setY(int y) { 
			this.y = y; 
		} 	
	}