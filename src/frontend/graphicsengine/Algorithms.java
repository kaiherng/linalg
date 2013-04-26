package frontend.graphicsengine;

import frontend.shapes.Coord;
import frontend.shapes.Polygon;

public class Algorithms {
	
	/**
	 * Given a displayable object and a click location, check if the click location is within the displayable object
	 * @return true if it is within, false otherwise
	 */
	public static boolean clickWithin(Displayable d, Coord clickLocation) {
		Coord location = d.getLocation();
		Coord size = d.getSize();
		if (clickLocation.x > location.x && clickLocation.x < location.x + size.x && clickLocation.y > location.y && clickLocation.y < location.y + size.y) {
			return true;
		}
		return false;
	}

}
