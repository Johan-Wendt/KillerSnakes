package rest;

public abstract class MoverBase extends ActorBase implements Mover {
	private Directions movingDirection;
	private int movingSpeed;

	public MoverBase() {
	}

	public MoverBase(InteractorDetails interactor) {
		super(interactor);
	}

	public MoverBase(InteractorDetails interactor, Forms form, int xPos, int yPos, Directions direction) {
		super(interactor, form, xPos, yPos);
		setMovingDirection(direction);
	}
	@Override
	public void gameRound() {
		int n = 0;
		while(n < getMovingSpeed()) {
			super.gameRound();
			n++;
		}
		
	}

	@Override
	public void act() {
		super.act();
		move();

	}

	@Override
	public void move() {
		super.setxPos(getNextxStep());
		super.setyPos(getNextyStep());

	}



	private double getNextxStep() {
		double newxPos = super.getxPos() + Math.sin(Math.toRadians(super.getRotation()));
		if(newxPos < 0) {
			newxPos += Constants.GAME_WIDTH;
		}
		else if(newxPos > Constants.GAME_WIDTH) {
			newxPos -= Constants.GAME_WIDTH;
		}
		return newxPos;
	}

	private double getNextyStep() {
		double newyPos = super.getyPos() - Math.cos(Math.toRadians(super.getRotation()));
		if(newyPos < 0) {
			newyPos += Constants.GAME_HEIGHT;
		}
		else if(newyPos > Constants.GAME_HEIGHT) {
			newyPos -= Constants.GAME_HEIGHT;
		}
		return newyPos;
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
