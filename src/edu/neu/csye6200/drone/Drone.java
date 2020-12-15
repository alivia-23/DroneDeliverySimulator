package edu.neu.csye6200.drone;

import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6200.drone.rules.DDRule;

public class Drone {

	public enum Status {
		AVAILABLE, UNAVAILABLE
	}
	
	private String droneID;  // id of the drone
	private double cargoCapacity; // cargo capacity of the drone
	private DDRule rule;         // movement rule
	private Status status;       
	private List<Location> projectedPath;  // initial path calculated for the drone to deliver a package
	private List<Location> actualPath;    // actual path taken by drone to deliver a package
	private int currentIndex;            // index of the current position of the drone
	private Package pkg;   // Package that the drone is assigned  
	private Location idleLocation; // Location where the drone sits when it is idle
	
	/**
	 * Parameterized Constructor for creating drone
	 * @param droneID
	 * @param cargoCapacity
	 * @param rule
	 * @param idleLocation
	 */
	public Drone(String droneID, double cargoCapacity, DDRule rule, Location idleLocation) {
		this.droneID = droneID;
		this.cargoCapacity = cargoCapacity;
	    this.rule = rule;
		this.status = Status.AVAILABLE;  // set the status of the drone AVAILABLE initially
        this.projectedPath = new ArrayList<>(); 
        this.actualPath = new ArrayList<>();
        this.idleLocation = idleLocation;
	}
	
    /**
     * 
     * @return idle location
     */
	public Location getIdleLocation() {
		return idleLocation;
	}

    /**
     * 
     * @param set idleLocation
     */
	public void setIdleLocation(Location idleLocation) {
		this.idleLocation = idleLocation;
	}


	/**
	 * @return the droneID
	 */
	public String getDroneID() {
		return droneID;
	}

	/**
	 * @param set the droneID
	 */
	public void setDroneID(String droneID) {
		this.droneID = droneID;
	}

	/**
	 * @return the cargoCapacity
	 */
	public double getCargoCapacity() {
		return cargoCapacity;
	}

	/**
	 * @param set the cargoCapacity
	 */
	public void setCargoCapacity(double cargoCapacity) {
		this.cargoCapacity = cargoCapacity;
	}

	/**
	 * @return the rule
	 */
	public DDRule getRule() {
		return rule;
	}

	/**
	 * @param set the movementRule
	 */
	public void setRule(DDRule rule) {
		this.rule = rule;
	}

	/**
	 * @param set the actualPath
	 */
	public void setActualPath(List<Location> actualPath) {
		this.actualPath = actualPath;
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
	 * @return the currentIndex
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}

	/**
	 * @param set the currentIndex
	 */
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}	
	
	/**
	 * @return the pkg
	 */
	public Package getPkg() {
		return pkg;
	}

	/**
	 * @param set the pkg
	 */
	public void setPkg(Package pkg) {
		this.pkg = pkg;
	}

	/**
	 * @return the projectedPath
	 */
	public List<Location> getProjectedPath() {
		return projectedPath;
	}

	/**
	 * @param set the projectedPath
	 */
	public void setProjectedPath(List<Location> projectedPath) {
		this.projectedPath = projectedPath;
	}

	/**
	 * @return the actualPath
	 */
	public List<Location> getActualPath() {
		return actualPath;
	}

    /**
     * Method to add location to actual path
     * @param location
     */
	public void addToActualPath(Location location) {
		actualPath.add(location);
	}	

}
