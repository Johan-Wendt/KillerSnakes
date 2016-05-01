package rest;

public class Bullet extends ProjectileBase {

	public Bullet(ProjectileDetails projectile, int longevity, PlayerBase owner) {
		super(projectile, longevity, owner);
		super.setxPos(owner.getxPos());
		super.setyPos(owner.getyPos());
		super.setRotation(owner.getRotation());
		super.setMovingSpeed(owner.getMovingSpeed() + 1);
		super.setActingSpeed(owner.getActingSpeed() + 1);
		
	}

	@Override
	public void handleGettingCrashed(Interactor violator) {
		super.setToBeRemoved();
	}

	@Override
	public void handleCrashing(Interactor violator) {
		super.setToBeRemoved();

	}

}
