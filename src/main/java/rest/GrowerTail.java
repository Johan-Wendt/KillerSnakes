package rest;

import java.util.LinkedList;

public class GrowerTail extends GrowerBase implements Slave {

	public static int num = 0;	
	
	public GrowerTail(GrowerDetails grower, Interactor owner, GrowerBase creator) {
		super(grower);
		super.setOwner(owner);
		super.setxPos(creator.getxPos());
		super.setyPos(creator.getyPos());
		super.setRotation(creator.getRotation());
		super.setTimesActed(creator.getTimesActed());
		super.setTurnInstructions(creator.getTurnInstructions());
		super.setWhenToReadInstructions(creator.getWhenToReadInstructions());
		super.setSteeringDirection(creator.getSteeringDirection());
	

	}



	@Override
	public void handleGettingCrashed(Interactor violator) {

	}

	@Override
	public void handleCrashing(Interactor violator) {

	}


	@Override
	public Directions getmovingDirection() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
