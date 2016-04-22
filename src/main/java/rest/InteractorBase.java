package rest;

public abstract class InteractorBase implements Interactor {
	private double xPos;
	private double yPos;
	private double rotation;
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

	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double degrees) {
		while(degrees < 0) {
			degrees += 360;
		}
		while(degrees > 360) {
			degrees -= 360;
		}
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
		int[] result = { interactor.subType(), (int) xPos, (int) yPos, width, height, (int) (rotation / 30), form.sendValue() };
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
