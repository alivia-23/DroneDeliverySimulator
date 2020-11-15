package edu.neu.csye6200.drone.rules;

public class MovementRule1 extends MovementRule{
	
	public MovementRule1() {
		
	}
	
	public int[][] getDirections() {
		return HV_DIRECTIONS;
	}

	@Override
	public int getMaxWaitTime() {		
		return 2;
	}
	
	

}
