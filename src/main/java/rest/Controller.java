package rest;

import java.util.ArrayList;

public interface Controller {


	// public Shape getCrashingPositions();
	public ArrayList<Interactor> getCrashers();

	public void testCrashInto(ArrayList<Interactor> violaters);
	
	public void controllerRound();
	
	public void disposeOfRemovables();
	
	

}
