package rest;

public enum Players implements PlayerDetails {
	PLAYER1(1, Directions.UP), PLAYER2(2, Directions.DOWN), PLAYER3(3, Directions.RIGHT), PLAYER4(4, Directions.LEFT);

	private final int type;
	private final int width;
	private final int heigth;
	private final int subType;
	private final Directions startDirection;
	private final int startSpeed;
	private final int startLength;

	Players(int subType, Directions startDirection) {
		type = 1;
		startSpeed = 10;
		width = 12;
		heigth = 3;
		startLength = 8;
		this.subType = subType;
		this.startDirection = startDirection;

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

}
