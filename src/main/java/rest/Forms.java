package rest;

public enum Forms {

	RECTANGLE(1),
	HALF_CIRCKLE(2);

	private final int sendValue;


	Forms(int sendValue) {
		this.sendValue = sendValue;

	}

	public int sendValue() {
		return sendValue;
	}

}