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
	
	public GamePanel() {
		random = new Random(); 
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT)); //sets the preferred dimension for screen
		this.setBackground(Color.BLACK); //sets the background to black
		this.setFocusable(true); //sets to focusable
		this.addKeyListener(new keyAdapter()); //adds event to listen to key presses
		startGame(); //starts the game
	}

	public void startGame() {
		
	}
	
	public void paintComponent(Graphics g) {
		
	}
	
	public void draw(Graphics g) {
		
	}
	
	public void move() {
		
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
