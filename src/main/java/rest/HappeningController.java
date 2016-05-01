package rest;

import java.util.ArrayList;

public class HappeningController extends TypeControllerBase{
	private ArrayList<Happenings> bonuses = new ArrayList<Happenings>();
	private ArrayList<Double> chanceMapBonus = new ArrayList<Double>();
	private double happeningChance;

	public HappeningController(Types type) {
		super(type);
		loadBonusChances();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act() {
		super.act();
		addRandomBonus();
	}

	public void addRandomBonus() {
		double limit = Math.random();
		if (limit < happeningChance) {
			addHappening(getRandomHappening(limit));

		}
	}

	public Happenings getRandomHappening(double limit) {
		int n = 0;
		double soFar = 0;
		while (n < bonuses.size()) {
			soFar += chanceMapBonus.get(n);

			if (limit < soFar) {
				return bonuses.get(n);
			}

			n++;

		}
		return null;
	}

	private void loadBonusChances() {
		bonuses.add(Happenings.GROW);
		chanceMapBonus.add(Happenings.GROW.chance());
		happeningChance += Happenings.GROW.chance();

		bonuses.add(Happenings.SPPED);
		chanceMapBonus.add(Happenings.SPPED.chance());
		happeningChance += Happenings.SPPED.chance();

		bonuses.add(Happenings.PISTOL);
		chanceMapBonus.add(Happenings.PISTOL.chance());
		happeningChance += Happenings.PISTOL.chance();

		bonuses.add(Happenings.SHOTGUN);
		chanceMapBonus.add(Happenings.SHOTGUN.chance());
		happeningChance += Happenings.SHOTGUN.chance();

	}

	private void addHappening(Happenings bonus) {
		int xPos = (int) (Math.random() * (Constants.GAME_WIDTH - 2) + 1);
		int yPos = (int) (Math.random() * (Constants.GAME_HEIGHT - 2) + 1);
		super.addToActingObjects(new Bonus(bonus, 100, xPos, yPos));
	}


}
