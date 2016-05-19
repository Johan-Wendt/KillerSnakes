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
		PlayerBase playerBase = (PlayerBase) super.getActingObjects().get(player.ordinal());
		playerBase.changeWeapon();

	}
	
	public int appendWeaponInfoSend(int[] message, int pointer) {
		message[pointer] = Constants.WEAPON_INFO;
		pointer++;
		for (Actor actor : super.getActingObjects()) {
			PlayerBase player = (PlayerBase) actor;
			int[] tempResult = player.getWeaponInfoSend();
			int n = 0;
			while (n < tempResult.length) {
				message[pointer] = tempResult[n];
				n++;
				pointer++;
			}
		}
		message[pointer] = -1;
		pointer++;
		return pointer;

	}
	public int getWeaponSendInfoSize() {
		return 1 + numberOfcreatedPlayers * 3 + 1;
		
	}
	@Override 
	public int getSendInfoSize() {
		return 1 + getLength() * Constants.INTS_SENT_PER_OBJECT + 1 + getSoundSendInfoSize() + getWeaponSendInfoSize();
	}

}
