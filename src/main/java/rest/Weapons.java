package rest;

public enum Weapons {
	KNIFE(0, Projectiles.STAB, 1), MINE(1, Projectiles.MINE, -1), PISTOL(1, Projectiles.BULLET, 100), SHOTGUN(1, Projectiles.BULLET, 50);
	
	private final int numberConsumed;
	private final Projectiles projectileShot;
	private final int range;


	Weapons(int numberConsumed, Projectiles projectileShot, int range) {

		this.numberConsumed = numberConsumed;
		this.projectileShot = projectileShot;
		this.range = range;
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


}
