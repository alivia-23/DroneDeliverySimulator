package edu.neu.csye6200.drone.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import edu.neu.csye6200.drone.Drone;
import edu.neu.csye6200.drone.Location;

/**
 * 
 * Utility class for drone path
 *
 */
public class PathUtil {
	
	static final double UNIT_HV_PATHCOST = 1.00;
	static final double UNIT_DIAG_PATHCOST = 1.50;
	static final double UNIT_HOVER_PATHCOST = .75;
	
	/**
	 * Default Constructor
	 */
	public PathUtil() {}
	
	/**
	 * Reset the projected path for the drone
	 * @param deliveryMap The delivery map
	 * @param drone The drone for which the projected path is calculated
	 */
	public static void resetPathForDrone(Location[][] deliveryMap, Drone drone) {
		List<Location> path = PathUtil.getPath(deliveryMap, drone.getPkg().getPickup(), drone.getPkg().getDestination(), drone.getRule().getDirections());
		drone.setProjectedPath(path);
		drone.setCurrentIndex(0);
	}
	
	/**
	 * 
	 * Calculate path from source to destination
	 * 
	 * @param deliveryMap The delivery map
	 * @param source Source location
	 * @param destination Destination location
	 * @param directions Directions in which the drone is allowed to move
	 * 
	 * @return The list of locations to be traversed to reach from source to destination
	 */
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
	

	/**
	 * 
	 * Calculate 'shortest' path from source to destination
	 * 
	 * @param deliveryMap The delivery map
	 * @param source Source location
	 * @param destination Destination location
	 * @param directions Directions in which the drone is allowed to move
	 * 
	 * @return The list of locations to be traversed to reach from source to destination
	 */
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
    
    /**
     * Calculate the cost of delivery per kg
     * 
     * @param path list of locations traversed by the drone
     * @return Cost of delivery per kg
     */
    public static double findPathCostPerKg(List<Location> path) {  	
    	double pathCost = 0;
    	for (int i = 0; i < path.size() - 1; i++) {
    		Location first = path.get(i);
    		Location second = path.get(i+1);
    		
    		if(( first.x == second.x ) && (first.y == second.y)) { // hover
    			pathCost += UNIT_HOVER_PATHCOST;
    		} else if (Math.abs(first.x - second.x ) == 1 && Math.abs(first.y - second.y) == 1) { // diagonal
    			pathCost += UNIT_DIAG_PATHCOST;
    		} else {                       // horizontal / vertical
    			pathCost += UNIT_HV_PATHCOST;
    		}
    	}
    	
    	return pathCost;
    }
    
    
    /**
     * Check if the location (x,y) is valid
     * @param x x co-ordinate of the location
     * @param y y co-ordinate of the location
     * @param deliveryMap The deliveryMap
     * @return
     */
    private static boolean isValid(int x, int y, Location[][] deliveryMap) {
    	if (x < 0 || x >=  deliveryMap.length || y < 0 || y >= deliveryMap[0].length) {
    		return false;
    	}
    	return true;
    }
    
    /**
     * 
     * Calculates the hover time of a drone on the same location
     * 
     * @param actualPath list of locations traversed by the drone
     * @return The hover time
     */
    public static int getHoverTime(List<Location> actualPath) {
    	int hoverTime = 1;
    	for(int i = actualPath.size() - 1; i > 0; i--) {
    		if(actualPath.get(i).equals(actualPath.get(i-1))) {
    			hoverTime++;
    		} else {
    			break;
    		}
    	}
    	return hoverTime;
    } 

}
