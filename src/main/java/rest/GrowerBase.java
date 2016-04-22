package rest;

import java.lang.reflect.InvocationTargetException;

public abstract class GrowerBase extends TurnerBase implements Grower {
	private GrowerTail tail;

	// Standard constructor
	public GrowerBase(GrowerDetails grower) {
		super(grower);
		setLength(grower.startLength());

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

	public void setLength(int newLength) {
		if (newLength > 1) {
			if (tail == null) {
				tail = new GrowerTail(super.getInteractor(), this);

			}

			tail.setLength(newLength - 1);

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
		filler[pointer + 1] = (int) super.getxPos();
		filler[pointer + 2] = (int) super.getyPos();
		filler[pointer + 3] = super.getWidth();
		filler[pointer + 4] = super.getHeight();
		filler[pointer + 5] = (int) (super.getRotation() / 30);
		filler[pointer + 6] = super.getInteractor().form().sendValue();
		pointer += 7;
		return (tail == null) ? filler : tail.getPositionsAllSend(filler, pointer);
	}

}
