package rest;

public abstract class GrowerFront extends GrowerBase implements Master{
	
	
	public GrowerFront(GrowerDetails grower) {
		super(grower);
		super.setOwner(this);
		setLength(grower.startLength(), grower.imunityLength());
		setTestCrashingInto(true);

	}
}
