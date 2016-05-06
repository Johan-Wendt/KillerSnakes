package rest;

public interface MasterController extends Controller {
	public void gameRound();

	public void handleInput(int[] input);

	public void sendOutPut();
	
	public int[] getAllPositionsSend();

}
