package rest;

public enum StringMessageTypes {
	PLAYER_NAME("p"), GAMEROOM_NAME("r"), GAMEROOM_MEMBERS("m");

	private final String prefix;

	StringMessageTypes(String prefix) {
		this.prefix = prefix;

	}

	public String prefix() {
		return prefix;
	}

}
