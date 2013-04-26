package frontend.graphicsengine;

import java.awt.Graphics2D;

import frontend.shapes.Coord;


//used to be called PhysObj
public interface Displayable {

	/**
	 * Sets the size of the object
	 * @param c the new size of the object
	 */
	public void setSize(Coord c);
	
	/**
	 * Gets the size of the object
	 * @return the size of the object
	 */
	public Coord getSize();
	
	/**
	 * Sets the location of the object on the screen
	 * @param c
	 */
	public void setLocation(Coord c);
	
	/**
	 * Gets the location of the object on the screen
	 * @return the location of the object
	 */
	public Coord getLocation();
	
	/**
	 * Draws the object
	 * @param g the brush
	 */
	public void onDraw(Graphics2D g);
	
}
