package rest;

public interface Interactor {
	public int[] getSendPositions();
	public int[] getCrashPositions();
	public void handleGettingCrashed(Mover violator);
	

}
