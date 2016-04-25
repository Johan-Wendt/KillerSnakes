package rest;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
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

	
	//  public void setInteractor(Interactors interactor) { this.interactor =
	 // interactor; }
	 
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
	public Shape getPositionsCrashed() {
		//Shape crashShape = rotateCrashShape(new Rectangle((int) xPos, (int) yPos, height, width));
		//rotateCrashShape(crashShape);
		return new Rectangle((int) xPos, (int) yPos, height, width);
	}

	@Override
	public Shape getPositionsCrashing() {
	//	Shape crashShape = rotateCrashShape(new Rectangle((int) xPos, (int) yPos, height, width));
		//rotateCrashShape(crashShape);
		return new Rectangle((int) xPos, (int) yPos, height, width);
	}

	protected Shape rotateCrashShape(Shape crashShape) {

		Path2D.Double path = new Path2D.Double();
		path.append(crashShape, false);
		AffineTransform t = new AffineTransform();
		t.rotate(Math.toRadians(rotation));
		path.transform(t);
		return path.createTransformedShape(t);
	}

/**
	protected void rotateCrashShape(Shape crashShape) {

		Path2D.Double path = new Path2D.Double();
		path.append(crashShape, false);
		AffineTransform t = new AffineTransform();
		t.rotate(Math.toRadians(rotation));
		path.transform(t);
	}
**/
	public void testCrashing(Interactor violator) {
		if (!(violator.equals(this)) && closeEnoughToCrash(violator)) {
				if (testAreaIntersection(violator)) {
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
	
	
	public static boolean testIntersection(Shape shapeA, Shape shapeB) {
		   Area areaA = new Area(shapeA);
		   areaA.intersect(new Area(shapeB));
		   return !areaA.isEmpty();
		}
	
	//private  boolean bs(Shape offender, Shape victim, Interactor violator) {
	private  boolean testAreaIntersection(Interactor violator) {
		Rectangle enemy = new Rectangle(violator.getWidth(), violator.getHeight());

		AffineTransform at = new AffineTransform();
	

		

	    at.translate(violator.getxPos(), violator.getyPos());
	    at.rotate(Math.toRadians(violator.getRotation()), 6, 1.5);
	    GeneralPath path1 = new GeneralPath();
	    path1.append(enemy.getPathIterator(at), false);
	    Area a1 = new Area(path1);
	    
	    Rectangle me = new Rectangle(width, height);
	    AffineTransform at2 = new AffineTransform();

	    at2.translate(xPos, yPos);
	    at2.rotate(Math.toRadians(rotation), 6, 1.5);
	    GeneralPath path2 = new GeneralPath();
	    path2.append(me.getPathIterator(at2), false);
	    
	    
	    Area a2 = new Area(path2);
	    a2.intersect(a1);
	    if (!a2.isEmpty()) {
	        return true;
	    }
	    return false;
		


    }
	private boolean closeEnoughToCrash(Interactor violator) {
		int largest = width;
		if(violator.getWidth() > largest) {
			largest = width;
		}
		if(height > largest) {
			largest = height;
		}
		if(violator.getHeight() > largest) {
			largest = violator.getHeight();
		}
		
		
		if(Math.abs(violator.getxPos() - xPos) <  largest && Math.abs(violator.getyPos() - yPos) <  largest) {
			return true;
		}
		return false;
	}
}
