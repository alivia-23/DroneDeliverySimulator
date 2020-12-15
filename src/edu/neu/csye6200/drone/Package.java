package edu.neu.csye6200.drone;

import java.util.ArrayList;
import java.util.List;

public class Package {
	
	/**
	 * Status of the drones
	 */
	public enum Status {
		UNDELIVERED, IN_TRANSIT, DELIVERED
	}
	
	private Location pickup;    // pickup/source location of the package
	private Location destination; // destination of the package to deliver
	private double weight;     // weight of the package 
	private String packageID;   // id of the package
	Status status;
	
	private List<Location> path;  // holds the path of the package from source to destination

	public Package(String packageID, Location pickup, Location destination, double weight) {
		this.packageID = packageID;
		this.pickup = pickup;
		this.destination = destination;
		this.weight = weight;
		this.status = Status.UNDELIVERED;
		path = new ArrayList<>();
	}
	
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param set the weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the packageID
	 */
	public String getPackageID() {
		return packageID;
	}

	/**
	 * @param set the packageID
	 */
	public void setPackageID(String packageID) {
		this.packageID = packageID;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param set the status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the path
	 */
	public List<Location> getPath() {
		return path;
	}

	/**
	 * @param set the path
	 */
	public void setPath(List<Location> path) {
		this.path = path;
	}
	
	/**
	 * @return the pickup
	 */
	public Location getPickup() {
		return pickup;
	}

	/**
	 * @param set the pickup location
	 */
	public void setPickup(Location pickup) {
		this.pickup = pickup;
	}

	/**
	 * @return the destination
	 */
	public Location getDestination() {
		return destination;
	}

	/**
	 * @param sets the destination location
	 */
	public void setDestination(Location destination) {
		this.destination = destination;
	}
	
	/**
	 * Add location traversed by the package to the path
	 * @param location
	 */
	public void addLoctionToPath(Location location) {
		path.add(location);
	}
}
