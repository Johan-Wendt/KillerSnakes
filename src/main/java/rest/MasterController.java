package rest;

public interface MasterController extends Controller{
	public void takeInput(byte[] input);
	public void sendOutPut(int[] message);

}
