package rest;

public enum Directions {
	UP (0, -1),
	RIGHT (1, 0),
	DOWN (0, 1),
	LEFT (-1, 0);
	
	private final double xMultiplier;
	private final double yMultiplier;
	
	Directions(double xMultiplier, double yMultiplier) {
		this.xMultiplier = xMultiplier;
		this.yMultiplier = yMultiplier;
	}
	private double xMultiplier () {return xMultiplier;}
	private double yMultiplier () {return yMultiplier;}


}
