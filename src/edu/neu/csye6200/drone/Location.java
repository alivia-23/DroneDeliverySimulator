package edu.neu.csye6200.drone;

public class Location {
	
	public enum Entity {
		FREE, OBSTACLE, PKG_SRC, PKG_DEST, DRONE_IDLE, DRONE_INTRANSIT, DRONE_TRACE
	}
	
	public int x;  // x-coordinate
	public int y;  // y-coordinate
	private boolean vacant; // sets to false if location is blocked or drone in-transit
	public Location parent; // holds previous location
	private Entity entity;
	 

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
		this.vacant = true;  // every location is set to vacant
		this.entity = Entity.FREE;
	}
	
	/**
	 * @return the x-coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param set the x-coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y-coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param set the y-coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @returns vacant
	 */
	public boolean isVacant() {
		return vacant;
	}

	/**
	 * @param  set the vacant
	 */
	public void setVacant(boolean vacant) {
		this.vacant = vacant;
	}
    
	/**
	 * 
	 * @return Entity
	 */
	public Entity getEntity() {
		return entity;
	}
    
	/**
	 * Set the Entity
	 * @param entity
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	/**
	 * @return the parent
	 */
	public Location getParent() {
		return parent;
	}

	/**
	 * @param set the parent
	 */
	public void setParent(Location parent) {
		this.parent = parent;
	}
    
	@Override
	public String toString() {
		return "(" + x + ", " + y +")";
	}
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
