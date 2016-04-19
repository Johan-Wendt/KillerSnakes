package rest;

import java.util.ArrayList;

public interface TypeController extends Controller {
	public void controllerRound(MasterController masterController);
	
	public void act(MasterController masterController);

	public void addToActingObjects(Actor actor);

	public void disposeOfRemovables();

	public ArrayList<Actor> getActingObjects();
	
	public void setTypeControlled(Types type);
	
	public int getTypeControlled();

}
