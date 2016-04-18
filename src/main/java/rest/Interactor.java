package rest;

public interface Interactor {
	public int[] getPositionsSend();
	public int[] getPositionsCrash();
	public void handleGettingCrashed(Mover violator);
	

}
