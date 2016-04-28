package rest;

import java.awt.Rectangle;
import java.awt.Shape;

public class GrowerTail extends InteractorBase implements Tail, Grower {
	private GrowerBase owner;
	private GrowerTail tail;
	private double lastXposDiff;
	private double lastYposDiff;

	public GrowerTail(InteractorDetails interactor, GrowerBase owner) {
		super(interactor);

		
		
		super.setxPos(owner.getLastTail().getxPos());
		super.setyPos(owner.getLastTail().getyPos());
		super.setRotation(owner.getLastTail().getRotation());

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
	
	public void followNew(double xPosDiff, double yPosDiff, double rotation) {
		if (tail != null && !(tail.getxPos() == getxPos() && tail.getyPos() == getyPos())) {
			tail.followNew(lastXposDiff, lastYposDiff, super.getRotation());

		}
		super.setxPos(getxPos() + xPosDiff);
		super.setyPos(getyPos() + yPosDiff);
		lastXposDiff = xPosDiff;
		lastYposDiff = yPosDiff;
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


	@Override
	public void testCrashing(Interactor violator) {
		if(!(owner.getTail() == this) || owner.getTail().getTail() == this) {
			super.testCrashing(violator);
		}
		

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

	public GrowerTail getTail() {
		return tail;
	}

	@Override
	public GrowerDetails getGrower() {
		//This returns the owner details!!
		return owner.getGrower();
	}
	
	@Override
	public void setxPos(double newX){
		super.setxPos(newX);
		if(tail != null) {
			tail.setxPos(newX);
		}
	}
	public void setyPos(double newY){
		super.setyPos(newY);
		if(tail != null) {
			tail.setyPos(newY);
		}
	}
	public Interactor getLastTail() {
		if(tail == null) {
			return this;
		}
		return tail.getLastTail();
	}
}
