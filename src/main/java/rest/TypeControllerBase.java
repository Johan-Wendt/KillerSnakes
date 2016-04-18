package rest;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class TypeControllerBase implements TypeController {
	private ArrayList<Actor> actingObjects = new ArrayList<>();

	public TypeControllerBase() {

	}

	public void controllerRound(MasterController masterController) {
		act(masterController);
		disposeOfRemovables();
	}
	public void act(MasterController masterController) {
		for(Actor actor: actingObjects) {
			actor.act();
		}
	}

	public int[] getAllPositionsSend() {
		int[] result = new int[0];
		for(Actor actor: actingObjects) {
			result = HelperMethods.intConcatenator(result, actor.getPositionsSend());
		}
		return result;
		
	}

	public Interactor craschCheck(int xPos, int yPos, Interactor interactor) {
		return null;
	}

	public void addToActingObjects(Actor actor) {
		actingObjects.add(actor);
	}
	
	

	public void disposeOfRemovables() {
		Iterator<Actor> itr = actingObjects.iterator();
		while (itr.hasNext()) {
			Actor obj = itr.next();
			if (obj.isToBeRemoved()) {
				itr.remove();
			}
		}

	}
	
	public ArrayList<Actor> getActingObjects() {
		return actingObjects;
	}

}
