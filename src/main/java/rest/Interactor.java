package rest;

public interface Interactor {
	public int[] getPositionsSend();
	public int[] getPositionsCrash();
	public void handleGettingCrashed(Mover violator);
	public void setxPos(int newX);
	public int getxPos();
	public void setyPos(int newX);
	public int getyPos();
	

}
