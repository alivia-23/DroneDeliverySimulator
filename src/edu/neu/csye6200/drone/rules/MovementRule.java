package edu.neu.csye6200.drone.rules;

import java.util.List;

import edu.neu.csye6200.drone.Location;

public abstract class MovementRule {
	
	protected static final int[][] HV_DIRECTIONS = new int[][] {
		{1,0},
		{-1,0},
		{0,1},
		{0,-1},
	};
	
	protected static final int[][] ALL_DIRECTIONS = new int[][] {
		{1,0},
		{-1,0},
		{0,1},
		{0,-1},
		{-1, 1},
		{1,-1},
		{-1,-1},
		{1, 1}
	};


	public abstract int[][] getDirections();
    public abstract int getMaxWaitTime();
}
