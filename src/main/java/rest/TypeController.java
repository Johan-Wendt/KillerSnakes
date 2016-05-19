package rest;

import java.util.ArrayList;

public interface TypeController extends Controller {
	

	public void act();

	public void addToActingObjects(Actor actor);

	public ArrayList<Actor> getActingObjects();

	public void setTypeControlled(Types type);

	public int getTypeControlled();

	public int getLength();
	
	public ArrayList<Interactor> getPossibleVictims();
	
	public int getSendInfoSize();
	
	public int appendAllPositionsSend(int[] message, int pointer);
	
	public int appendAllSoundsSend(int[] message, int pointer);
	
	public boolean hasSoundToPlay();
	
	public void setHasSoundToPlay(boolean hasSound);
	

}
