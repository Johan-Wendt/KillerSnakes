package rest;

public abstract class PlayerBase extends GrowerBase implements Player{

	public PlayerBase(Players player) {
		super(player);
		super.setMovingDirection(player.startDirection());
		super.setxPos(player.startX());
		super.setyPos(player.startY());
		super.setForm(player.form());
		super.setActingSpeed(player.startActingSpeed());
		super.setSteeringDirection(player.startDirection());
		super.setMovingSpeed(player.startMovingSpeed());
		
	}
	public PlayerBase(GrowerDetails player, boolean head) {
		super(player, head);

	}
	
	@Override
	public void handleCrashing(Interactor victim) {
		// TODO Auto-generated method stub
		
	}

}
