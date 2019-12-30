package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fruit implements SnakeObserver{
	int nbOfFruits = 1;
	int pas = 0;
	List<Coordinate> positions;
	Game game;
	
	public Fruit(Game g) {
		game = g;
		positions = new ArrayList<Coordinate>();
		fruitify();
		
		g.getSnake().register(this);
	}
	public Fruit(Game g, int nb) {
		game = g;
		nbOfFruits = nb;
		positions = new ArrayList<Coordinate>();
		fruitify();
		
		g.getSnake().register(this);
	}
	
	public void fruitify() {
		if(pas == 0 || positions.size() == 0) {
			pickPosition();
			resetPas();
		}
		pas--;
		
	}
	
	public void pickPosition() {
		positions.clear();
		
		int h = game.getHeight(), w = game.getWidth();
		Random rand = new Random();
		
		for(int i = 0; i < nbOfFruits; i++) {
			int nh = rand.nextInt(h), nw = rand.nextInt(w);
			positions.add(new Coordinate(nw, nh));
		}
	}
	
	public void resetPas() {
		pas = 50;
	}
	
	public List<Coordinate> getPositions() {
		return positions;
	}
	
	@Override
	public void notify(List<SnakeEvent> events) {
		for(SnakeEvent e: events) {
			if(e.getChangeType() == SnakeEvent.ChangeType.ENTER) {
				Coordinate eventCoord = e.getCoordinate();
				List<Coordinate> remove = new ArrayList<Coordinate>();
				for(Coordinate c: positions) {
					if(c.equals(eventCoord)) {
						game.growSnake();
						remove.add(c);
					}
				}
				positions.removeAll(remove);
			}
		}
	}
	
}
