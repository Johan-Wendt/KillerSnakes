package rest;

import java.awt.Shape;

public interface Grower {
//	public void makeLonger();
//	public void makeShorter();
	public void setLength(int current, int imunityLength);
	public int[] getPositionsAllSend(int[] filler, int pointer);
	//public Shape[] getPositionsAllCrash(Shape[] filler, int pointer);
	

}
