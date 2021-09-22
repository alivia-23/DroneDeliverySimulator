# DroneDeliverySimulator
Created a simulation that models the behavior of Delivery Drones, moving packages within a delivery map area.
Packages
•	Each uniform package has a pick-up and drop-off location, plus a weight in Kg.
•	The package weight cannot exceed the cargo capacity of the drone.
•	Each package has a pick-up (departure) location, and a destination (arrival) location.
•	Moving a package incurs cost (freight movement).
o	Moving a 1 Kg to a neighboring horizontal or vertical cell incurs a $1.00 cost
o	Moving a 1 Kg diagonally to a neighboring cell incurs a $1.50 cost.
o	Hovering in place incurs a $0.75 per Kg.
Drones
•	Each Drone can deliver one or more packages, with weight restrictions.
•	A drone has a delivery weight limit that must not be exceeded in flight.
•	Drone movement are governed by a movement Rules
•	Drone’s actions can be controlled remotely
Delivery Map
•	Drones navigate via a two dimensional array of location cells.
•	For safety, only one drone may occupy a cell location cell at a time.
•	Some cells may be “occupied” by fixed or moving entities that block regular movement of the drones and provided algorithmic paths to detect blocks and avoid collision.
•	A drone may stop at a fixed recharge station, and it does not incur any cost to operate.
Simulation
•	Start, pause and stop buttons to perform the simulation actions of the drone in the GUI.
•	Provided a list of packages with their status being maintained (i.e. awaiting pick-up, in-transit, delivered).
•	A time cycle in the simulation should move each delivery drone up to 1 cell (horizontally, vertically, or diagonally), based on a movement rules.
•	A pick-up or drop-off action requires a 1-cycle of hover, but this does not incur a freight charge
•	Drone simulation advance by a unit amount of time, and able to produce data (drone positions, cost, and package delivery status) on a periodic basis.
•	Drone simulation support movement rule options with differing movement algorithms. 
