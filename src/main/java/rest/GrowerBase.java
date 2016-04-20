package rest;

public abstract class GrowerBase extends TurnerBase implements Grower {

	public GrowerBase(InteractorDetails interactor) {
		super(interactor);
		// TODO Auto-generated constructor stub
	}
	public GrowerBase(InteractorDetails interactor, Forms form,int xPos,int yPos, Directions direction) {
		super(interactor, form, xPos, yPos, direction);
	}

	public void makeLonger() {

	}

	public void makeShorter() {

	}

}
