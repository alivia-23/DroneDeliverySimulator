package edu.neu.csye6200.drone.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import edu.neu.csye6200.drone.Location;

public class DDPanel extends JPanel implements Observer {	

	private Graphics2D g2d;
	private int boxSize = 19;
	private int rows;
	private int cols;
	private Location[][] locations;
	
	
	/**
	 * Paints the screen and calls method drawCanvas to show the initial render of the screen
	 */
	public void paint(Graphics g) {
		drawCanvas(g);
	}
	
	public void drawCanvas(Graphics g) {
		g2d = (Graphics2D) g;
		
		Dimension size = getSize(); // width and height
		
		cols = size.width / boxSize;
	    rows = size.height / boxSize;
		
		//Looping to draw a grid of box cells
		for (int i = 0; i < rows; i++) { //rows
			for (int j = 0; j < cols; j++) { //columns			
				if (locations!= null && locations[i][j].getEntity() == Location.Entity.OBSTACLE)
					g2d.setColor(Color.BLACK);
				else if (locations!= null && locations[i][j].getEntity() == Location.Entity.PKG_SRC)
					g2d.setColor(Color.ORANGE);
				else if (locations!= null && locations[i][j].getEntity() == Location.Entity.PKG_DEST)
					g2d.setColor(Color.GREEN);
				else if (locations!= null && locations[i][j].getEntity() == Location.Entity.DRONE_IDLE)
					g2d.setColor(Color.RED);
				else if (locations!= null && locations[i][j].getEntity() == Location.Entity.DRONE_INTRANSIT)
					g2d.setColor(Color.BLUE);
				else if (locations!= null && locations[i][j].getEntity() == Location.Entity.DRONE_TRACE) {
					g2d.setColor(Color.WHITE);
				}
				else 
					g2d.setColor(Color.PINK);
				g2d.fillRect(j*20, i*20, boxSize, boxSize);			
			}
		}
	}

	/**
	 * @return the g2d
	 */
	public Graphics2D getG2d() {
		return g2d;
	}

	/**
	 * @param method to set the g2d
	 */
	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}

	/**
	 * @return the boxSize
	 */
	public int getBoxSize() {
		return boxSize;
	}

	/**
	 * @param method to set the boxSize
	 */
	public void setBoxSize(int boxSize) {
		this.boxSize = boxSize;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param method to set the rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return the columns
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @param method to set columns
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
     * Simulation thread will call this method as part of the observer interface
     */
	@Override
	public void update(Observable o, Object arg) {
		if(arg!= null && arg instanceof Location[][]) {
			this.locations = (Location[][]) arg;
			this.repaint();  // Cause a redraw of the panel
		}
	}
}
