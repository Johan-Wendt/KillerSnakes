package rest;

public enum Weapons {
	KNIFE(1, 0, Projectiles.STAB, 40, 0, 1), MINE(2, 1, Projectiles.MINE, -1, 0, -1), PISTOL(3, 1, Projectiles.BULLET, 300, 6, 2), SHOTGUN(4, 1, Projectiles.BULLET, 100, 10, 1);
	
	private final int numberConsumed;
	private final Projectiles projectileShot;
	private final int range;
	private final int ricoche;
	private final int speed;
	private final int subType;
	


	Weapons(int subType, int numberConsumed, Projectiles projectileShot, int range, int ricoche, int speed) {

		this.numberConsumed = numberConsumed;
		this.projectileShot = projectileShot;
		this.range = range;
		this.ricoche = ricoche;
		this.speed = speed;
		this.subType = subType;
	}

	public int numberConsumed() {
		return numberConsumed;
	}
	public Projectiles projectileShot() {
		return projectileShot;
	}
	public int range() {
		return range;
	}
	public int ricoche() {
		return ricoche;
	}
	public int speed() {
		return speed;
	}
	public int subType() {
		return subType;
	}


}
