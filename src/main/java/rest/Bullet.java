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
		int type = violator.getInteractor().type();
		switch (type) {
		case Constants.PROJECTILE:
			break;
		default:
			System.out.println("into");
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
			System.out.println("outof");
			super.setToBeRemoved();
			break;
		}

	}

	@Override
	public void move() {
		super.move();
		System.out.println(super.getxPos());
		System.out.println(super.getyPos());

	}

}
