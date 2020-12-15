package edu.neu.csye6200.drone.rules;

public interface DDRule {
	
	/**
	 * Horizontal and vertical directions
	 */
	public static final int[][] HV_DIRECTIONS = new int[][] {
		{1,0},
		{-1,0},
		{0,1},
		{0,-1},
	};
	
	/**
	 * Diagonal directions
	 */
	public static final int[][] DIAG_DIRECTIONS = new int[][] {
		{-1, 1},
		{1,-1},
		{-1,-1},
		{1, 1}
	};
	
	/**
	 * All directions i.e. horizontal, vertical and diagonal
	 */
	public static final int[][] ALL_DIRECTIONS = new int[][] {
		{1,0},
		{-1,0},
		{0,1},
		{0,-1},
		{-1, 1},
		{1,-1},
		{-1,-1},
		{1, 1}
	};

	/**
	 * 
	 * Specify the directions in which the drone can move
	 */
	public abstract int[][] getDirections();
	
	/**
	 * 
	 * Specify the maximum hovering time on a specified cell.
	 * Minimum value should be 1
	 */
    public abstract int getMaxHoverTime();
    
    
	/**
	 * 
	 * Specifies the timeout period after which the simulation will stop
	 * even if all the packages are not delivered
	 */
    public abstract int getTimeout();
}
