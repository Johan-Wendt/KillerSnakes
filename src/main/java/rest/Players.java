package rest;

public enum Players implements PlayerDetails {
	PLAYER1(4, 2, 1, Directions.UP), PLAYER2(4, 2, 1, Directions.DOWN), PLAYER3(4, 2, 1, Directions.RIGHT), PLAYER4(4, 2, 1, Directions.LEFT);

	private final int type;
	private final int width;
	private final int heigth;
	private final int subType;
	private final Directions startDirection;
	private final int startSpeed;

	Players(int width, int heigth, int subType, Directions startDirection) {
		type = 1;
		startSpeed = 10;
		this.width = width;
		this.heigth = heigth;
		this.subType = subType;
		this.startDirection = startDirection;

	}

	public int type() {
		return type;
	}

	public int width() {
		return width;
	}

	public int heigth() {
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

}
