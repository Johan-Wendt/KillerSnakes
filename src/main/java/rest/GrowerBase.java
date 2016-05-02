package rest;

public abstract class GrowerBase extends TurnerBase implements Grower, Slave {
	private GrowerTail tail;
	private Interactor owner;

	// Standard constructor
	public GrowerBase() {
	}
	
	public GrowerBase(GrowerDetails grower) {
		super(grower);
	}

	//public GrowerBase(GrowerDetails grower, boolean head) {
	//	super(grower);
	//	if (!head) {
	//		super.setForm(grower.tailForm());
	//	}
	//}

	public GrowerBase(InteractorDetails interactor, Forms form, int xPos, int yPos, Directions direction) {
		super(interactor, form, xPos, yPos, direction);
	}

	@Override
	public int[] getPositionsSend() {
		int[] filler = new int[getLength() * Constants.INTS_SENT_PER_OBJECT];
		int pointer = 0;

		// int[] result = { interactor.subType(), xPos, yPos, width, height,
		// rotation / 30, form.sendValue() };
		return getPositionsAllSend(filler, pointer);
	}
	@Override
	public int getLength() {
		int current = 1;
		return (tail == null) ? current : tail.getLength(current);

	}

	@Override 
	public int getLength(int current) {
		//int current = 1;
		current ++;
		return (tail == null) ? current : tail.getLength(current);

	}

	public void setLength(int newLength, int imunityLength) {
		imunityLength--;
		if (newLength > 1) {
			if (tail == null) {
				tail = new GrowerTail(getGrower(), owner, this );
				if (imunityLength >= 0) {
					tail.setImuneToCrash(owner);
					
				}

			}

			tail.setLength(newLength - 1, imunityLength);

		} else {
			removeAllTails();
		}
	}


	public void removeAllTails() {
		if (tail != null) {
			tail.removeAllTails();
			tail = null;
		}
	}

	@Override
	public void act() {		
		super.act();
		if (tail != null && getTimesActed() == tail.getTimesActed() + getHeight()) {
			tail.act();
		}
	}
	

	public int[] getPositionsAllSend(int[] filler, int pointer) {
		filler[pointer] = super.getInteractor().subType();
		filler[pointer + 1] = Math.round((long) super.getxPos());
		filler[pointer + 2] = Math.round((long) super.getyPos());
		filler[pointer + 3] = super.getWidth();
		filler[pointer + 4] = super.getHeight();
		filler[pointer + 5] = (int) (super.getRotation() / 30);
		filler[pointer + 6] = super.getInteractor().form().sendValue();
		pointer += 7;
		return (tail == null) ? filler : tail.getPositionsAllSend(filler, pointer);
	}


	public GrowerTail getTail() {
		return tail;
	}
	@Override
	public Interactor[] getEntireInteractor() {
		Interactor[] result = new Interactor[getLength()];
		result[0] = this;
		int pointer = 1;
		return (tail == null) ? result : tail.getEntireInteractor(result, pointer);
		
	}
	protected Interactor[] getEntireInteractor(Interactor[] result, int pointer) {
		result[pointer] = this;
		pointer ++;
		return (tail == null) ? result : tail.getEntireInteractor(result, pointer);
		
	}
	public GrowerDetails getGrower() {
		GrowerDetails grower = (GrowerDetails) getInteractor();
		return grower;
	}
	
	
	public void setxPosAll(double newX){
		super.setxPos(newX);
		if(tail != null) {
			tail.setxPosAll(newX);
		}
	}
	public void setyPosAll(double newY){
		super.setyPos(newY);
		if(tail != null) {
			tail.setyPosAll(newY);
		}
	}
	public Interactor getLastTail() {
		if(tail == null) {
			return this;
		}
		return tail.getLastTail();
	}
	public void setOwner(Interactor owner) {
		this.owner = owner;
	}
	public Interactor getOwner() {
		return owner;
	}
	@Override
	public void setMovingSpeed(int speed) {
		super.setMovingSpeed(speed);
		if(tail != null) {
			tail.setMovingSpeed(speed);
		}
	}
	@Override
	public void setSteerPoint(Directions newDirection, int whenToReadInstructions) {
		super.setSteerPoint(newDirection, whenToReadInstructions);
		if(tail != null) {
			tail.setSteerPoint(newDirection, whenToReadInstructions);
		}
	}
	@Override
	public void setMovingDirection(Directions direction) {
		super.setMovingDirection(direction);
		if(tail != null) {
			tail.setMovingDirection(direction);
		}
	}
	public void setSteeringDirection(Directions direction) {
		super.setSteeringDirection(direction);
		if(tail != null) {
			tail.setSteeringDirection(direction);
		}
	}
	
	public void setRotationAll(int rotation) {
		super.setRotation(rotation);
		if(tail != null) {
			tail.setRotationAll(rotation);
		}
	}
	@Override
	public void setTimesActed(int times) {
		super.setTimesActed(times);
		if(tail != null) {
			tail.setTimesActed(times);
		}
	}
	
	@Override
	public void emptyTurnInstructions() {
		super.emptyTurnInstructions();
		if(tail != null) {
			tail.emptyTurnInstructions();
		}
	}
	@Override
	public void testCrashing(Interactor violator) {
		if (!(owner.getImuneToCrash() == violator || violator.getImuneToCrash() == owner)) {
			super.testCrashing(violator);
		}
		//setTestCrashingInto(false);
		//violator.setTestCrashingInto(false);

	}

}
