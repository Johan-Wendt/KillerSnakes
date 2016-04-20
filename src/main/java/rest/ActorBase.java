package rest;

public abstract class ActorBase extends InteractorBase implements Actor {
	private int actingLimit = 60;
	private int currentActingPoints = 0;
	private int actingSpeed = 10;

	public ActorBase(InteractorDetails interactor, Forms form) {
		super(interactor, form);
		// TODO Auto-generated constructor stub
	}
	public void gameRound() {
		if(isTimeToAct()) {
			act();
		}
	}
	public boolean isTimeToAct() {
		currentActingPoints += actingSpeed;
		if(currentActingPoints >= actingLimit) {
			currentActingPoints -= actingLimit;
			return true;
		}
		return false;
	}
	public void setActingSpeed(int newSpeed) {
		actingSpeed = newSpeed;
	}
	public int getActingSpeed(){
		return actingSpeed;
	}
	

}
