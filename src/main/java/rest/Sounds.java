package rest;

public enum Sounds {
	CRASH(1), BONUS_TAKEN(2);

	private final int soundNumber;


	Sounds(int soundNumber) {
		this.soundNumber = soundNumber;
	}
	
	public int soundNumber() {
		return soundNumber;
	}
}