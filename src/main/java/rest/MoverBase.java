package rest;

public abstract class MoverBase extends ActorBase implements Mover{
	private Directions steeringDirection;
	private Directions movingDirection;

	public MoverBase(InteractorDetails interactor) {
		super(interactor);
	}
	public MoverBase(InteractorDetails interactor, Forms form,int xPos,int yPos, Directions direction) {
		super(interactor, form, xPos, yPos);
		setMovingDirection(direction);
	}
	
	@Override
	public void act() {
		move();
		
	}
	
	@Override
	public void move() {
		super.setxPos(getNextxStep());
		super.setyPos(getNextyStep());
		
		
	}
	private int getNextxStep() {
		return super.getxPos() + movingDirection.getxMultiplier() * getHeight();
	}
	private int getNextyStep() {
		return super.getyPos() + movingDirection.getyMultiplier() * getHeight();
	}
	
	public void setMovingDirection(Directions direction) {
		movingDirection = direction;
		super.setRotation(direction.getDegreesTurned());
	}


}
