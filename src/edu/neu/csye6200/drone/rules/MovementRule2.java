package edu.neu.csye6200.drone.rules;

public class MovementRule2 extends MovementRule {

	@Override
	public int[][] getDirections() {
		return ALL_DIRECTIONS;
	}

	@Override
	public int getMaxWaitTime() {
		return 0;
	}
}
