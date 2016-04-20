package rest;

public enum Directions {
	UP (0, -1, 0),
	RIGHT (1, 0, 90),
	DOWN (0, 1, 180),
	LEFT (-1, 0, 270);
	
	private final int xMultiplier;
	private final int yMultiplier;
	private final int degreesTurned;
	
	Directions(int xMultiplier, int yMultiplier, int degreesTurned) {
		this.xMultiplier = xMultiplier;
		this.yMultiplier = yMultiplier;
		this.degreesTurned = degreesTurned;
	}
	private int xMultiplier () {return xMultiplier;}
	private int yMultiplier () {return yMultiplier;}
	public int getxMultiplier() {
		return xMultiplier;
	}
	public int getyMultiplier() {
		return yMultiplier;
	}
	public int getDegreesTurned() {
		return degreesTurned;
	}


}
