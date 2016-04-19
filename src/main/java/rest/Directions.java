package rest;

public enum Directions {
	UP (0, -1),
	RIGHT (1, 0),
	DOWN (0, 1),
	LEFT (-1, 0);
	
	private final int xMultiplier;
	private final int yMultiplier;
	
	Directions(int xMultiplier, int yMultiplier) {
		this.xMultiplier = xMultiplier;
		this.yMultiplier = yMultiplier;
	}
	private int xMultiplier () {return xMultiplier;}
	private int yMultiplier () {return yMultiplier;}
	public int getxMultiplier() {
		return xMultiplier;
	}
	public int getyMultiplier() {
		return yMultiplier;
	}


}
