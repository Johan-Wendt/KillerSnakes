package rest;

public interface Actor extends Interactor {
	public void act();
	public void setToBeRemoved();
	public boolean isToBeRemoved();
	public boolean isTimeToAct();
	public void setActingSpeed(int newSpeed);
	public int getActingSpeed();
	public void gameRound();
	public int getLength();
	public void setInvincible(int rounds);

}
