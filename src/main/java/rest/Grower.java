package rest;


public interface Grower {
	// public void makeLonger();
	// public void makeShorter();
	public void setLength(int length, int imunityLength);
	public int getLength(int current);

	public int[] getPositionsAllSend(int[] filler, int pointer);
	// public Shape[] getPositionsAllCrash(Shape[] filler, int pointer);
	
	public GrowerDetails getGrower();
	
	public void setRotationAll(int rotation);

}
