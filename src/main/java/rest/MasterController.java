package rest;

public interface MasterController extends Controller{
	public void gameRound();
	public void takeInput(byte[] input);
	public void sendOutPut();

}
