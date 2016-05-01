package rest;

public class ProjectileController extends TypeControllerBase{

	public ProjectileController(Types type) {
		super(type);
	}
	
	@Override
	public void act() {
		super.act();
	}
	
	public void shoot(PlayerBase player) {
		Weapons weapon = player.getEquippedWeapon();
		Bullet bullet = new Bullet(weapon.projectileShot(), weapon.range(), player);
		super.addToActingObjects(bullet);

	}

}
