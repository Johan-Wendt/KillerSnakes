package rest;

public abstract class MoverBase extends ActorBase implements Mover{
	private Directions steeringDirection;
	private Directions movingDirection;

	public MoverBase(InteractorDetails interactor, Forms form) {
		super(interactor, form);
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
		return super.getxPos() + steeringDirection.getxMultiplier() * getHeight();
	}
	private int getNextyStep() {
		return super.getyPos() + steeringDirection.getyMultiplier() * getHeight();
	}
	
	public void setMovingDirection(Directions direction) {
		movingDirection = direction;
	}
	Sätta constructors för interactor med xpos, ypos. En linje ska vara utan (för player som har det i details)
	Sen ska mover ha directions i consturtor. Direction behöver påverka rotation!

}
