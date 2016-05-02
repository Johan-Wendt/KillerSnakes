package rest;

import java.util.ArrayList;
import java.util.LinkedList;

public class ProjectileController extends TypeControllerBase {
	private LinkedList<PlayerBase> shotsQueued = new LinkedList<>();

	public ProjectileController(Types type) {
		super(type);
	}

	@Override
	public void act() {
		super.act();
		shootQueuedShots();
	}

	private void shootQueuedShots() {
		if (shotsQueued.size() > 0) {
			PlayerBase player = shotsQueued.pop();
			Weapons weapon = player.getEquippedWeapon();
			super.addToActingObjects(new Bullet(weapon.projectileShot(), weapon.range(), player));
		}
	}

	public void shoot(PlayerBase player) {
		shotsQueued.add(player);

	}

}
