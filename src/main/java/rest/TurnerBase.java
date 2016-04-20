package rest;

public abstract class TurnerBase extends MoverBase implements Turner {
	private Directions currentDirection;

	public TurnerBase(InteractorDetails interactor, Forms form) {
		super(interactor, form);
	}

	@Override
	public void turn(Directions direction) {
		// TODO Auto-generated method stub
		
	}

	public Directions getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(Directions currentDirection) {
		this.currentDirection = currentDirection;
	}
}
