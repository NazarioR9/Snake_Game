package model;

import java.util.List;

public class Game implements SnakeObserver{
	private int height;
	private int width;
	private SnakeObservable snake;
	public static Fruit fruit;

	public Game(int width, int height, Coordinate c) {
		this.height = height;
		this.width = width;
		snake = new SnakeObservable(this, c);
		fruit = new Fruit(this, 1);
		
		snake.register(this);
	}	

	public SnakeObservable getSnake() {
		return snake;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void step() {
		fruit.fruitify();
		snake.move();
	}
	
	public void growSnake() {
		snake.grow();
	}

	@Override
	public void notify(List<SnakeEvent> events) {
		//TO DO
	}

}
