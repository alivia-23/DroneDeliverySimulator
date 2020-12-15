package edu.neu.csye6200.drone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Assigns packages to drones
 *
 */
public class PackageAssigner {
	
	/**
	 * Method to assign package to drones
	 * @param packages
	 * @param drones
	 */
	public static void assignPkgToDrone(List<Package> packages, List<Drone> drones) {
		List<Package> undeliveredPackages = getUndeliveredPackages(packages); 
		List<Drone> availableDrones = getAvailableDrones(drones);
		
		// sort packages based on weight in ascending order
		Collections.sort(undeliveredPackages, (a, b) -> a.getWeight() < b.getWeight() ? -1 : 1); 
		
		// sort available drones based on cargo capacity in ascending order
		Collections.sort(availableDrones, (a, b) -> a.getCargoCapacity() < b.getCargoCapacity() ? -1 : 1);
		
		int pkgPtr = 0; // pointer pointing to 1st element in the undelivered packages after sorting
		int drnPtr = 0;  // pointer pointing to 1st element in the available drones after sorting
		int pkgSize = packages.size(); 
		int drnSize = drones.size();
		while (pkgPtr < pkgSize && drnPtr < drnSize) {  // loop through the number of packages and drones
          double pkgWt = undeliveredPackages.get(pkgPtr).getWeight();
		  double cargoCapacity = availableDrones.get(drnPtr).getCargoCapacity();
			if (pkgWt <= cargoCapacity) {                   // if package weight less than cargo capacity
				availableDrones.get(drnPtr).setPkg(undeliveredPackages.get(pkgPtr)); // assign package to drone
				availableDrones.get(drnPtr).setStatus(Drone.Status.UNAVAILABLE);   // make the drone unavailable
				undeliveredPackages.get(pkgPtr).setStatus(Package.Status.IN_TRANSIT); // set status of the package to in-transit
				pkgPtr++;
				drnPtr++;
			} else {
				drnPtr++;
			}
		}
	}
	
	/**
	 * Method to get list of available drones
	 * @param drones
	 * @return
	 */
	private static List<Drone> getAvailableDrones(List<Drone> drones) {
		List<Drone> availableDrones = new ArrayList<Drone>();
		for (Drone drone : drones) {
			if (drone.getStatus() == Drone.Status.AVAILABLE) {
				availableDrones.add(drone);
			}
		}
		return availableDrones;
	}
	
	/**
	 * Method to get list of undelivered packages
	 * @param packages
	 * @return
	 */
	private static List<Package> getUndeliveredPackages(List<Package> packages) {
		List<Package> availablePackages = new ArrayList<>();
		for (Package parcel : packages) {
			if (parcel.getStatus() == Package.Status.UNDELIVERED) {
				availablePackages.add(parcel);
			}
		}
		return availablePackages;
	}

}
