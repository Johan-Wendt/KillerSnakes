package rest;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class TypeControllerBase implements TypeController {
	private ArrayList<Actor> actingObjects = new ArrayList<>();
	private int typesControlled;

	public TypeControllerBase(Types type) {
		setTypeControlled(type);
	}

	public void controllerRound(MasterController masterController) {
		act(masterController);
		disposeOfRemovables();
	}
	public void act(MasterController masterController) {
		for(Actor actor: actingObjects) {
			actor.gameRound();
		}
	}

	public int[] getAllPositionsSend() {
		int[] result = new int[1 + actingObjects.size() * Constants.INTS_SENT_PER_OBJECT + 1];
		result[0] = getTypeControlled();
		int pointer = 1;
		for(Actor actor: actingObjects) {
			int[] tempResult = actor.getPositionsSend();
			int n = 0;
			while(n < tempResult.length) {
				result[pointer] = tempResult[n];
				n++;
				pointer++;
			}
		}
		result[result.length - 1] = -1;
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
	
public void setTypeControlled(Types type) {
	typesControlled = type.type();
}
	
	public int getTypeControlled() {
		return typesControlled;
	}

}
