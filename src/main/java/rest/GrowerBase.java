package rest;

public abstract class GrowerBase extends TurnerBase implements Grower {
	private GrowerTail tail;

	// Standard constructor
	public GrowerBase(GrowerDetails grower) {
		super(grower);
		setLength(grower.startLength(), grower.imunityLength());

	}

	public GrowerBase(GrowerDetails grower, boolean head) {
		super(grower);
		if (!head) {
			super.setForm(grower.tailForm());
		}
	}

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

	public void setLength(int newLength, int imunityLength) {
		if (newLength > 1) {
			if (tail == null) {
				tail = new GrowerTail(super.getInteractor(), this);
				if (imunityLength > 0) {
					tail.setImuneToCrash(this);
					imunityLength--;
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
	public void move() {
		if (tail != null) {
			tail.follow(super.getxPos(), super.getyPos(), super.getRotation());
		}
		super.move();

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

	/**
	 * @Override public Shape[] getPositionsAllCrash(Shape[] filler, int
	 *           pointer) { Rectangle crashShape = new Rectangle((int)
	 *           super.getxPos(),(int) super.getyPos(), super.getHeight(),
	 *           super.getWidth()); rotateCrashShape(crashShape);
	 *           filler[pointer] = crashShape; pointer ++; return (tail == null)
	 *           ? filler : tail.getPositionsAllCrash(filler, pointer); }
	 **/
	@Override
	public void testCrashing(Interactor violator) {
		super.testCrashing(violator);
	//	if (tail != null) {
	//		tail.testCrashing(violator);
	//	}

	}

	public GrowerTail getTail() {
		return tail;
	}
	@Override
	public Interactor[] getEntireInteractor() {
		Interactor[] result = new Interactor[getLength()];
		result[0] = this;
		int pointer = 1;
		return (tail == null) ? result : tail.getInteractorAll(result, pointer);
		
	}

}
