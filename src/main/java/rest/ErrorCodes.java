package rest;

public enum ErrorCodes {
	NAME_NOT_UNIQUE("a");

	private final String errorCode;

	ErrorCodes(String errorCode) {
		this.errorCode = errorCode;

	}

	public String errorCode() {
		return errorCode;
	}

}
