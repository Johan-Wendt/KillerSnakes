package rest;

import java.util.LinkedList;

public interface Turner extends Mover {

	public Directions getmovingDirection();

	// public void turn(Directions direction);
	public void steer(Directions newDirection);

	public void setSteeringDirection(Directions direction);

	public void emptyTurnInstructions();
	
	public LinkedList<Integer> getTurnInstructions();
	public void setTurnInstructions(LinkedList<Integer> turnInstructions);

	public LinkedList<Integer> getWhenToReadInstructions();
	public void setWhenToReadInstructions(LinkedList<Integer> whenToReadInstructions);

}
