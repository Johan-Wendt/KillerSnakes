package rest;

import java.util.LinkedList;

public abstract class TurnerBase extends MoverBase implements Turner {
	private Directions steeringDirection;
	private LinkedList<Integer> turnInstructions = new LinkedList<>();
	private int stepsPerTurn = 6;

	public TurnerBase(InteractorDetails interactor) {
		super(interactor);
	}

	public TurnerBase(InteractorDetails interactor, Forms form, int xPos, int yPos, Directions direction) {
		super(interactor, form, xPos, yPos, direction);
		steeringDirection = direction;
	}

	@Override
	public void act() {
		super.act();
		turn();

	}

	public void turn() {
		if (turnInstructions.size() > 0) {
			super.setRotation(super.getRotation() + turnInstructions.poll());
			if (super.getRotation() % 90 == 0) {
				super.setMovingDirection(Directions.getDirection((int) super.getRotation()));
			}
		}

	}

	public Directions getSteeringDirection() {
		return steeringDirection;
	}
	public void setSteeringDirection(Directions direction) {
		steeringDirection = direction;
	}

	public void steer(Directions newDirection) {
		if (Math.abs(newDirection.getxMultiplier()) != Math.abs(steeringDirection.getxMultiplier())) {
			addToTurnInstructions(newDirection);
			steeringDirection = newDirection;

		}
	}

	private void addToTurnInstructions(Directions newDirection) {
		//Integer degreesPerTurn = (newDirection.getxMultiplier() + newDirection.getyMultiplier()) * 30;
		int totalTurn = (newDirection.getDegreesTurned() - steeringDirection.getDegreesTurned());
		if(totalTurn > 90) {
			totalTurn -= 360;
		}
		if(totalTurn < -90) {
			totalTurn += 360;
		}
		Integer degreesPerTurn = totalTurn / stepsPerTurn;
		int n = 0;
		while (n < stepsPerTurn) {
			turnInstructions.add(degreesPerTurn);
			n ++;
		}
	}
	public void emptyTurnInstructions() {
		turnInstructions.clear();
	}

}
