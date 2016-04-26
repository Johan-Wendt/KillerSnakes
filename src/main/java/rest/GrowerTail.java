package rest;

import java.awt.Rectangle;
import java.awt.Shape;

public class GrowerTail extends InteractorBase implements Tail, Grower {
	private GrowerBase owner;
	private GrowerTail tail;

	public GrowerTail(InteractorDetails interactor, GrowerBase owner) {
		super(interactor);

		super.setxPos(-10);
		super.setyPos(-10);
		this.owner = owner;
	}

	public GrowerBase getOwner() {
		return owner;
	}

	public int[] getPositionsAllSend(int[] filler, int pointer) {
		filler[pointer] = owner.getInteractor().subType();
		filler[pointer + 1] = (int) super.getxPos();
		filler[pointer + 2] = (int) super.getyPos();
		filler[pointer + 3] = super.getWidth();
		filler[pointer + 4] = super.getHeight();
		filler[pointer + 5] = (int) (super.getRotation() / 30);
		filler[pointer + 6] = super.getInteractor().form().sendValue();
		pointer += 7;
		return (tail == null) ? filler : tail.getPositionsAllSend(filler, pointer);
	}

	public void removeAllTails() {
		if (tail != null) {
			tail.removeAllTails();
			tail = null;
		}
	}

	public int getLength(int current) {
		current++;
		return (tail == null) ? current : tail.getLength(current);

	}

	public void follow(double xPos, double yPos, double rotation) {
		if (tail != null) {
			tail.follow(super.getxPos(), super.getyPos(), super.getRotation());

		}
		super.setxPos(xPos);
		super.setyPos(yPos);
		super.setRotation(rotation);
	}

	public void setLength(int current, int imunityLength) {
		if (current > 1) {
			if (tail == null) {
				tail = new GrowerTail(super.getInteractor(), owner);
				if (imunityLength > 0) {
					tail.setImuneToCrash(owner);
					imunityLength--;
				}

			}
			tail.setLength(current - 1, imunityLength);

		} else {
			removeAllTails();
		}
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

	public Interactor[] getInteractorAll(Interactor[] result, int pointer) {
		result[pointer] = this;
		pointer ++;
		return (tail == null) ? result : tail.getInteractorAll(result, pointer);
		
	}

	@Override
	public void handleGettingCrashed(Interactor violator) {

	}

	@Override
	public void handleCrashing(Interactor violator) {

	}
}
