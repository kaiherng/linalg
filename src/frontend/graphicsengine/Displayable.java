package frontend.graphicsengine;

import java.awt.Graphics2D;

import frontend.shapes.Coord;


//used to be called PhysObj
public interface Displayable {

	/**
	 * Sets the size of the object
	 * @param c the new size of the object
	 */
	void setSize(Coord c);
	
	/**
	 * Gets the size of the object
	 * @return the size of the object
	 */
	Coord getSize();
	
	/**
	 * Sets the location of the object on the screen
	 * @param c
	 */
	void setLocation(Coord c);
	
	/**
	 * Gets the location of the object on the screen
	 * @return the location of the object
	 */
	Coord getLocation();
	
	/**
	 * Draws the object
	 * @param g the brush
	 */
	void onDraw(Graphics2D g);

	/**
	 * Called when a mouse click event within the object's location on the screen is detected
	 * @param clickCount how many clicks this mouse click event is
	 * @param c the location of the mouse click
	 */
	void onMouseClicked(int clickCount, Coord c);
	
}