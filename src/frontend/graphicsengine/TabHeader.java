package frontend.graphicsengine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;


import frontend.general.Constants;
import frontend.shapes.Coord;
import frontend.shapes.GenericShape;
import frontend.shapes.Text;


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
	private Text _title;
	private Color _bgColor;
	
	public TabHeader(Coord location, Coord size, String title) {
		_bgColor = Constants.TAB_HEADERBG_INACTIVE_COLOR;
		_location = location;
		_size = size;
		adjustPolygon();
		_title = new Text(Constants.TEXT_FONTSTYLE, "bold", 12, title.toUpperCase(), Constants.TABHEADER_TEXT_ACTIVE_COLOR, location.plus(new Coord(20,3)));
	}
	
	public void adjustPolygon() {
		int[] xpoints = new int[4];
		int[] ypoints = new int[4];
		
		xpoints[0] = _location.x + Constants.TABHEADER_LEFT_RIGHT_OFFSET;
		ypoints[0] = _location.y;
		
		xpoints[1] = _location.x + _size.x - Constants.TABHEADER_LEFT_RIGHT_OFFSET;
		ypoints[1] = _location.y;
		
		xpoints[2] = _location.x + _size.x;
		ypoints[2] = _location.y + _size.y;
		
		xpoints[3] = _location.x;
		ypoints[3] = _location.y + _size.y;
		
		_polygon = new Polygon(xpoints, ypoints, 4);
	}
	
	public void onFocus() {
		_bgColor = Constants.TAB_HEADERBG_ACTIVE_COLOR;
		_title.setColor(Constants.TABHEADER_TEXT_ACTIVE_COLOR); 
	}
	
	public void onBlur() {
		_bgColor = Constants.TAB_HEADERBG_INACTIVE_COLOR;
		_title.setColor(Constants.TABHEADER_TEXT_INACTIVE_COLOR);
	}
	
	public void setLocation(Coord location) {
		_location = location;
		_title.setLocation(_location); //centralizes the title
		int xOffset = (_size.x - _title.getBoundingBoxSize().x)/2;
		int yOffset = (_size.y - _title.getBoundingBoxSize().y)/4;
		_title.setLocation(_location.plus(new Coord(xOffset, yOffset))); //centralizes the title
		adjustPolygon();
	}
	
	public Coord getLocation() {
		return _location;
	}
	
	public void setSize(Coord size) {
		_size = size;
		adjustPolygon();
		_title.setLocation(_location); //centralizes the title
		int xOffset = (_size.x - _title.getBoundingBoxSize().x)/2;
		int yOffset = (_size.y - _title.getBoundingBoxSize().y)/4;
		_title.setLocation(_location.plus(new Coord(xOffset, yOffset))); //centralizes the title
	}
	
	public Coord getSize() {
		return _size;
	}
	
	public boolean containsPoint(Coord c) {
		return _polygon.contains(new Point(c.x, c.y));
	}
	
	public void onDraw(Graphics2D g, boolean focus) {
		java.awt.Color savedColor = g.getColor();
		java.awt.Stroke savedStroke = g.getStroke();
		g.setColor(_bgColor);
		g.fillPolygon(_polygon);
		g.setColor(savedColor);
		g.setStroke(savedStroke);
		_title.onDraw(g);
	}
	

}
