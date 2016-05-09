package rest;

public enum StringMessageTypes {
	PLAYER_NAME("p");

	private final String prefix;

	StringMessageTypes(String prefix) {
		this.prefix = prefix;

	}

	public String prefix() {
		return prefix;
	}

}
