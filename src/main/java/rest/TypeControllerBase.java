package rest;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class TypeControllerBase implements TypeController {
	private ArrayList<Actor> actingObjects = new ArrayList<>();
	private int typesControlled;
	private boolean hasSoundToPlay;

	public TypeControllerBase(Types type) {
		setTypeControlled(type);
	}

	public void controllerRound() {
		act();
	}

	public void act() {
		for (Actor actor : actingObjects) {
			actor.gameRound();
		}
	}

	public int appendAllPositionsSend(int[] message, int pointer) {

		message[pointer] = getTypeControlled();
		pointer++;
		for (Actor actor : actingObjects) {
			int[] tempResult = actor.getPositionsSend();
			int n = 0;
			while (n < tempResult.length) {
				message[pointer] = tempResult[n];
				n++;
				pointer++;
			}
		}
		message[pointer] = -1;
		pointer++;
		return pointer;
	}

	@Override
	public ArrayList<Interactor> getCrashers() {
		ArrayList<Interactor> result = new ArrayList<>();
		for (Actor actor : actingObjects) {
			if (actor.isTestCrashingInto()) {
				result.add(actor);
			}
		}

		return result;
	}

	public ArrayList<Interactor> getPossibleVictims() {
		ArrayList<Interactor> result = new ArrayList<>();
		for (Actor actor : actingObjects) {
			if (actor.isTestGettingCrashed()) {
				Interactor[] partResult = actor.getEntireInteractor();
				int n = 0;
				while (n < partResult.length) {
					result.add(partResult[n]);
					n++;
				}
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
	public int getSendInfoSize() {
		return 1 + getLength() * Constants.INTS_SENT_PER_OBJECT + 1 + getSoundSendInfoSize();
	}
	
	public int appendAllSoundsSend(int[] message, int pointer) {

		for (Actor actor : actingObjects) {
			if(actor.hasQueuedSound()) {
				message[pointer] = actor.getQueuedSound();
				pointer++;
			}
		}
		return pointer;
	}
	
	public int getSoundSendInfoSize() {
		int result = 0;
		for (Actor actor : actingObjects) {
			if(actor.hasQueuedSound()) {
				hasSoundToPlay = true;
				result++;
			}
		}
		return result;
	}

	public boolean hasSoundToPlay() {
		return hasSoundToPlay;
		
	}
	public void setHasSoundToPlay(boolean hasSound) {
		hasSoundToPlay = hasSound;
	}
	
}
