package rest;

public abstract class PlayerBase extends TurnerBase implements Player{

	public PlayerBase(Players player) {
		super(player);
	}
	
	@Override
	public void handleCrashing(Interactor victim) {
		// TODO Auto-generated method stub
		
	}

}
