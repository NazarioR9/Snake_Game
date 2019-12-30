package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SnakeObservable{

	private final int INITSIZE = 3;

	private List<Coordinate> body;
	private Game game;
	private Direction direction;
	private Direction lastDirection;
	private boolean alive;
	private List<SnakeObserver> observers;
	private Saved saved;

	public SnakeObservable(Game game, Coordinate start) {		
		observers = new ArrayList<>();
		alive = true;
		this.game = game;
		direction = Direction.Right;
		lastDirection = direction;
		body = new ArrayList<>();
		for(int i = INITSIZE; i > 0; i--) {
			body.add(new Coordinate(start.getX()-i,start.getY()));
		}
		
	}
	
	void move() {		
		//waitForDirection();
		
		List<SnakeEvent> events = new ArrayList<SnakeEvent>();
		Coordinate head, nhead, rmv;
		
		if(Direction.colineaire(direction, lastDirection) == 0) {
			System.out.println("inverse");
			inverseBody();
			lastDirection = direction;
		}
		
		
		head =  body.get(0);
		rmv = body.remove(body.size()-1); //remove last
		nhead = new Coordinate(head.getX()+direction.getX(), head.getY()+direction.getY());
		
		body.add(0, nhead);
		
		saved.s.setFields(rmv, direction); //save the queue position
		
		events.add(new SnakeEvent(nhead, SnakeEvent.ChangeType.ENTER));
		events.add(new SnakeEvent(rmv, SnakeEvent.ChangeType.LEAVE));
		notifyObserver(events);
		
		outOfBounds();
	}

	private void inverseBody() {
		List<Coordinate> b = new ArrayList<Coordinate>();
		for(Coordinate c: body) {
			b.add(0, c);
		}
		
		body = b;
	}
	
	public void grow() {
		body.add(saved.s.getCoordinate());
	}
	
	public void register(SnakeObserver o) {
		observers.add(o);
	}
	
	public void unregister(SnakeObserver o) {
		observers.remove(o);
	}
	
	private void notifyObserver(List<SnakeEvent> events) {
		for (SnakeObserver snakeObserver : observers) {
			snakeObserver.notify(events);
		}
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public List<Coordinate> getBody() {		
		return new ArrayList<>(body);
	}

	public boolean isAlive() {
		return alive;
	}
	
	private void outOfBounds() {
		Coordinate f = body.get(0), l = body.get(body.size()-1);
		int w = game.getWidth(), h = game.getHeight();
		
		alive = oOB(f, w, h) && oOB(l, w, h);
		
		if(!alive)
			System.out.println("Dead !!!!");
	}

	private boolean oOB(Coordinate f, int w, int h) {
		return f.getX() >= 0 && f.getY() >= 0 && f.getX() < w && f.getY() < h;
	}
	
	public void waitForDirection() {
		String key = JOptionPane.showInputDialog("Move: \n 8:UP 2:DOWN 4:LEFT 6:RIGHT");
		
		if(key.equals("8")) {
			direction = Direction.Up;
		}else if(key.equals("2")) {
			direction = Direction.Down;
		}else if(key.equals("4")) {
			direction = Direction.Left;
		}else if(key.equals("6")) {
			direction = Direction.Right;
		}else {
			waitForDirection();
		}
	}
	
	public void getKey(int key) {
		System.out.println("Got Key");
		lastDirection = direction; 
		if(key == KeyEvent.VK_UP) {direction = Direction.Up;}
		else if(key == KeyEvent.VK_DOWN) {direction = Direction.Down;}
		else if(key == KeyEvent.VK_LEFT) {direction = Direction.Left;}
		else if(key == KeyEvent.VK_RIGHT) {direction = Direction.Right;}
	}

}

class Saved{
 	static Saved s = new Saved();
 	private Coordinate coord = null;
 	private Direction direction = null;
 	
	private Saved() {
	}
	
	public void setFields(Coordinate c, Direction d) {
		coord = c;
		direction = d;
	}
	
	public Direction getDirection() {
		return direction;
	}
	public Coordinate getCoordinate() {
		return coord;
	}
}
