package game;

import javax.swing.JFrame;

public class Game extends JFrame {

	public Game() {
		add(new Board());
		this.setResizable(true);
		this.pack();
		this.setVisible(true);
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game();
	}

}
