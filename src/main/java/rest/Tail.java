package rest;

public interface Tail {
	public GrowerBase getOwner();
	public int[] getPositionsAllSend(int[] filler, int pointer);
	public void removeAllTails();
	public int getLength(int current);
	public void setLength(int current, int imunityLength);
	public void follow(double xPos, double yPos, double rotation);

}
