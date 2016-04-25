package rest;

public interface Turner extends Mover{
	
	
	public Directions getmovingDirection();
	//public void turn(Directions direction);
	public void steer(Directions newDirection);
	public void setSteeringDirection(Directions direction);
	public void emptyTurnInstructions();
	

}
