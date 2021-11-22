package game;

public class Apple {
	private Snake snake = new Snake();
	private int appleX; // Xpos for apple
	private int appleY; // Ypos for apple

	private final int RANDOMPOSITION = (int) Math.random() * 100;

	public void createApple() { // randomizes apple position
		int location = (int) (Math.random() * RANDOMPOSITION);

		appleX = (location * Board.getDotSize());
		location = (int) (Math.random() * RANDOMPOSITION);
		appleY = (location * Board.getDotSize());

		if (appleX == snake.getSnakeX(0) && appleY == snake.getSnakeY(0)) {
			createApple();
		}

	}

	public int getAppleX() {
		return appleX;
	}

	public int getAppleY() {
		return appleY;
	}
}
