package rest;

public abstract class ProjectileBase extends MoverBase implements Projectile, Slave{
	private int longevity;
	private Interactor owner;
	
	public ProjectileBase(ProjectileDetails projectile, int longevity, PlayerBase owner) {
		super(projectile);
		this.longevity = longevity;
		this.owner = owner;
		super.setImuneToCrash(owner);
		
		super.setTestGettingCrashed(true);
		super.setTestCrashingInto(true);
		
		owner.delay(owner.getEquippedWeapon().ricoche() * owner.getMovingSpeed());
	}
	public void setOwner(Interactor owner) {
		this.owner = owner;
	}
	public Interactor getOwner() {
		return owner;
	}
	@Override
	public void act() {
		super.act();
		if(longevity > 0 && super.getTimesActed() > longevity) {
			super.setToBeRemoved();
		}
		if(super.getTimesActed() > 2 * owner.getHeight() * getMovingSpeed()) {
			super.setImuneToCrash(null);
		}
	}



}
