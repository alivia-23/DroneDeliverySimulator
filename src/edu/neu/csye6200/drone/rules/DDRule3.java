package edu.neu.csye6200.drone.rules;

/**
 * 
 * Rule 3 - a specification of DDRule
 */
public class DDRule3 implements DDRule {
	
	/**
	 * @see interface
	 */
	@Override
	public int[][] getDirections() {
		return ALL_DIRECTIONS;
	}

	/**
	 * @see interface
	 */
	@Override
	public int getMaxHoverTime() {
		System.out.println("Hovering for path clearance...");
		return 3;
	}

	/**
	 * @see interface
	 */
	@Override
	public int getTimeout() {
		return 12;
	}

}
