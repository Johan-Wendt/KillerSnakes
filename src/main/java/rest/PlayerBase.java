package rest;

import java.util.ArrayList;

public abstract class PlayerBase extends GrowerFront implements Player {
	private Weapons equippedWeapon = Weapons.KNIFE;
	private ArrayList<Integer> ammo = new ArrayList<>();

	public PlayerBase(Players player) {
		super(player);
		reStart();

		super.setForm(player.form());
		super.setActingSpeed(player.startActingSpeed());

		super.setMovingSpeed(player.startMovingSpeed());

		loadUpAmmo();

		// setLength(player.startLength(), player.imunityLength());

	}

	// public PlayerBase(PlayerDetails player, boolean head) {
	// super(player, head);

	// }
	/**
	 * @Override public void act() { int n = 0; while(n <
	 *           super.getMovingSpeed()) { super.act(); n++; } }
	 **/
	@Override
	public void handleGettingCrashed(Interactor violator) {
		int type = violator.getInteractor().type();
		switch (type) {
		case Constants.PLAYER:
			break;

		}
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

	private void handleBonus(Happenings bonus) {
		setCurrentActingPoints(-6 * getActingSpeed() * getMovingSpeed());
		switch (bonus) {
		case SPPED:
			setMovingSpeed((getMovingSpeed() + 1));
			break;
		case GROW:
			setLength(getLength() + 1, getPlayerDetails().imunityLength());
			break;
		case PISTOL:
			addWeapon(Weapons.PISTOL);
			break;
		case SHOTGUN:
			addWeapon(Weapons.SHOTGUN);
			break;
		}

	}

	private void deathPenalty() {
		reStart();

	}

	private void reStart() {
		super.setMovingDirection(getPlayerDetails().startDirection());
		super.setxPosAll(getPlayerDetails().startX());
		super.setyPosAll(getPlayerDetails().startY());
		super.setSteeringDirection(getPlayerDetails().startDirection());
		super.setRotationAll(getPlayerDetails().startDirection().getDegreesTurned());
		super.setTimesActed(0);
		super.setInvincible(30);
		super.emptyTurnInstructions();
	}

	public PlayerDetails getPlayerDetails() {
		PlayerDetails result = (PlayerDetails) super.getInteractor();
		return result;
	}

	private void loadUpAmmo() {
		int n = 0;
		while (n < Weapons.values().length) {
			ammo.add(0);
			n++;
		}
		addWeapon(Weapons.KNIFE);

	}

	private void addWeapon(Weapons weapon) {
		int replaceIndex = weapon.ordinal();
		ammo.set(replaceIndex, ammo.get(replaceIndex) + 1);

	}

	public void changeWeapon() {
		int weaponSize = Weapons.values().length;
		int newWeapon = equippedWeapon.ordinal() + 1;
		if (newWeapon >= weaponSize) {
			newWeapon -= weaponSize;
		}
		equippedWeapon = Weapons.values()[newWeapon];
		if (ammo.get(newWeapon) <= 0) {
			changeWeapon();
		}
	}

	public Weapons getEquippedWeapon() {
		return equippedWeapon;
	}

	public void setEquippedWeapon(Weapons equippedWeapon) {
		this.equippedWeapon = equippedWeapon;
	}

	public ArrayList<Integer> getAmmo() {
		return ammo;
	}

	public void setAmmo(ArrayList<Integer> ammo) {
		this.ammo = ammo;
	}

	public boolean shoot() {
		int weapon = equippedWeapon.ordinal();
		int ammonition = ammo.get(weapon);
		if (ammonition > 0) {
			ammo.set(weapon, ammonition - equippedWeapon.numberConsumed());
			
			return true;
		}
		else {
			changeWeapon();
		return false;
		}

	}
	public int [] getWeaponInfoSend() {
		int weapon = equippedWeapon.subType();
		
	
		int [] result = {getPlayerDetails().subType(), weapon, ammo.get(weapon - 1)}; 
		
		return result;
	}

}
