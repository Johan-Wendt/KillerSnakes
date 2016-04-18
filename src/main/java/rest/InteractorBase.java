package rest;

public abstract class InteractorBase implements Interactor{
	private int xPos;
	private int yPos;
	private int rotation;
	private boolean toBeRemoved = false;
	private InteractorDetails interactor;
	
	public InteractorBase(InteractorDetails interactor) {
		this.interactor = interactor;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public boolean isToBeRemoved() {
		return toBeRemoved;
	}

	public void setToBeRemoved(boolean toBeRemoved) {
		this.toBeRemoved = toBeRemoved;
	}

	public InteractorDetails getInteractor() {
		return interactor;
	}

	public void setInteractor(Interactors interactor) {
		this.interactor = interactor;
	}
}
