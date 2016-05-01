package rest;

public class Bonus extends HappeningBase {

	public Bonus(HappeningDetails happening, int longevity, int xPos, int yPos) {
		super(happening, longevity, xPos, yPos);
	}
	public Happenings getBonus() {
		Happenings bonus = (Happenings) getInteractor();
		return bonus;
	}
	
}
