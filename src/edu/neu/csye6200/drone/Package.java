package edu.neu.csye6200.drone;

import java.util.ArrayList;
import java.util.List;

public class Package {
	
	private enum Status {
		UNDELIVERED, IN_TRANSIT, DELIVERED
	}
	
	private Location destination;
	private double weight;
	private String packageID;
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
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
	 * @param packageID the packageID to set
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
	 * @param status the status to set
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
	 * @param path the path to set
	 */
	public void setPath(List<Location> path) {
		this.path = path;
	}

	Status status;
	
	private List<Location> path;

	public Package(String packageID, Location pickup, Location destination, double weight) {
		this.packageID = packageID;
		this.pickup = pickup;
		this.destination = destination;
		this.weight = weight;
		this.status = Status.UNDELIVERED;
		path = new ArrayList<>();
	}
	
	private Location pickup;
	/**
	 * @return the pickup
	 */
	public Location getPickup() {
		return pickup;
	}

	/**
	 * @param pickup the pickup to set
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
	 * @param destination the destination to set
	 */
	public void setDestination(Location destination) {
		this.destination = destination;
	}
	
	public void addLoctionToPath(Location location) {
		path.add(location);
	}
}
