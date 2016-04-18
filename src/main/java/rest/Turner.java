package rest;

public interface Turner extends Mover{
	
	public void setCurrentDirection(Directions direction);
	public Directions getCurrentDirection();
	public Directions getmovingDirection();
	public void turn(Directions direction);

}
