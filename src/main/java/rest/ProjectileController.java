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
			switch (weapon) {
			case KNIFE:

					super.addToActingObjects(
							new Bullet(weapon.projectileShot(), weapon.range(), player, player.getNextxStep(player.getHeight(), player.getRotation() - 90), player.getNextyStep(player.getLength(), player.getRotation() - 90)));

				break;
				
			case PISTOL:
				super.addToActingObjects(new Bullet(weapon.projectileShot(), weapon.range(), player));
				break;
			case SHOTGUN:
				int n = 0;
				int rotationOffset2 = -30;
				while (n < 3) {
					super.addToActingObjects(
							new Bullet(weapon.projectileShot(), weapon.range(), player, rotationOffset2));
					n++;
					rotationOffset2 += 30;
				}
				break;
			}
		}
	}

	public void shoot(PlayerBase player) {
		if (player.shoot()) {
			shotsQueued.add(player);
		}

	}


}
