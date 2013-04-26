package frontend.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Polygon extends GenericShape {

	private Coord[] _coords;

	public Polygon(Coord location, Color fillColor, Coord... coords) {
		super(location, new Coord(0,0), fillColor);
		_coords = coords;
		for (int i=0; i<_coords.length; i++) {
			_coords[i] = _coords[i].plus(location);
		}
	}
	
	public Polygon(Coord location, Color fillColor, int strokeWidth, Color borderColor, Coord... coords) {
		super(location, new Coord(0,0), fillColor, strokeWidth, borderColor);
		_coords = coords;
		for (int i=0; i<_coords.length; i++) {
			_coords[i] = _coords[i].plus(location);
		}
	}
	
	/**
	 * No size to set, so ignore (setting size on polygon is non-trivial)
	 */
	public void setSize(Coord size) {
		super.setSize(size);
	}
	
	public void setLocation(Coord location) {
		Coord oldLocation = getLocation();
		super.setLocation(location);
		Coord diff = location.minus(oldLocation);
		for (int i=0; i<_coords.length; i++) {
			_coords[i] = _coords[i].plus(diff);
		}
	}
	
	public boolean containsPoint(Coord c) {
		for (int i=0; i<_coords.length; i++) {
			Coord start = _coords[i];
			Coord end = _coords[(i+1)%_coords.length];
			Coord edgeVector = end.minus(start);
			Coord pointVector = c.minus(start);
			double crossProduct = edgeVector.cross(pointVector);
			if (crossProduct < 0) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void onDraw(Graphics2D g) {
		java.awt.Color savedColor = g.getColor();
		java.awt.Stroke savedStroke = g.getStroke();
		g.setColor(getFillColor());
		
		Path2D.Double path = new Path2D.Double();
		path.moveTo(_coords[0].x,_coords[0].y);
		for (int i=1; i<_coords.length; i++) {
			path.lineTo(_coords[i].x,_coords[i].y);
		}
		path.lineTo(_coords[0].x,_coords[0].y);
		g.fill(path);
		g.setColor(savedColor);
		g.setStroke(new java.awt.BasicStroke(getStrokeWidth()));
		if (getStrokeWidth() != 0) {
			g.setColor(getBorderColor());
			g.draw(path);
			g.setColor(savedColor);
			g.setStroke(savedStroke);
		}
	}
	
}
