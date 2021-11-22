package game;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {
	private final static int BOARDWIDTH = 500;
	private final static int BOARDHEIGHT = 500;

	private final static int PIXELSIZE = 25; // pixel size for apple and snake joints

	private final static int TOTALPIXELS = (BOARDWIDTH * BOARDHEIGHT) / ((int) Math.pow(PIXELSIZE, 2));

	private boolean isPlaying = true;
	private Timer timer;

	private static int speed = 45;

	private Snake snake = new Snake();
	private Apple apple = new Apple();

	public Board() {
		this.addKeyListener(new Keys());
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));
		initGame();
	}

	protected void paintComponent(Graphics g) {
		super.paint(g);
		draw(g);
	}

	private void draw(Graphics g) {
		if (isPlaying == true) {
			g.setColor(Color.GREEN);
			g.fillRect(apple.getAppleX(), apple.getAppleY(), PIXELSIZE, PIXELSIZE);

			for (int i = 0; i < snake.getJoints(); i++) {
				if (i == 0) {
					g.setColor(Color.RED);
					g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i), PIXELSIZE, PIXELSIZE);
				} else {
					g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i), PIXELSIZE, PIXELSIZE);
				}
			}
			Toolkit.getDefaultToolkit().sync();
		} else {
			endGame(g);
		}
	}

	private void initGame() {
		snake.setNumJoints(3); // set the initial snake size
		for (int i = 0; i < snake.getJoints(); i++) {
			snake.setSnakeX(BOARDWIDTH / 2);
			snake.setSnakeY(BOARDHEIGHT / 2);
		}
		snake.setRight(true); // start off going right

		apple.createApple();

		timer = new Timer(speed, this);
		timer.start();

	}

	private void eatFood() {
		if ((proximity(snake.getSnakeX(0), apple.getAppleX(), 20))
				&& (proximity(snake.getSnakeY(0), apple.getAppleY(), 20))) {

			System.out.println("intersection");
			// Add a 'joint' to our snake
			snake.setNumJoints(snake.getJoints() + 1);
			// Create new food
			apple.createApple();
		}
	}

	private void collision() {
		for (int i = snake.getJoints(); i > 0; i--) {
			if (i > 5 && (snake.getSnakeX(0) == snake.getSnakeX(i) && (snake.getSnakeY(0) == snake.getSnakeY(i))))
				isPlaying = false; // ends the game
		}

		if (snake.getSnakeX(0) >= BOARDHEIGHT) {
			isPlaying = false;
		}
		if (snake.getSnakeY(0) < 0) {
			isPlaying = false;
		}
		if (snake.getSnakeX(0) >= BOARDWIDTH) {
			isPlaying = false;
		}
		if (snake.getSnakeX(0) < 0) {
			isPlaying = false;
		}

		if (!isPlaying)
			timer.stop();

	}

	private void endGame(Graphics g) {
		String gameOver = "Game Over";

		Font font = new Font("Times New Roman", Font.BOLD, 12);
		FontMetrics metrics = getFontMetrics(font);

		g.setColor(Color.RED);
		g.drawString(gameOver, (BOARDWIDTH - metrics.stringWidth(gameOver)) / 2, (BOARDHEIGHT / 2));
	}

	public void actionPerformed(ActionEvent e) {
		if (isPlaying == true) {
			eatFood();
			collision();
			snake.move();
		}

		repaint();
	}

	private class Keys extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT && !snake.isGoingRight()) {
				snake.setLeft(true);
				snake.setUp(false);
				snake.setDown(false);
			}
			if (key == KeyEvent.VK_RIGHT && !snake.isGoingLeft()) {
				snake.setRight(true);
				snake.setUp(false);
				snake.setDown(false);
			}
			if (key == KeyEvent.VK_DOWN && !snake.isGoingUp()) {
				snake.setDown(true);
				snake.setRight(false);
				snake.setLeft(false);
			}
			if (key == KeyEvent.VK_ENTER && isPlaying == false) {
				isPlaying = true;
				snake.setDown(false);
				snake.setUp(false);
				snake.setLeft(false);
				snake.setRight(false);

				initGame();
			}

		}
	}

	private boolean proximity(int i, int j, int closeness) {
		return Math.abs((long) i - j) <= closeness;
	}

	public static int getAllDots() {
		return TOTALPIXELS;
	}

	public static int getDotSize() {
		return PIXELSIZE;
	}

}
