package rest;

public abstract class HappeningBase extends ActorBase implements Happening{
	private int age;
	private int longevity;

	public HappeningBase(HappeningDetails happening, int longevity, int xPos, int yPos) {
		super(happening);
		this.longevity = longevity;
		this.setxPos(xPos);
		this.setyPos(yPos);
	}

	@Override
	public void act() {
		if(age >= 1) {
			super.setTestCrashingInto(false);
		}
		
		if(age > longevity) {
			super.setToBeRemoved();
		}
		age++;
		
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
