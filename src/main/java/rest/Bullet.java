package rest;

public class Bullet extends ProjectileBase {

	public Bullet(ProjectileDetails projectile, int longevity, PlayerBase owner) {
		super(projectile, longevity, owner);
		super.setxPos(owner.getxPos());
		super.setyPos(owner.getyPos());
		super.setRotation(owner.getRotation());
		super.setMovingSpeed(owner.getMovingSpeed() + owner.getEquippedWeapon().speed());
		super.setActingSpeed(owner.getActingSpeed());
	}
	public Bullet(ProjectileDetails projectile, int longevity, PlayerBase owner, double rotationOffset) {
		super(projectile, longevity, owner);
		super.setxPos(owner.getxPos());
		super.setyPos(owner.getyPos());
		super.setMovingSpeed(owner.getMovingSpeed() + owner.getEquippedWeapon().speed());
		super.setActingSpeed(owner.getActingSpeed());
		super.setRotation(owner.getRotation() + rotationOffset);
	}
	public Bullet(ProjectileDetails projectile, int longevity, PlayerBase owner, double xPos, double yPos) {
		super(projectile, longevity, owner);
		super.setxPos(xPos);
		super.setyPos(yPos);
		super.setRotation(owner.getRotation());
		super.setMovingSpeed(owner.getMovingSpeed() + owner.getEquippedWeapon().speed());
		super.setActingSpeed(owner.getActingSpeed());
	}

	@Override
	public void handleGettingCrashed(Interactor violator) {
		int type = violator.getInteractor().type();
		switch (type) {
		case Constants.PROJECTILE:
			break;
		default:
			super.setToBeRemoved();
			break;
		}

	}

	@Override
	public void handleCrashing(Interactor violator) {
		int type = violator.getInteractor().type();
		switch (type) {
		case Constants.PROJECTILE:
			break;
		default:
			super.setToBeRemoved();
			break;
		}

	}

	@Override
	public void move() {
		super.move();

	}
	@Override
	public int getLength() {
		return super.getLength();
	}

}
