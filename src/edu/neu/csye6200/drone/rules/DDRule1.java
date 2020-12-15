package edu.neu.csye6200.drone.rules;

/**
 * 
 * Rule 1 - a specification of DDRule
 */
public class DDRule1 implements DDRule{
	
	/**
	 * Returns the type of movement path
	 */
	@Override
	public int[][] getDirections() {
		return ALL_DIRECTIONS;
	}
    
	/**
	 * Returns the hovering time of the drone if another drone comes in the path of 
	 * current drone in-transit
	 */
	@Override
	public int getMaxHoverTime() {
		System.out.println("Hovering for path clearance...");
		return 2;
	}
    
	/**
	 * Returns the maximum wait time of the drone for clearance of its path
	 */
	@Override
	public int getTimeout() {
		return 15;
	}	
	
}
