package rest;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javafx.scene.shape.SVGPath;

public interface Interactor {
	public int[] getPositionsSend();

	//public Shape getPositionsCrashed();

	//public Shape getPositionsCrashing();

	public void handleGettingCrashed(Interactor violator);

	public void handleCrashing(Interactor violator);

	public void testCrashing(Interactor violator);

	public void setxPos(double newX);

	public double getxPos();

	public void setyPos(double newX);

	public double getyPos();

	public Forms getForm();

	public void setForm(Forms form);

	public void setRotation(double degrees);

	public boolean isTestCrashingInto();

	public void setTestCrashingInto(boolean testCrashingInto);

	public InteractorDetails getInteractor();

	public boolean isInvincible();

	public void setInvincible(boolean invincible);

	public Interactor getImuneToCrash();

	public void setImuneToCrash(Interactor imuneToCrash);

	public double getRotation();

	public int getHeight();

	public int getWidth();
	
	SVGPath getCrashBorders();

}
