package oldfrontend.graphicsengine;

import java.awt.Graphics2D;

import oldfrontend.shapes.Coord;



/** 
 * An interface that defines a Screen. A screen is everything that the user sees in the program
 * We define this as an interface, and not an abstract class, because each screen implements these user interaction methods differently
 * It wouldn't make sense to have an abstract class with all abstract methods -- might as well just use an interface
 * @author Kai
 *
 */

public interface Screen {
	
	/**
	 * Draws the contents of the screen
	 * @param g the brush to draw with
	 */
	public void onDraw(Graphics2D g);
	
	/**
	 * When a key is pressed
	 * @param keycode
	 */
	public void onDown(int keycode);
	
	
	public void onUp(int keycode);
	
	
	public void onRepeated(int keycode);
	
	
	public void onTyped(char keychar);
	
	
	public void onResize(Coord newSize);
	
	/**
	 * Called when the mouse is clicked at a location on the screen
	 * @param clickCount the number of times the user clicks
	 * @param location the location on the screen
	 */
	public void onMouseClicked(int clickCount, Coord location);
	
	public void onMousePressed();
	
	public void onMouseReleased();
	
	public void onMouseDragged(Coord location);
	
	public void onMouseMoved(Coord location);
	
	public void onMouseWheelForward(Coord location);
	
	public void onMouseWheelBackward(Coord location);
	
	public void onDragStart(Coord location);
	
	public void onDragEnd(Coord location);
}
