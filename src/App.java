import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Gui.Component;
import model.Coordinate;
import model.Game;


public class App {
	JFrame frame = new JFrame();
	Component component;
	Game game;
	public KeyListener listener = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			System.out.println("Key pressed");
			int key = e.getKeyCode();
			game.getSnake().getKey(key);
		}
	};
	
	public App() {
		
		int size = 50;
		int pc = Component.pixelCell;
		Coordinate start = new Coordinate(30,5);
		
		game = new Game(size, size, start);
		component = new Component(game.getSnake().getBody(), Game.fruit);
	    
	    component.addKeyListener(listener);
	    
		frame.setTitle("Snake");
		frame.setSize(size*pc, size*pc);
		frame.setContentPane(component);
		frame.addKeyListener(listener);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
	}
	
	public void play() {
		while(game.getSnake().isAlive()) {
			component.obtainSnakeBody(game.getSnake().getBody());
			component.repaint();
			game.step();
			sleep(110);
		}
	}

	private void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		App app = new App();
		app.play();
	}
}
