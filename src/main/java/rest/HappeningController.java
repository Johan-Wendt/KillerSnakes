package rest;

import java.util.ArrayList;

public class HappeningController extends TypeControllerBase{
	private ArrayList<Bonuses> bonuses = new ArrayList<Bonuses>();
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

	public Bonuses getRandomHappening(double limit) {
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
		bonuses.add(Bonuses.GROW);
		chanceMapBonus.add(Bonuses.GROW.chance());
		happeningChance += Bonuses.GROW.chance();

		bonuses.add(Bonuses.SPPED);
		chanceMapBonus.add(Bonuses.SPPED.chance());
		happeningChance += Bonuses.SPPED.chance();

		bonuses.add(Bonuses.PISTOL);
		chanceMapBonus.add(Bonuses.PISTOL.chance());
		happeningChance += Bonuses.PISTOL.chance();

		bonuses.add(Bonuses.SHOTGUN);
		chanceMapBonus.add(Bonuses.SHOTGUN.chance());
		happeningChance += Bonuses.SHOTGUN.chance();

	}

	private void addHappening(Bonuses bonus) {
		int xPos = (int) (Math.random() * (Constants.GAME_WIDTH - 2) + 1);
		int yPos = (int) (Math.random() * (Constants.GAME_HEIGHT - 2) + 1);
		super.addToActingObjects(new Bonus(bonus, 100, xPos, yPos));
	}


}
