package rest;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class TypeControllerBase implements TypeController {
	private ArrayList<Actor> actingObjects = new ArrayList<>();
	private int typesControlled;

	public TypeControllerBase(Types type) {
		setTypeControlled(type);
	}

	public void controllerRound() {
		act();
	//	disposeOfRemovables();
	}

	public void act() {
		for (Actor actor : actingObjects) {
			actor.gameRound();
		}
	}

	public int[] getAllPositionsSend() {
		int length = getLength();

		int[] result = new int[1 + length * Constants.INTS_SENT_PER_OBJECT + 1];
		result[0] = getTypeControlled();
		int pointer = 1;
		for (Actor actor : actingObjects) {
			int[] tempResult = actor.getPositionsSend();
			int n = 0;
			while (n < tempResult.length) {
				result[pointer] = tempResult[n];
				n++;
				pointer++;
			}
		}
		result[result.length - 1] = -1;
		return result;

	}

	@Override
	public ArrayList<Interactor> getCrashers() {
		ArrayList<Interactor> result = new ArrayList<>();
		for (Actor actor : actingObjects) {
			if (actor.isTestCrashingInto()) {
				result.add(actor);
				if(actor instanceof Bonus) {
					System.out.println("Bonus");
				}
			}
		}
		
		return result;
	}
	public ArrayList<Interactor> getPossibleVictims() {
		ArrayList<Interactor> result = new ArrayList<>();
		for (Actor actor : actingObjects) {
			Interactor[] partResult = actor.getEntireInteractor();
			int n = 0;
			while(n < partResult.length) {
				result.add(partResult[n]);
				n++;
			}
		}
		
		return result;
		
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

	public int getLength() {
		int length = 0;
		for (Actor actor : actingObjects) {
			length += actor.getLength();
		}
		return length;
	}

	public void testCrashInto(ArrayList<Interactor> violaters) {
		ArrayList<Interactor> victims = getPossibleVictims();
		for (Interactor violater : violaters) {
			for (Interactor victim : victims) {
				victim.testCrashing(violater);
			}
		}
	}

}
