package edu.neu.csye6200.drone.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import edu.neu.csye6200.drone.Location;

public class PathUtil {
	
	static final String DIAG_TRANSITION = "dt";
	static final String HV_TRANSITION = "hv";
	static final double UNIT_HV_PATHCOST = 1.00;
	static final double UNIT_DIAG_PATHCOST = 1.50;
	
	static final int[][] HV_DIRECTIONS = new int[][] {
		{1,0},
		{-1,0},
		{0,1},
		{0,-1},
	};
	
	static final int[][] DIAG_DIRECTIONS = new int[][] {
		{-1, 1},
		{1,-1},
		{-1,-1},
		{1, 1}
	};
	
	static final int[][] ALL_DIRECTIONS = new int[][] {
		{1,0},
		{-1,0},
		{0,1},
		{0,-1},
		{-1, 1},
		{1,-1},
		{-1,-1},
		{1, 1}
	};

	public PathUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<Location> getPath(Location[][] deliveryMap, Location source, Location destination, int[][] directions) {
		Location dest = getShortestPath(deliveryMap, source, destination, directions);
		List<Location> path = new ArrayList<>();
		
		Location current = dest;
		
		while (current != null) {
			path.add(0, current);
			current = current.parent;
		}
		
		for (int i = 0; i<deliveryMap.length; i++) {
			for (int j = 0; j < deliveryMap[0].length; j++) {
				deliveryMap[i][j].setParent(null);
			}
		}
		return path;
	}
	
	public static void printPath(List<Location> path) {
		for (Location l : path) {
			System.out.println(l);
		}
	}
    
    private static Location getShortestPath(Location[][] deliveryMap, Location source, Location destination, int[][] directions) {
    	
    	boolean[][] visited = new boolean[deliveryMap.length][deliveryMap[0].length];
		Queue<Location> q = new LinkedList<>();
		q.offer(source);
		visited[source.x][source.y] = true;
		
		
			
		while (!q.isEmpty()) {
			Location current = q.poll();
			if (current.x == destination.x && current.y == destination.y) {
				return current;
			}
			
			for (int[] direction : directions) {
				int x = current.x + direction[0];
				int y = current.y + direction[1];
				
				if(isValid(x, y, deliveryMap)) {
					if(!visited[x][y] && deliveryMap[x][y].isVacant()) {
						Location child = deliveryMap[x][y];
						child.parent = current;
						q.offer(child);
						visited[x][y] = true;
					}
				}
			}
		}
		return null;
	}
    
    public static double findPathCost(List<Location> path) {
    	
    	double pathCost = 0;
    	for (int i = 0; i < path.size() - 1; i++) {
    		Location first = path.get(i);
    		Location second = path.get(i+1);
    		String type = getTransitionType(first, second);
    		if (type.equals(DIAG_TRANSITION)) {
    			pathCost += UNIT_DIAG_PATHCOST;
    		} else {
    			pathCost += UNIT_HV_PATHCOST;
    		}
    	}
    	
    	return pathCost;
    }
    
    private static String getTransitionType(Location first, Location second) {
    	if (Math.abs(first.x - second.x ) == 1 && Math.abs(first.y - second.y) == 1) {
    		return DIAG_TRANSITION;
    	} else {
    		return HV_TRANSITION;
    	}
    	
    }
    
    private static boolean isValid(int x, int y, Location[][] deliveryMap) {
    	if (x < 0 || x >=  deliveryMap.length || y < 0 || y >= deliveryMap.length) {
    		return false;
    	}
    	return true;
    }
	 

}
