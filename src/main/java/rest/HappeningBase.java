package rest;

public abstract class HappeningBase extends ActorBase implements Happening {
	private int longevity;

	public HappeningBase(HappeningDetails happening, int longevity, int xPos, int yPos) {
		super(happening);
		this.longevity = longevity;
		this.setxPos(xPos);
		this.setyPos(yPos);
	}

	@Override
	public void act() {
		super.act();
		if(super.getTimesActed() > longevity) {
			super.setToBeRemoved();
		}
	}

	@Override
	public void handleGettingCrashed(Interactor violator) {

		int type = violator.getInteractor().type();

		switch (type) {
		case Constants.BONUS:
			break;
		default:
			super.setToBeRemoved();
		}

	}

	@Override
	public void handleCrashing(Interactor violator) {
		super.setToBeRemoved();

	}

}
