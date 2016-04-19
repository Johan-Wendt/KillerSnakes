package rest;

public abstract class MoverBase extends ActorBase implements Mover{
	private Directions steeringDirection;
	private Directions movingDirection;

	public MoverBase(InteractorDetails interactor) {
		super(interactor);
	}
	
	@Override
	public void act() {
		move();
		
	}
	
	@Override
	public void move() {
		
		
	}
	private int getNextxStep() {
		return super.getxPos() + steeringDirection.getxMultiplier() * getHeight();
	}
	private int getNextyStep() {
		return super.getyPos() + steeringDirection.getyMultiplier() * getHeight();
	}
	

}
