package rest;

public abstract class PlayerBase extends GrowerBase implements Player {

	public PlayerBase(Players player) {
		super(player);
		reStart();

		super.setForm(player.form());
		super.setActingSpeed(player.startActingSpeed());

		super.setMovingSpeed(player.startMovingSpeed());

	}

	public PlayerBase(PlayerDetails player, boolean head) {
		super(player, head);

	}

	@Override
	public void handleCrashing(Interactor victim) {

		int type = victim.getInteractor().type();
		switch (type) {
		case Constants.PLAYER:
			if (!(super.isInvincible())) {
				// GrowerTail tail = (GrowerTail) victim;
				deathPenalty();
				break;
			}
			break;
		case Constants.BONUS:
			Bonus bonus = (Bonus) victim;
			handleBonus(bonus.getBonus());

		}

	}

	private void handleBonus(Bonuses bonus) {
		setCurrentActingPoints(-6 * getActingSpeed());
		switch (bonus) {
		case SPPED:
			setActingSpeed((getActingSpeed() + 2));
			break;
		case GROW:
			setLength(getLength() + 5, getPlayerDetails().imunityLength());
			break;
		case PISTOL:
			break;
		case SHOTGUN:
			break;
		}

	}

	private void deathPenalty() {
		reStart();

	}

	private void reStart() {
		super.setMovingDirection(getPlayerDetails().startDirection());
		super.setxPos(getPlayerDetails().startX());
		super.setyPos(getPlayerDetails().startY());
		super.setSteeringDirection(getPlayerDetails().startDirection());
		super.setInvincible(30);
		super.emptyTurnInstructions();
	}

	@Override
	public void handleGettingCrashed(Interactor violator) {
		int type = violator.getInteractor().type();
		switch (type) {
		case Constants.PLAYER:
			break;

		}
	}

	public PlayerDetails getPlayerDetails() {
		PlayerDetails result = (PlayerDetails) super.getInteractor();
		return result;
	}

}
