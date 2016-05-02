package rest;

public enum Weapons {
	KNIFE(0, Projectiles.STAB, 1, 0), MINE(1, Projectiles.MINE, -1, 0), PISTOL(1, Projectiles.BULLET, 300, 6), SHOTGUN(1, Projectiles.BULLET, 100, 10);
	
	private final int numberConsumed;
	private final Projectiles projectileShot;
	private final int range;
	private final int ricoche;


	Weapons(int numberConsumed, Projectiles projectileShot, int range, int ricoche) {

		this.numberConsumed = numberConsumed;
		this.projectileShot = projectileShot;
		this.range = range;
		this.ricoche = ricoche;
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


}
