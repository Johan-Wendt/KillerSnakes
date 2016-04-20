package rest;

public interface Mover extends Actor{
	public void move();
	public void handleCrashing(Interactor victim);
	public void turn(Directions direction);
	public void setMovingDirection(Directions direction);

}
