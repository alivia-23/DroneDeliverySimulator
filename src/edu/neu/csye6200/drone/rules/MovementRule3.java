package edu.neu.csye6200.drone.rules;

public class MovementRule3 extends MovementRule {

	public MovementRule3() {
		
	}

	@Override
	public int[][] getDirections() {
		return ALL_DIRECTIONS;
	}

	@Override
	public int getMaxWaitTime() {
		return 1;
	}

}
