package oldfrontend.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class FourSidedPolygon extends GenericShape {
	
	private Coord _topLeftOffset;
	private Coord _topRightOffset;
	private Coord _bottomLeftOffset;
	private Coord _bottomRightOffset;
	private Coord[] _coords = new Coord[4];

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
		for (int i=0; i<4; i++) {
			Coord start = _coords[i];
			Coord end = _coords[(i+1)%4];
			Coord edgeVector = end.minus(start);
			Coord pointVector = c.minus(start);
			double crossProduct = edgeVector.cross(pointVector);
			if (crossProduct < 0) {
				return false;
			}
		}
		return true;
	}
	
	private void adjustPolygon() {
		_coords[0] = getLocation().plus(_topLeftOffset);
		_coords[1] = getLocation().plus(new Coord(getSize().x,0)).minus(_topRightOffset);
		_coords[2] = getLocation().plus(getSize()).minus(_bottomRightOffset);
		_coords[3] = getLocation().plus(new Coord(0, getSize().y)).plus(_bottomLeftOffset);
	}
	
	@Override
	public void onDraw(Graphics2D g) {
		java.awt.Color savedColor = g.getColor();
		java.awt.Stroke savedStroke = g.getStroke();
		g.setColor(getFillColor());
		
		Path2D.Double path = new Path2D.Double();
		path.moveTo(_coords[0].x,_coords[0].y);
		path.lineTo(_coords[1].x,_coords[1].y);
		path.lineTo(_coords[2].x,_coords[2].y);
		path.lineTo(_coords[3].x,_coords[3].y);
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
