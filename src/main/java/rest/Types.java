package rest;

public enum Types {

	PLAYER(1),
	HAPPENING(2),
	PROJECTILE(3);
	

	private final int type;

	Types(int type) {
		this.type = type;

	}

	public int type() {
		return type;
	}

}
