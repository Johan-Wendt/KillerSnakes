package rest;

public abstract class PlayerBase extends GrowerBase implements Player{

	public PlayerBase(Players player) {
		super(player);
		super.setMovingDirection(player.startDirection());
		super.setxPos(player.startX());
		super.setyPos(player.startY());
		super.setForm(player.form());
		super.setActingSpeed(player.startSpeed());
		
	}
	
	@Override
	public void handleCrashing(Interactor victim) {
		// TODO Auto-generated method stub
		
	}

}
