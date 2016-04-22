package rest;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public abstract class InteractorBase implements Interactor {
	private double xPos;
	private double yPos;
	private double rotation;
	private boolean toBeRemoved = false;
	private InteractorDetails interactor;
	private int width;
	private int height;
	private Forms form;
	private boolean TestCrashingInto;
	private boolean invincible = false;
	private Interactor imuneToCrash = null;

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

	/**
	 * public void setInteractor(Interactors interactor) { this.interactor =
	 * interactor; }
	 **/
	public int[] getPositionsSend() {
		int[] result = { interactor.subType(), (int) xPos, (int) yPos, width, height, (int) (rotation / 30),
				form.sendValue() };
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

	@Override
	public Rectangle2D getPositionsCrashed() {
		Rectangle crashShape = new Rectangle((int) xPos, (int) yPos, height, width);
		rotateCrashShape(crashShape);
		return crashShape;
	}

	@Override
	public Shape getPositionsCrashing() {
		Rectangle crashShape = new Rectangle((int) xPos, (int) yPos, height, width);
		rotateCrashShape(crashShape);
		return crashShape;
	}

	protected void rotateCrashShape(Shape crashShape) {

		Path2D.Double path = new Path2D.Double();
		path.append(crashShape, false);
		AffineTransform t = new AffineTransform();
		t.rotate(rotation);
		path.transform(t);
	}

	public void testCrashing(Interactor violator) {
		if (!(violator.equals(this))) {
			Rectangle2D me = getPositionsCrashed();
			Shape them = violator.getPositionsCrashing();
			if (them.intersects(me)) {
				handleGettingCrashed(violator);
				if (imuneToCrash == null) {
					violator.handleCrashing(this);
				}
				else if (imuneToCrash != violator) {
					violator.handleCrashing(this);
				}
			}
		}

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
}
