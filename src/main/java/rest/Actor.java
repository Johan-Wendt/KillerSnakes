package rest;

public interface Actor extends Interactor {
	public void act();
	public void setToBeRemoved();
	public boolean isToBeRemoved();

}
