package rest;

import java.util.Random;

public class PlayerAI extends PlayerBase{
	private static final double moveLimit = 0.02;
	private Random random = new Random();

	public PlayerAI(Players player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Directions getmovingDirection() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void act() {
		super.act();
		double moveChance = Math.random();
		if(moveChance < moveLimit) {
			moveRandomDirection();
		}
		
	}
	private void moveRandomDirection() {
		int direction = 1 + random.nextInt(4);
		super.steer(Directions.getClientDirection(direction));
	}

}
