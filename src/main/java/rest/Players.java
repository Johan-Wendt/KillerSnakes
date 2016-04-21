package rest;

public enum Players implements PlayerDetails {
	PLAYER1(1, Directions.UP, 100, 100), PLAYER2(2, Directions.DOWN, 140, 140), PLAYER3(3, Directions.RIGHT,180, 180), PLAYER4(4, Directions.LEFT, 220, 220);

	private final int type;
	private final int width;
	private final int heigth;
	private final int subType;
	private final Directions startDirection;
	private final int startSpeed;
	private final int startLength;
	private final int startX;
	private final int startY;

	Players(int subType, Directions startDirection, int startX,int startY) {
		type = 1;
		startSpeed = 10;
		width = 12;
		heigth = 3;
		startLength = 8;
		this.subType = subType;
		this.startDirection = startDirection;
		this.startX = startX;
		this.startY = startY;

	}

	public int type() {
		return type;
	}

	public int width() {
		return width;
	}

	public int height() {
		return heigth;
	}

	@Override
	public int subType() {
		return subType;
	}

	public Directions startDirection() {
		return startDirection;
	}

	public int startSpeed() {
		return startSpeed;
	}


	@Override
	public int length() {
		return startLength;
	}

	@Override
	public Forms form() {
		
		return Forms.HALF_CIRCKLE;
	}
	@Override
	public Forms tailForm() {
		return Forms.RECTANGLE;
	}
	
	public int startX() {
		return startX;
	}
	public int startY() {
		return startY;
	}
	public static Players getPlayer(byte clientRequest) {
		switch(clientRequest) {
		case 1: return PLAYER1;
		case 2: return PLAYER2;
		case 3: return PLAYER3;
		case 4: return PLAYER4;
		default: return PLAYER1;
		}
	}

}
