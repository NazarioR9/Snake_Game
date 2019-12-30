package model;

public enum Direction {
	Up(0, -1), Down(0, 1), Left(-1, 0), Right(1, 0);

	private int x;
	private int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
	    return x;
	}

	public int getY() {
	    return y;
	}	

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public boolean equal(Direction other) {
		return other.getX() == x && other.getY() == y;
	}
	
	public static int colineaire(Direction d1, Direction d2) {
		if(d1.equal(d2)) return -1;
		
		return d1.getX()*d2.getY() - d2.getX()*d1.getY();
	}
}
