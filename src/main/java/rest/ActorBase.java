package rest;

public abstract class ActorBase extends InteractorBase implements Actor {
	private final static int actingLimit = 60;
	private int currentActingPoints = 0;
	private int actingSpeed = 10;
	private int invincibleCounter;
	public int timesActed;
	
	public ActorBase() {
	}

	public ActorBase(InteractorDetails interactor) {
		super(interactor);
	}

	public ActorBase(InteractorDetails interactor, Forms form, int xPos, int yPos) {
		super(interactor, form, xPos, yPos);
	}

	public void gameRound() {
		currentActingPoints += actingSpeed;
		while (isTimeToAct()) {
		//if (isTimeToAct()) {
			act();
			
			countDownInvincibility();
		}
	}
	public void act() {
		timesActed++;
		//System.out.println("timesActed = " + timesActed);
	}

	public boolean isTimeToAct() {
		
		if (currentActingPoints >= actingLimit) {
			currentActingPoints -= actingLimit;
			return true;
		}
		return false;
	}

	public void setActingSpeed(int newSpeed) {
		actingSpeed = newSpeed;
	}

	public int getActingSpeed() {
		return actingSpeed;
	}

	public int getLength() {
		return 1;
	}

	public void setInvincible(int rounds) {
		super.setInvincible(true);
		invincibleCounter = rounds;
	}

	private void countDownInvincibility() {
		if (invincibleCounter > 0) {
			invincibleCounter--;
			if (invincibleCounter == 0) {
				super.setInvincible(false);
			}
		}
	}
	public int getCurrentActingPoints() {
		return currentActingPoints;
	}
	public void setCurrentActingPoints(int points) {
		currentActingPoints = points;
	}
	public int getTimesActed() {
		return timesActed;
	}
	public void setTimesActed(int times) {
		timesActed = times;
	}
	public void delay(int timesTillNextAct) {
		currentActingPoints = -(actingSpeed * timesTillNextAct);
	}

	public static int getActinglimit() {
		return actingLimit;
	}

}
