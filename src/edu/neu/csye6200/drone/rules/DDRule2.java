package edu.neu.csye6200.drone.rules;

/**
 * 
 * Rule 2 - a specification of DDRule
 */
public class DDRule2 implements DDRule {
    
	/**
	 * @see interface
	 */
	@Override
	public int[][] getDirections() {
		return HV_DIRECTIONS;
	}

	/**
	 * @see interface
	 */
	@Override
	public int getMaxHoverTime() {
		System.out.println("Hovering for path clearance...");
		return 1;
	}

	/**
	 * @see interface
	 */
	@Override
	public int getTimeout() {
		return 20;
	}
}
