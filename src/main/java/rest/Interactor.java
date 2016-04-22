package rest;

public interface Interactor {
	public int[] getPositionsSend();
	public int[] getPositionsCrash();
	public void handleGettingCrashed(Mover violator);
	public void setxPos(double newX);
	public double getxPos();
	public void setyPos(double newX);
	public double getyPos();
	public Forms getForm();
	public void setForm(Forms form);
	public void setRotation(double degrees);
	

}
