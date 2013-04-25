package frontend.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class FourSidedPolygon extends GenericShape {
	
	private java.awt.Polygon _polygon;
	private Coord _topLeftOffset;
	private Coord _topRightOffset;
	private Coord _bottomLeftOffset;
	private Coord _bottomRightOffset;

	public FourSidedPolygon(Coord location, Coord size, Color fillColor, Coord topLeftOffset, Coord topRightOffset, Coord bottomLeftOffset, Coord bottomRightOffset) {
		super(location, size, fillColor);
		_topLeftOffset = topLeftOffset;
		_topRightOffset = topRightOffset;
		_bottomLeftOffset = bottomLeftOffset;
		_bottomRightOffset = bottomRightOffset;
		adjustPolygon();
	}
	
	public FourSidedPolygon(Coord location, Coord size, Color fillColor, int strokeWidth, Color borderColor, Coord topLeftOffset, Coord topRightOffset, Coord bottomLeftOffset, Coord bottomRightOffset) {
		super(location, size, fillColor, strokeWidth, borderColor);
		_topLeftOffset = topLeftOffset;
		_topRightOffset = topRightOffset;
		_bottomLeftOffset = bottomLeftOffset;
		_bottomRightOffset = bottomRightOffset;
		adjustPolygon();
	}
	
	public void setSize(Coord size) {
		super.setSize(size);
		adjustPolygon();
	}
	
	public void setLocation(Coord location) {
		super.setLocation(location);
		adjustPolygon();
	}
	
	public boolean containsPoint(Coord c) {
		return _polygon.contains(new Point(c.x, c.y));
	}
	
	private void adjustPolygon() {
		int[] xpoints = new int[4];
		int[] ypoints = new int[4];
		
		xpoints[0] = getLocation().x + _topLeftOffset.x;
		ypoints[0] = getLocation().y + _topLeftOffset.y;
		
		xpoints[1] = getLocation().x + getSize().x - + _topRightOffset.x;
		ypoints[1] = getLocation().y + _topRightOffset.y;
		
		xpoints[2] = getLocation().x + getSize().x + _bottomRightOffset.x;
		ypoints[2] = getLocation().y + getSize().y + _bottomRightOffset.y;
		
		xpoints[3] = getLocation().x + _bottomLeftOffset.x;
		ypoints[3] = getLocation().y + getSize().y + _bottomLeftOffset.x;
		
		_polygon = new java.awt.Polygon(xpoints, ypoints, 4);
	}

	@Override
	public void onDraw(Graphics2D g) {
		java.awt.Color savedColor = g.getColor();
		java.awt.Stroke savedStroke = g.getStroke();
		g.setColor(getFillColor());
		g.fill(_polygon);
		g.setColor(savedColor);
		g.setStroke(new java.awt.BasicStroke(getStrokeWidth()));
		if (getStrokeWidth() != 0) {
			g.setColor(getFillColor());
			g.draw(_polygon);
			g.setColor(savedColor);
			g.setStroke(savedStroke);
		}
	}
	
}
