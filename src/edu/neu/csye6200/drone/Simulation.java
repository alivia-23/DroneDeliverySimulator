
package edu.neu.csye6200.drone;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.neu.csye6200.drone.newutil.PathUtil;
import edu.neu.csye6200.drone.rules.MovementRule;
import edu.neu.csye6200.drone.rules.MovementRule2;

public class Simulation {
	
	Location[][] deliveryMap;
	List<Package> packages;
	List<Drone> drones;
	int len = 3;
	
	public static void main(String[] args) throws Exception {
		new Simulation().run();
	}
	
	public Simulation() {
		
	}
	
	public void run() throws Exception {
		packages = new ArrayList<Package>();
		drones = new ArrayList<Drone>();
		createDeliveryMap();
		createPackages();
		createDrones();
		assignPackageToDrones();
		
		
		
		int time = 0;
		while(true) {
			System.out.println("Starting time " + time);
			List<Drone> inTransitDrones = drones.stream().filter(d -> d.getStatus() == Drone.Status.UNAVAILABLE).collect(Collectors.toList());
			if(inTransitDrones.size() == 0) {
				System.out.println("No more packages to deliver. Stopping simulation");
				break;
			}
			for (Drone drone : inTransitDrones) {
				Location currentLocation = drone.getProjectedPath().get(drone.getCurrentIndex());
				Location nextProjectedLocation = drone.getProjectedPath().get(drone.getCurrentIndex()+1);
				if (nextProjectedLocation.isVacant()) {
					System.out.println("Moving drone " + drone.getDroneID()+ " to " + "location " +nextProjectedLocation);
					drone.addToActualPath(nextProjectedLocation);
					drone.getPkg().addLoctionToPath(nextProjectedLocation);
					drone.setCurrentIndex(drone.getCurrentIndex() + 1);
					deliveryMap[currentLocation.x][currentLocation.y].setVacant(true);
					deliveryMap[nextProjectedLocation.x][nextProjectedLocation.y].setVacant(false);
					if (nextProjectedLocation.equals(drone.getPkg().getDestination())) {
						System.out.println("Package "+ drone.getPkg().getPackageID() + " delivered by drone "+drone.getDroneID());
						drone.setStatus(Drone.Status.AVAILABLE);
						deliveryMap[nextProjectedLocation.x][nextProjectedLocation.y].setVacant(true);
					}
				} else {
					System.out.println("Next location " + nextProjectedLocation + " fpr drone " +  drone.getDroneID()+" is blocked, calculating alternate route!");
					List<Location> path = PathUtil.getPath(deliveryMap, currentLocation, drone.getPkg().getDestination(), drone.getMovementRule().getDirections());
					drone.setProjectedPath(path);
					drone.setCurrentIndex(0);
				}
				Thread.sleep(5000);
			}
			time++;
			System.out.println("****************************************");
		}
		
	}
	
	public void createDeliveryMap() {
		deliveryMap = new Location[len][len];
		for (int i = 0; i<deliveryMap.length; i++) {
			for (int j = 0; j < deliveryMap[0].length; j++) {
				Location location = new Location(i, j);
				deliveryMap[i][j] = location;
			}
		}
	}
	
	public List<Package> createPackages() {
		Package p0 = new Package("P123", deliveryMap[0][0], deliveryMap[2][2], 10);
		packages.add(p0);
		Package p1 = new Package("P345", deliveryMap[0][2], deliveryMap[2][0], 8);
		packages.add(p1);
		return packages;	
	}
	
	public void createDrones() {
		MovementRule rule = new MovementRule2();
		Drone d0 = new Drone("D0-123", 10.0, rule);
		drones.add(d0);
		Drone d1 = new Drone("D1-234", 8.0, rule);
		drones.add(d1);
	}
	
	public void assignPackageToDrones() {
		
		Drone drone0 = drones.get(0);
		Package pkg0 = packages.get(0);		

		drone0.setStatus(Drone.Status.UNAVAILABLE);
		drone0.setPkg(pkg0);
		
		List<Location> path = PathUtil.getPath(deliveryMap, pkg0.getPickup(), pkg0.getDestination(), drone0.getMovementRule().getDirections());
		drone0.setProjectedPath(path);
		drone0.setCurrentIndex(0);
		
		
		Drone drone1 = drones.get(1);
		Package pkg1 = packages.get(1);		

		drone1.setStatus(Drone.Status.UNAVAILABLE);
		drone1.setPkg(pkg1);
		
		List<Location> path1 = PathUtil.getPath(deliveryMap, pkg1.getPickup(), pkg1.getDestination(), drone1.getMovementRule().getDirections());
		drone1.setProjectedPath(path1);
		drone1.setCurrentIndex(0);


	}
}
