package rest;

public interface Controller {
	public void gameRound();
	public int[] getAllPositions();
	public Interactor craschCheck(int xPos,int yPos, Interactor interactor);

}
