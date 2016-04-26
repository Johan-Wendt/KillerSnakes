package rest;

public abstract class HappeningBase extends ActorBase implements Happening {
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
		if (age >= 5) {
			super.setTestCrashingInto(false);
		}

		if (age > longevity) {
			super.setToBeRemoved();
		}
		age++;
		System.out.println(super.isTestCrashingInto());

	}

	@Override
	public void handleGettingCrashed(Interactor violator) {

		int type = violator.getInteractor().type();

		switch (type) {
		case Constants.BONUS:
			break;
		default:
			super.setToBeRemoved();
			System.out.println("Removed due to crashed into");
		}

	}

	@Override
	public void handleCrashing(Interactor violator) {
		System.out.println("Removed due to crashing into");
		super.setToBeRemoved();

	}

}
