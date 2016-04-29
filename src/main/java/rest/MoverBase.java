package rest;

public abstract class MoverBase extends ActorBase implements Mover {
	private Directions movingDirection;
	private int movingSpeed;

	public MoverBase(InteractorDetails interactor) {
		super(interactor);
	}

	public MoverBase(InteractorDetails interactor, Forms form, int xPos, int yPos, Directions direction) {
		super(interactor, form, xPos, yPos);
		setMovingDirection(direction);
	}

	@Override
	public void act() {
		move();
		super.setTestCrashingInto(true);

	}

	@Override
	public void move() {
		super.setxPos(getNextxStep());
		super.setyPos(getNextyStep());

	}

	/**
	 * private double getNextxStep() { return super.getxPos() +
	 * movingDirection.getxMultiplier() * getHeight(); } private double
	 * getNextyStep() { return super.getyPos() +
	 * movingDirection.getyMultiplier() * getHeight(); }
	 **/
	/**
	private double getNextxStep() {
		return super.getxPos() + movingSpeed * getHeight() * Math.sin(Math.toRadians(super.getRotation()));
	}

	private double getNextyStep() {
		return super.getyPos() - movingSpeed * getHeight() * Math.cos(Math.toRadians(super.getRotation()));
	}
	**/
	
	private double getNextxStep() {
		return super.getxPos() + movingSpeed * Math.sin(Math.toRadians(super.getRotation()));
	}

	private double getNextyStep() {
		return super.getyPos() - movingSpeed * Math.cos(Math.toRadians(super.getRotation()));
	}

	public void setMovingDirection(Directions direction) {
		movingDirection = direction;
		super.setRotation(direction.getDegreesTurned());
	}

	public Directions getMovingDirection() {
		return movingDirection;
	}

	public int getMovingSpeed() {
		return movingSpeed;
	}

	public void setMovingSpeed(int movingSpeed) {
		this.movingSpeed = movingSpeed;
	}

}
