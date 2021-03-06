package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
	// private data fields
	private static final int SCREEN_WIDTH = 650;
	private static final int SCREEN_HEIGHT = 600;
	private static final int UNIT_SIZE = 24; // object size for screen
	private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE; // how many units screen can hold
	private static final int DELAY = 75; // timer delay
	private final int x[] = new int[GAME_UNITS]; // holds all snake x-coords
	private final int y[] = new int[GAME_UNITS]; // holds all snake y-coords
	private int bodyParts = 3;
	private int applesEaten;
	private int appleX;
	private int appleY;
	private char direction = 'R'; // R (right {*default*}), L (left), U (up), D (down)
	private boolean running = false;
	Timer timer;
	Random random;

	/**
	 * constructor - sets our screen components and starts game
	 */
	public GamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); // sets the preferred dimension for screen
		this.setBackground(Color.BLACK); // sets the background to black
		this.setFocusable(true); // sets to focusable
		this.addKeyListener(new keyAdapter()); // adds event to listen to key presses
		startGame(); // starts the game
	}

	/**
	 * starts our game, creates new apple, and sets/starts the timer with a delay
	 */
	public void startGame() {
		Apple(); // creates a new apple on the screen
		running = true;
		timer = new Timer(DELAY, this); // creates a new timer with a delay
		timer.start(); // starts the timer
	}

	/**
	 * will place apple evenly in a random x-y coord
	 */
	public void Apple() {
		appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE; // set apple to some random x-coord in
																				// range of screen width/unit size
		appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE; // set apple to some random y-coord in
																				// range of screen width/unit size
	}

	/**
	 * calls our draw method and paints components to the screen
	 * 
	 * @param g
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // calls parent class's paintComponent method
		draw(g);
	}

	/**
	 * draws our visual components
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		if (running) { // only draw components if game is running
			for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) { // draws lines on the screen to look like a grid
				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT); // draw lines across x-axis
				g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE); // draw lines across y-axis
			}
			g.setColor(Color.RED);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); // randomly creates a red apple of UNIT_SIZE x UNIT_SIZE
																// in one of the grid spaces

			for (int i = 0; i < bodyParts; i++) { // draws snake body parts
				if (i == 0) { // head of snake
					g.setColor(Color.GREEN);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); // UNIT_SIZE x UNIT_SIZE green snake at x-y
				} 
				else { // snake body
					g.setColor(new Color(45, 180, 0)); // new color different shade of green
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			// draws the score on screen
			g.setColor(Color.RED);
			g.setFont(new Font("Times New Roman", Font.BOLD, 35)); // 75-pt bold TNR font
			FontMetrics metrics = getFontMetrics(g.getFont()); // object for lining up text
			g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2,
					g.getFont().getSize()); // sets 'Score: ' in the top of screen

		} 
		else {
			GameOver(g); // call game over if it isn't running
		}
	}

	/**
	 * movement for the snake
	 */
	public void move() {
		for (int i = bodyParts; i > 0; i--) { // shift snake body parts over by 1
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}

		switch (direction) { // cases for each possible direction
		case 'U':
			y[0] = y[0] - UNIT_SIZE; // coord for head of snake, up to the next pos.
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE; // coord for head of snake, down to the next pos.
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE; // coord for head of snake, left to the next pos.
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE; // coord for head of snake, right to the next pos.
			break;
		}
	}

	/**
	 * check coords of snake and apple to eat apple
	 */
	public void checkApple() {
		if ((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++; // increase body parts by one
			applesEaten++; // works as the score
			Apple(); // generates new apple
		}
	}

	/**
	 * check if head collides with body or game borders
	 */
	public void checkForCollision() {
		for (int i = bodyParts; i > 0; i--) { // checking each body part
			if ((x[0] == x[i]) && (y[0] == y[i]))// if head coords = body coords
				running = false; // stop game
		}

		if (x[0] < 0) // if head touches left border
			running = false;
		if (x[0] > SCREEN_WIDTH-UNIT_SIZE) // if head touches right border
			running = false;
		if (y[0] < 0) // if head touches bottom border
			running = false;
		if (y[0] > SCREEN_HEIGHT-UNIT_SIZE) // if head touches top border
			running = false;
		
		if (!running)
			timer.stop(); // stop timer if there's a collision
	}

	/**
	 * what happens in the event that the game isn't running
	 * 
	 * @param g
	 */
	public void GameOver(Graphics g) {
		// draws the score on screen
		g.setColor(Color.RED);
		g.setFont(new Font("Times New Roman", Font.BOLD, 35)); // 75-pt bold TNR font
		FontMetrics scoreMetrics = getFontMetrics(g.getFont()); // object for lining up text
		g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - scoreMetrics.stringWidth("Score: " + applesEaten)) / 2,
				g.getFont().getSize()); // sets 'Score: ' in the top of screen

		// Game over text
		g.setColor(Color.RED);
		g.setFont(new Font("Times New Roman", Font.BOLD, 75)); // 75-pt bold TNR font
		FontMetrics gameOverMetrics = getFontMetrics(g.getFont()); // object for lining up text
		g.drawString("Game Over", (SCREEN_WIDTH - gameOverMetrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2); // sets 'Game Over' in the center of the screen
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (running) { // if the game is running
			move(); // move the snake
			checkApple(); // did we run into the apple?
			checkForCollision(); // did we run into ourselves or the wall?
		}
		repaint();

	}

	/**
	 * class for what keys are being pressed to control the snake's movements
	 * 
	 * @author Reskr
	 *
	 */
	public class keyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) { //can use left or 'a' key 
				if (direction != 'R') // prevents player from making 180 degree turns
					direction = 'L'; // go left
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) { //can use right or 'd' key
				if (direction != 'L')
					direction = 'R';
			}
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) { //can use up or 'w' key
				if (direction != 'D')
					direction = 'U';
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) { //can use down or 's' key
				if (direction != 'U')
					direction = 'D';
			}
		}

	}
}