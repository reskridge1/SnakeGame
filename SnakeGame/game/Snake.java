package game;

public class Snake {
	//snake body part locations
	private final int[] x = new int[Board.getAllDots()];
	private final int[] y = new int[Board.getAllDots()];
	
	//Snake directions
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private int joints = 0; //number of dots on snake body
	
	public void setSnakeX(int idx) {
		x[0] = idx;
	}
	
	public void setSnakeY(int idx) {
		y[0] = idx;
	}
	
	public int getSnakeX(int idx) {
		return x[idx];
	}
	
	public int getSnakeY(int idx) {
		return y[idx];
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}
	
	public void setDown(boolean down) {
		this.down = down;
	}
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public void setRight(boolean right) {
		this.right = right;
	}
	
	
	public boolean isGoingLeft() {
		return left;
	}
	
	public boolean isGoingRight() {
		return right;
	}
	
	public boolean isGoingUp() {
		return up;
	}
	
	public boolean isGoingDown() {
		return down;
	}
	
	public void setNumJoints(int j) {
		joints = j;
	}
	
	public int getJoints() {
		return joints;
	}
	
	public void move() {
		for(int i = joints; i > 0; i--) {
			x[i] = x[i-1]; //all joints should move up one
			y[i] = y[i-1];
		}
		
		if(left) {
			x[0] -= Board.getDotSize();
		}
		if(right) {
			x[0] += Board.getDotSize();
		}
		if(up) {
			y[0] -= Board.getDotSize();
		}
		if(down) {
			y[0] -= Board.getDotSize();
		}
		
		//dot size is the size of the joints
	}
}
