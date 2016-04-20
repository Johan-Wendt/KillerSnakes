package rest;

public abstract class InteractorBase implements Interactor {
	private int xPos;
	private int yPos;
	private int rotation;
	private boolean toBeRemoved = false;
	private InteractorDetails interactor;
	private int width;
	private int height;
	private Forms form;

	public InteractorBase(InteractorDetails interactor) {
		this.interactor = interactor;
		width = interactor.width();
		height = interactor.height();
		form = interactor.form();

	}

	public InteractorBase(InteractorDetails interactor, Forms form, int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.interactor = interactor;
		width = interactor.width();
		height = interactor.height();
		this.form = form;

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

	public void setRotation(int degrees) {
		this.rotation = degrees;
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

	public int[] getPositionsSend() {
		int[] result = { interactor.subType(), xPos, yPos, width, height, rotation / 30, form.sendValue() };
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

	public Forms getForm() {
		return form;
	}

	public void setForm(Forms form) {
		this.form = form;
	}
}
