package rest;

public class PlayerController extends TypeControllerBase {
	private int numberOfcreatedPlayers = 0;
	public static final int MAX_NUMBER_OF_SNAKES = 4;

	public PlayerController(Types type) {
		super(type);
	}

	public void createPlayer() {
		if (numberOfcreatedPlayers < MAX_NUMBER_OF_SNAKES) {
			super.addToActingObjects(new PlayerHuman(Players.values()[numberOfcreatedPlayers]));
			numberOfcreatedPlayers++;
		}
	}

	public void createPlayerAI() {
		if (numberOfcreatedPlayers < MAX_NUMBER_OF_SNAKES) {
			super.addToActingObjects(new PlayerHuman(Players.values()[numberOfcreatedPlayers]));
			numberOfcreatedPlayers++;
		}
	}

	public void turnPlayer(Players player, Directions newDirection) {
		PlayerBase playerBase = (PlayerBase) super.getActingObjects().get(player.ordinal());
		playerBase.steer(newDirection);
	}

	 public void shoot(ProjectileController projectileController, Players player) {
	 PlayerBase playerBase = (PlayerBase) super.getActingObjects().get(player.ordinal());
	 projectileController.shoot(playerBase);
	 }

	public void changeWeapon(Players player) {
		// PlayerBase playerBase = (PlayerBase)
		// super.getActingObjects().get(player.ordinal());
		// player.changeWeapon();

	}

}
