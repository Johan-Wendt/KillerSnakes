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
		System.out.println("int0");
		super.setToBeRemoved();
	}

	@Override
	public void handleCrashing(Interactor violator) {
		System.out.println("out of");
		super.setToBeRemoved();

	}
	
	@Override
	public void move() {
		super.move();
		System.out.println(super.getxPos());
		System.out.println(super.getyPos());

	}

}
