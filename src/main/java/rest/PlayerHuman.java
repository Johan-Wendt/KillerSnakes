package rest;

public class PlayerHuman extends PlayerBase {
	
	public PlayerHuman(Players player, Forms form) {
		super(player, form);
	super.setxPos(300);
	super.setyPos(400);
	}





	@Override
	public int[] getPositionsCrash() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleGettingCrashed(Mover violator) {
		// TODO Auto-generated method stub
		
	}










	@Override
	public Directions getmovingDirection() {
		// TODO Auto-generated method stub
		return null;
	}

}
