package frontend.graphicsengine;

import frontend.shapes.Coord;

public interface Interactable {

	/**
	 * Called when a mouse click event within the object's location on the screen is detected
	 * @param clickCount how many clicks this mouse click event is
	 * @param c the location of the mouse click
	 */
	void onMouseClicked(int clickCount, Coord c);
	
}
