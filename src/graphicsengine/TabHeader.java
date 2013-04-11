package graphicsengine;

import java.awt.Graphics2D;
import java.awt.Polygon;

import frontend.Constants;



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
		xpoints[0] = _location.x + Constants.TABHEADER_LEFT_RIGHT_OFFSET;
		System.out.println(xpoints[0]);
		ypoints[0] = _location.y;
		System.out.println(ypoints[0]);
		xpoints[1] = _location.x + _size.x - Constants.TABHEADER_LEFT_RIGHT_OFFSET;
		System.out.println(xpoints[1]);
		ypoints[1] = _location.y;
		System.out.println(ypoints[1]);
		xpoints[2] = _location.x;
		System.out.println(xpoints[2]);
		ypoints[2] = _location.y + _size.y;
		System.out.println(ypoints[2]);
		xpoints[3] = _location.x + _size.x;
		System.out.println(xpoints[3]);
		ypoints[3] = _location.y + _size.y;
		System.out.println(ypoints[3]);
		
		_polygon = new Polygon();
		_polygon.addPoint(xpoints[0], ypoints[0]);
		_polygon.addPoint(xpoints[1], ypoints[1]);
		_polygon.addPoint(xpoints[2], ypoints[2]);
		_polygon.addPoint(xpoints[3], ypoints[3]);
//		_polygon = new Polygon(xpoints, ypoints, 4);
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
