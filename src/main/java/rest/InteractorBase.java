package rest;

import java.util.LinkedList;

import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public abstract class InteractorBase implements Interactor {
	private double xPos;
	private double yPos;
	private double rotation;
	private boolean toBeRemoved = false;
	private InteractorDetails interactor;
	private int width;
	private int height;
	private Forms form;
	private boolean TestCrashingInto = false;
	private boolean TestGettingCrashed = true;
	private boolean invincible = false;
	private Interactor imuneToCrash = null;
	private final LinkedList<Integer> queuedSounds = new LinkedList<>();

	private SVGPath borders;
	
	public InteractorBase() {
		
	}

	public InteractorBase(InteractorDetails interactor) {
		this.interactor = interactor;
		width = interactor.width();
		height = interactor.height();
		form = interactor.form();
		setBorders(interactor.borders());

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
		while (degrees < 0) {
			degrees += 360;
		}
		while (degrees > 360) {
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

	public int[] getPositionsSend() {
		int[] result = { interactor.subType(), Math.round((long) xPos), Math.round((long) yPos), width, height,
				(int) (rotation / 30), form.sendValue() };
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

	public boolean isTestCrashingInto() {
		return TestCrashingInto;
	}

	public void setTestCrashingInto(boolean testCrashingInto) {
		TestCrashingInto = testCrashingInto;
	}

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public Interactor getImuneToCrash() {
		return imuneToCrash;
	}

	public void setImuneToCrash(Interactor imuneToCrash) {
		this.imuneToCrash = imuneToCrash;
	}

	public void testCrashing(Interactor violator) {
		if (!(violator.equals(this) || imuneToCrash == violator || violator.getImuneToCrash() == this) && closeEnoughToCrash(violator)) {

			if (putOnTop(violator)) {
				handleGettingCrashed(violator);
				if (imuneToCrash == null) {
					violator.handleCrashing(this);
				} else if (imuneToCrash != violator) {
					violator.handleCrashing(this);
				}
			}
		}
		//setTestCrashingInto(false);
		//violator.setTestCrashingInto(false);

	}

	private boolean closeEnoughToCrash(Interactor violator) {
		int largest = width;
		if (violator.getWidth() > largest) {
			largest = width;
		}
		if (height > largest) {
			largest = height;
		}
		if (violator.getHeight() > largest) {
			largest = violator.getHeight();
		}

		if (Math.abs(violator.getxPos() - xPos) < largest && Math.abs(violator.getyPos() - yPos) < largest) {
			return true;
		}
		return false;
	}

	public boolean putOnTop(Interactor violator) {

		Shape intersects = Shape.intersect(violator.getCrashBorders(), getCrashBorders());
		if (intersects.getBoundsInLocal().getWidth() != -1) {
			return true;
		}
		return false;
	}

	public SVGPath getCrashBorders() {

		setPosition();

		borders.setRotate(rotation);

		return borders;

	}

	private void setBorders(String content) {
		borders = new SVGPath();
		borders.setContent(content);
	}

	protected void setPosition() {
		if (borders != null) {
			borders.setLayoutX(xPos);
			borders.setLayoutY(yPos);
		}
	}

	public Interactor[] getEntireInteractor() {
		Interactor[] result = { this };
		return result;
	}

	public boolean isTestGettingCrashed() {
		return TestGettingCrashed;
	}

	public void setTestGettingCrashed(boolean testGettingCrashed) {
		TestGettingCrashed = testGettingCrashed;
	}

	public void addQueuedSounds(int type, int subType) {
		queuedSounds.add(type);
		queuedSounds.add(subType);
	}

}
