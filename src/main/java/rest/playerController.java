package rest;

public class playerController extends TypeControllerBase{
	private int numberOfcreatedPlayers = 0;
	public static final int MAX_NUMBER_OF_SNAKES = 4;
	
	public playerController() {
		super();
	}
	
	public void createPlayer() {
		if (numberOfcreatedPlayers < MAX_NUMBER_OF_SNAKES) {
			super.addToActingObjects(new PlayerHuman(Players.values()[numberOfcreatedPlayers]));
			numberOfcreatedPlayers++;
		}
	}

	public void createPlayerAI() {
		if (numberOfcreatedPlayers < MAX_NUMBER_OF_SNAKES) {
			//super.addToActingObjects(new PlayerAI(Players.values()[numberOfcreatedPlayers]));
			//numberOfcreatedPlayers++;
		}
	}

	public void turnPlayer(Players player, Directions direction) {
		PlayerBase playerBase = (PlayerBase) super.getActingObjects().get(player.ordinal());
		player.turn(direction);
	}

	//public void shoot(WeaponController weaponController, byte player) {
		//PlayerBase playerBase = (PlayerBase) super.getActingObjects().get(player.ordinal());
		//offender.shoot(weaponController);
	//}

	public void changeWeapon(Players player) {
		//PlayerBase playerBase = (PlayerBase) super.getActingObjects().get(player.ordinal());
		//player.changeWeapon();

	}
	
	

}
