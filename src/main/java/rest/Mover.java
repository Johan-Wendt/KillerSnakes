package rest;

public interface Mover extends Actor{
	public void move();
	public void handleCrashing(Interactor victim);
	public void setMovingDirection(Directions direction);
	public Directions getMovingDirection();

}
