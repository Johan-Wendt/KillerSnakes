package rest;

public abstract class ActorBase extends InteractorBase implements Actor {
	private int actingLimit = 60;
	private int currentActingPoints = 0;
	private int actingSpeed = 10;
	private int invincibleCounter;

	public ActorBase(InteractorDetails interactor) {
		super(interactor);
		// TODO Auto-generated constructor stub
	}

	public ActorBase(InteractorDetails interactor, Forms form, int xPos, int yPos) {
		super(interactor, form, xPos, yPos);
	}

	public void gameRound() {
		if (isTimeToAct()) {
			act();
			countDownInvincibility();
		}
	}

	public boolean isTimeToAct() {
		currentActingPoints += actingSpeed;
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

}
