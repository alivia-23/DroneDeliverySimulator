package edu.neu.csye6200.drone;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

import edu.neu.csye6200.drone.rules.DDRule;
import edu.neu.csye6200.drone.rules.DDRule1;
import edu.neu.csye6200.drone.rules.DDRule2;
import edu.neu.csye6200.drone.rules.DDRule3;
import edu.neu.csye6200.drone.util.PathUtil;

/**
 * 
 * Simulation class which performs the drone simulation
 */
public class DDSimulation extends Observable implements Runnable  {
	
	private int rows = 39; // rows on the deliveryMap
	private int cols = 63; // cols on the deliveryMap
	
	private Thread thread = null;   // the thread that runs my simulation
	private boolean done = false;   //set true to end my simulation loop
	private boolean paused = false; //set true to pause my simulation 
	
	
	private Location[][] deliveryMap; // delivery map
	
	List<Package> packages; // list of packages that are to be delivered
	
	List<Drone> drones; // list of available drones
	
	DDRule rule = null; // rule governing the drone movement
	
	/**
	 * Constructor DDSimulation
	 */
	public DDSimulation() {

		rule = new DDRule1();
	}
	
	/**
	 * Create and initiate the delivery map
	 */
	public void initDeliveryMap() {
		deliveryMap = new Location[rows][cols];
		for (int i = 0; i < deliveryMap.length; i++) {
			for (int j = 0; j < deliveryMap[0].length; j++) {
				Location location = new Location(i, j);
				deliveryMap[i][j] = location;
			}
		}
	}
	

	/**
	 * Start the simulation
	 */
	public void startSim() {
		System.out.println("Starting the simulation");
		if (thread != null) {
			return;
		}
		thread = new Thread(this); 
		done = false;
		paused = false;
		initDeliveryMap();
		packages = new ArrayList<Package>();
		drones = new ArrayList<Drone>();
		createInitPackages();
		createInitDrones();
		createObstacles();
		PackageAssigner.assignPkgToDrone(packages, drones);
		thread.start();
	}
	

	/**
	 * Stop the simulation
	 */
	public void stopSim() {
		System.out.println("Stopping the simulation");
		if (thread == null) return;
		done = true;
	}
	
	/**
	 * Start the simulation thread
	 */
	@Override
	public void run() {
		runSimLoop();  
		thread = null; 
	}
	
	/**
	 * Make the current thread sleep
	 * @param millis
	 */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 *  Run the simulation loop
	 *  
	 */
	private void runSimLoop() {
		int time = 0;
		sleep(2000);
		while (!done) {
			if(!paused) {
				if(time > rule.getTimeout()) {
					System.out.println("Stopping simulation as time elasped is greater than timeout");
				}
				System.out.println("Starting time: "+ time);
				updateSim();
				time++;  				
			}
			sleep(500);
		}
	}
	
	/**
	 * Assign a path to the drone
	 * @param drone
	 */
	public void setPath(Drone drone) {
		List<Location> path = PathUtil.getPath(deliveryMap, drone.getPkg().getPickup(), drone.getPkg().getDestination(), drone.getRule().getDirections());
		drone.setProjectedPath(path);
		drone.setCurrentIndex(0);
	}
	
	/**
	 * Perform an update to the simulation per unit time
	 */
	private void updateSim() {
		List<Drone> inTransitDrones = drones.stream().filter(d -> d.getStatus() == Drone.Status.UNAVAILABLE).collect(Collectors.toList());
		if (inTransitDrones.size() == 0) {
			System.out.println("No more packages to deliver. Stopping simulation");
			stopSim();
		}
		for (Drone drone : inTransitDrones) { // loop through all the drones in transit	
			if (drone.getProjectedPath().size() == 0) {
				pickupPackage(drone);
			} else {  // drone transition to the next location
				Location currentLocation = drone.getProjectedPath().get(drone.getCurrentIndex());
				Location nextProjectedLocation = drone.getProjectedPath().get(drone.getCurrentIndex()+1);
				if (nextProjectedLocation.isVacant()) {    // checks if next location is vacant  
					moveToNextLocation(drone, currentLocation, nextProjectedLocation); // moves drone to next location and update current location
					if (nextProjectedLocation.equals(drone.getPkg().getDestination())) {   
						deliverPackage(drone);                              // if next location is the destination, deliver package
						deliveryMap[nextProjectedLocation.x][nextProjectedLocation.y].setVacant(true); // set the next projected location free
					}
				} else { // if next location is blocked
					int hoverTime = PathUtil.getHoverTime(drone.getActualPath());
					if (hoverTime >= drone.getRule().getMaxHoverTime()) { // if current hovering time exceeds from defined rule hovering time
						calculateAlternatePath(drone, currentLocation, nextProjectedLocation); // calculate alternate path  
					} else {  // hover on the current location
						hover(drone,currentLocation, nextProjectedLocation);
					}
				}
			}
			setChanged();
			notifyObservers(deliveryMap);
			sleep(1000);
		}
		System.out.println("****************************************");
	}
	
	/**
	 * Create the list of packages and set their source and destination
	 */
    public void createInitPackages() {
		
    	Location src0 = deliveryMap[3][3];
    	Location dest0 = deliveryMap[9][9];
		src0.setEntity(Location.Entity.PKG_SRC);
		dest0.setEntity(Location.Entity.PKG_DEST);
		Package p0 = new Package("P123", src0, dest0, 10);
		packages.add(p0);
		
		Location src1 = deliveryMap[3][6];
		Location dest1 = deliveryMap[3][18];
		src1.setEntity(Location.Entity.PKG_SRC);
		dest1.setEntity(Location.Entity.PKG_DEST);
		Package p1 = new Package("P234", src1, dest1, 8);
		packages.add(p1);
		
		Location src2 = deliveryMap[12][15];
		Location dest2 = deliveryMap[0][15];
		src2.setEntity(Location.Entity.PKG_SRC);
		dest2.setEntity(Location.Entity.PKG_DEST);
		Package p2 = new Package("P345", src2, dest2, 5);
		packages.add(p2);
		
		setChanged();
		notifyObservers(deliveryMap);
	
	}
	
    /**
	 * Creates the list of initial available drones
	 */
	public void createInitDrones() {
		
		Location src0 = deliveryMap[0][0];
		src0.setEntity(Location.Entity.DRONE_IDLE);
		Drone d0 = new Drone("D0-123", 10.0, rule, src0);
		drones.add(d0);
		
		Location src1 = deliveryMap[3][0];
		src1.setEntity(Location.Entity.DRONE_IDLE);
		Drone d1 = new Drone("D1-234", 8.0, rule, src1);
		drones.add(d1);

		Location src2 = deliveryMap[7][0];
		src2.setEntity(Location.Entity.DRONE_IDLE);
		Drone d2 = new Drone("D2-345", 6.0, rule, src2);
		drones.add(d2);
		
		setChanged();
		notifyObservers(deliveryMap);
	}
	
	/**
	 * Set obstacles in the delivery map
	 */
	public void createObstacles() {
				
		deliveryMap[5][5].setEntity(Location.Entity.OBSTACLE);
		deliveryMap[5][5].setVacant(false);
		
		deliveryMap[8][8].setEntity(Location.Entity.OBSTACLE);
		deliveryMap[8][8].setVacant(false);
		
		setChanged();
		notifyObservers(deliveryMap);
	}
	
	/**
	 * Set movement rules for the drones
	 * @param ruleName
	 */
	public void setRule(String ruleName) {
		if(ruleName.equals("DDRule1")) {
			rule = new DDRule1();
		} else if(ruleName.equals("DDRule2")) {
			rule = new DDRule2();
		} else {
			rule = new DDRule3();
		}
	}
	
	/**
	 * Toggle the Pause button of the simulation
	 */
	public void pauseSim() {
		paused = !paused; // Flip the pause state
		System.out.println("Pause state is: " + paused);
	}
	
	/**
	 * Method to pickup the package by the drone
	 * 
	 * set the drone state in transit
	 * @param drone The moving drone
	 */
	private void pickupPackage(Drone drone) {
		System.out.println("Picking up package "+ drone.getPkg().getPackageID() + " by "+drone.getDroneID());
		drone.getIdleLocation().setEntity(Location.Entity.FREE);
		setPath(drone);
		drone.addToActualPath(drone.getPkg().getPickup());
		drone.getPkg().getPickup().setEntity(Location.Entity.DRONE_INTRANSIT);
	}
	
	/**
	 * Method to deliver package by the drone
	 * Set the drone state free after delivery
	 * 
	 * @param drone The moving drone
	 */
	private void deliverPackage(Drone drone) {
		System.out.println("Package "+ drone.getPkg().getPackageID() + " delivered by drone "+drone.getDroneID());
		System.out.println("Delivery Path "+  drone.getActualPath());
		double totalDeliveryCost = PathUtil.findPathCostPerKg(drone.getActualPath()) * drone.getPkg().getWeight();
		System.out.println("Path cost to deliver package "+  drone.getPkg().getPackageID() + " is $"
		            + totalDeliveryCost);
		drone.setStatus(Drone.Status.AVAILABLE);
	}
	
	/**
	 * Moves drone to the next location
	 * 
	 * @param drone The moving drone 
	 * @param current Current location
	 * @param next Next location
	 */
	private void moveToNextLocation(Drone drone, Location current, Location next) {
		System.out.println("Moving drone " + drone.getDroneID()+ " to " + "location " +next);
		drone.addToActualPath(next);
		drone.getPkg().addLoctionToPath(next);
		drone.setCurrentIndex(drone.getCurrentIndex() + 1);
		deliveryMap[current.x][current.y].setVacant(true);
		deliveryMap[next.x][next.y].setVacant(false);
		current.setEntity(Location.Entity.DRONE_TRACE);
		next.setEntity(Location.Entity.DRONE_INTRANSIT);
	}
	
	/**
	 * Calculate the alternate path
	 * 
	 * @param drone The moving drone 
	 * @param current Current location
	 * @param next Next location
	 */
	private void calculateAlternatePath(Drone drone, Location current, Location next) {
		System.out.println("Next location " + next + " for drone " +  drone.getDroneID()+" is blocked, calculating alternate route!");
		List<Location> path = PathUtil.getPath(deliveryMap, current, drone.getPkg().getDestination(), drone.getRule().getDirections());
		drone.setProjectedPath(path);
		drone.setCurrentIndex(0);
	}
	
	/**
	 * Hover on the same location
	 * 
	 * @param drone The moving drone 
	 * @param current Current location
	 * @param next Next location
	 */
    private void hover(Drone drone, Location current, Location next) {
    	System.out.println("Next location " + next + " for drone " +  drone.getDroneID()+" is blocked. Hovering for 1 unit");
		drone.addToActualPath(current);
		drone.getPkg().addLoctionToPath(current);
	}
	
}
