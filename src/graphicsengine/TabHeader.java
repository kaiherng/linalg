package graphicsengine;

import java.awt.Graphics2D;
import java.awt.Polygon;

import swinglayer.Coord;

/**
 *  Something that looks like this:
 * x  _________
 *   /  title  \
 *  /___________\
 *
 * The location refers to the x (topleft of the bounding box)
 * Size refers to the size of the bounding box
 * 
 * @author Kai
 *
 */
public class TabHeader extends GenericShape {
	
	private Coord _location; 
	private Coord _size;
	private Polygon _polygon;
	private String _title;
	
	public TabHeader(Coord location, Coord size, String title) {
		_location = location;
		_size = size;
		_title = title;
		adjustPolygon();
	}
	
	public void adjustPolygon() {
		int[] xpoints = new int[4];
		int[] ypoints = new int[4];
		xpoints[0] = _location.x + 5;
		ypoints[0] = _location.y;
		xpoints[1] = _location.x + _size.x - 5;
		ypoints[1] = _location.y;
		xpoints[2] = _location.x;
		ypoints[2] = _location.y + _size.y;
		xpoints[3] = _location.x + _size.x;
		ypoints[3] = _location.y + _size.y;
		_polygon = new Polygon(xpoints, ypoints, 4);
	}
	
	public void setLocation(Coord location) {
		_location = location;
		adjustPolygon();
	}
	
	public void setSize(Coord size) {
		_size = size;
		adjustPolygon();
	}
	
	public void onDraw(Graphics2D g) {
		g.fillPolygon(_polygon);
	}
	

}
