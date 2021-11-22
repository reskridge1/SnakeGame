package game;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame() {
		this.add(new GamePanel());
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); //doesn't allow frame to be resized
		this.pack(); //allows all components to be at minimum size to display
		this.setVisible(true); //sets the frame to visible
		this.setLocationRelativeTo(null); //place in middle of screen
	}
}
