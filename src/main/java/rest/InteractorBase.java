package rest;

public abstract class InteractorBase implements Interactor{
	private int xPos;
	private int yPos;
	private int rotation;
	private boolean toBeRemoved = false;
	private InteractorDetails interactor;
	private int width;
	private int height;
	
	public InteractorBase(InteractorDetails interactor) {
		this.interactor = interactor;
		width = interactor.width();
		height = interactor.height();
		
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

	public void setToBeRemoved() {
		toBeRemoved = true;
	}

	public InteractorDetails getInteractor() {
		return interactor;
	}

	public void setInteractor(Interactors interactor) {
		this.interactor = interactor;
	}
	
	public int [] getPositionsSend() {
		int[] result = {interactor.type(), interactor.subType(), xPos, yPos};
		return result;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setInteractor(InteractorDetails interactor) {
		this.interactor = interactor;
	}
}
