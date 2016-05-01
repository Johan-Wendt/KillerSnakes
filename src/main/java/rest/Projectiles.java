package rest;

public enum Projectiles implements ProjectileDetails{ 
	STAB(1), BULLET(2), MINE(3);

	private final int type;
	private final int width;
	private final int heigth;
	private final int subType;
	private final String borders;
	

	Projectiles(int subType) {
		width = 10;
		heigth = 10;
		borders = "M0,0 L0,10 10,10 10,0 Z";
		type = Constants.PROJECTILE;
		this.subType = subType;
	}

	public int type() {
		return type;
	}

	public int width() {
		return width;
	}

	public int height() {
		return heigth;
	}

	@Override
	public int subType() {
		return subType;
	}


	@Override
	public Forms form() {

		return Forms.HALF_CIRCKLE;
	}


	@Override
	public String borders() {
		return borders;
	}

}
