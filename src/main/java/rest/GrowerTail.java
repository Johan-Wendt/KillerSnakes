package rest;

public class GrowerTail extends InteractorBase implements Tail {
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

	@Override
	public int[] getPositionsCrash() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleGettingCrashed(Mover violator) {
		// TODO Auto-generated method stub

	}

	public void setLength(int current) {
		if (current > 1) {
			if (tail == null) {
				tail = new GrowerTail(super.getInteractor(), owner);

			}
			tail.setLength(current - 1);

		} else {
			removeAllTails();
		}
	}

}
