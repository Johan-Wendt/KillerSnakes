package rest;

public enum Weapons {
	KNIFE(0, Projectiles.STAB, 1, 0, 1), MINE(1, Projectiles.MINE, -1, 0, -1), PISTOL(1, Projectiles.BULLET, 300, 6, 2), SHOTGUN(1, Projectiles.BULLET, 100, 10, 1);
	
	private final int numberConsumed;
	private final Projectiles projectileShot;
	private final int range;
	private final int ricoche;
	private final int speed;
	


	Weapons(int numberConsumed, Projectiles projectileShot, int range, int ricoche, int speed) {

		this.numberConsumed = numberConsumed;
		this.projectileShot = projectileShot;
		this.range = range;
		this.ricoche = ricoche;
		this.speed = speed;
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


}
