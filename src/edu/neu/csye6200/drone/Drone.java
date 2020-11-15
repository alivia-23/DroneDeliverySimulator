package edu.neu.csye6200.drone;

import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6200.drone.rules.MovementRule;

public class Drone {
	
	public enum Status {
		AVAILABLE, UNAVAILABLE
	}
	
	private String droneID;
	private double cargoCapacity;
	private MovementRule movementRule;
	private Status status;
	private List<Location> projectedPath;
	private List<Location> actualPath;
	private int currentIndex;
	private Package pkg;
	
	
	public Drone(String droneID, double cargoCapacity, MovementRule movementRule) {
		this.droneID = droneID;
		this.cargoCapacity = cargoCapacity;
	    this.movementRule = movementRule;
		this.status = Status.AVAILABLE;
        projectedPath = new ArrayList<>();
        actualPath = new ArrayList<>();
	}
	

	/**
	 * @return the droneID
	 */
	public String getDroneID() {
		return droneID;
	}



	/**
	 * @param droneID the droneID to set
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
	 * @param cargoCapacity the cargoCapacity to set
	 */
	public void setCargoCapacity(double cargoCapacity) {
		this.cargoCapacity = cargoCapacity;
	}



	/**
	 * @return the movementRule
	 */
	public MovementRule getMovementRule() {
		return movementRule;
	}



	/**
	 * @param movementRule the movementRule to set
	 */
	public void setMovementRule(MovementRule movementRule) {
		this.movementRule = movementRule;
	}



	/**
	 * @param actualPath the actualPath to set
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
	 * @param status the status to set
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
	 * @param currentIndex the currentIndex to set
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
	 * @param pkg the pkg to set
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
	 * @param projectedPath the projectedPath to set
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



	public void addToActualPath(Location location) {
		projectedPath.add(location);
	}
	
	

}
