package Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import model.Coordinate;
import model.Fruit;

public class Component extends JComponent{
	private static final long serialVersionUID = 1L;
	
	private List<Coordinate> body = new ArrayList<>();
	private Fruit fruit;
	public static int pixelCell = 10;
	
	public Component(List<Coordinate> b, Fruit f) {
		super();
		this.setBackground(Color.black);
		obtainSnakeBody(b);
		fruit = f;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.blue);
		for(Coordinate b: body) {
			g.fillOval(pixelCell*b.getX(), pixelCell*b.getY(), pixelCell, pixelCell);
		}
		g.setColor(Color.green);
		for(Coordinate c: fruit.getPositions()) {
			g.fillOval(pixelCell*c.getX(), pixelCell*c.getY(), pixelCell, pixelCell);
		}
	}
	
	public void obtainSnakeBody(List<Coordinate> b) {
		body = b;
	}
}
