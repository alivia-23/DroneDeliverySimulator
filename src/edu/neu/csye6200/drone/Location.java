package edu.neu.csye6200.drone;

public class Location {
	
	 public int x;  // x-coordinate
	 public int y;  // y-coordinate
	 private boolean vacant;
	 public Location parent;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
		this.vacant = true;
	}
	
	
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}



	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}



	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}



	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}



	/**
	 * @return the vacant
	 */
	public boolean isVacant() {
		return vacant;
	}



	/**
	 * @param vacant the vacant to set
	 */
	public void setVacant(boolean vacant) {
		this.vacant = vacant;
	}



	/**
	 * @return the parent
	 */
	public Location getParent() {
		return parent;
	}



	/**
	 * @param parent the parent to set
	 */
	public void setParent(Location parent) {
		this.parent = parent;
	}



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
