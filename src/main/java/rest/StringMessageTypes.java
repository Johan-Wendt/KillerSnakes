package rest;

public enum StringMessageTypes {
	ALL_PLAYER_NAMES("p"), GAMEROOM_NAME("r"), GAMEROOM_MEMBERS("m"), ERROR_CODE("e"), PLAYER_NAME("n"), CURRENT_GAMEROOM_NAME("c");

	private final String prefix;

	StringMessageTypes(String prefix) {
		this.prefix = prefix;

	}

	public String prefix() {
		return prefix;
	}

}
