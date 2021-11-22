package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
	//private data fields
	private static final int SCREEN_WIDTH = 650;
	private static final int SCREEN_HEIGHT = 600;
	private static final int UNIT_SIZE = 24; //object size for screen
	private static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE; //how many units screen can hold
	private static final int DELAY = 75; //timer delay
	private final int x[] = new int[GAME_UNITS]; //holds all snake x-coords
	private final int y[] = new int[GAME_UNITS]; //holds all snake y-coords
	private int bodyParts = 3;
	private int applesEaten;
	private int appleX;
	private int appleY;
	private char direction = 'R'; //R (right {*default*}), L (left), U (up), D (down)
	private boolean running = false;
	Timer timer;
	Random random;
	
	/**
	 * constructor - sets our screen components and starts game
	 */
	public GamePanel() {
		random = new Random(); 
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT)); //sets the preferred dimension for screen
		this.setBackground(Color.BLACK); //sets the background to black
		this.setFocusable(true); //sets to focusable
		this.addKeyListener(new keyAdapter()); //adds event to listen to key presses
		startGame(); //starts the game
	}

	/**
	 * starts our game, creates new apple, and sets/starts the timer with a delay
	 */
	public void startGame() {
		Apple(); //creates a new apple on the screen
		running = true;
		timer = new Timer(DELAY, this); //creates a new timer with a delay
		timer.start(); //starts the timer
	}
	
	/**
	 * will place apple evenly in a random x-y coord
	 */
	public void Apple() {
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE; //set apple to some random x-coord in range of screen width/unit size
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE; //set apple to some random y-coord in range of screen width/unit size
	}
	
	/**
	 * calls our draw method and paints components to the screen
	 * @param g
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //calls parent class's paintComponent method
		draw(g);
	}
	
	/**
	 * draws our visual components
	 * @param g
	 */
	public void draw(Graphics g) {
		for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE;i++) { //draws lines on the screen to look like a grid
			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT); //draw lines across x-axis
			g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE); //draw lines across y-axis
		}
		g.setColor(Color.RED);
		g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); //randomly creates a red apple of UNIT_SIZE x UNIT_SIZE in one of the grid spaces
		
		for(int i = 0; i < bodyParts; i++) {
			if(i==0) { //head of snake
				g.setColor(Color.GREEN);
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); //UNIT_SIZE x UNIT_SIZE green snake at x-y
			}
			else { //snake body
				g.setColor(new Color(45,180,0)); //new color different shade of green
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			}
		}
	
	}
	
	/**
	 * movement for the snake
	 */
	public void move() {
		for(int i = bodyParts; i > 0; i--) { //shift snake body parts over by 1
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) { //cases for each possible direction
		case 'U':
			y[0] = y[0] - UNIT_SIZE; //coord for head of snake, up to the next pos.
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE; //coord for head of snake, down to the next pos.
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE; //coord for head of snake, left to the next pos.
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE; //coord for head of snake, right to the next pos.
			break;
		}
	}
	
	public void checkApple() {
		
	}
	
	public void checkForCollision() {
		
	}

	public void GameOver(Graphics g) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public class keyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
	}
	
}
