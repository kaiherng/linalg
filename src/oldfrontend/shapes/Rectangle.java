package oldfrontend.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

public class Rectangle extends GenericShape {
	
	private java.awt.geom.Rectangle2D.Float _rectangle;
	
	/**
	 * Constructs a rectangle with no border
	 * @param location
	 * @param size
	 * @param fillColor
	 */
	public Rectangle(Coord location, Coord size, Color fillColor) {
		super(location, size, fillColor);
		_rectangle = new java.awt.geom.Rectangle2D.Float();
		_rectangle.setFrame(location.x, location.y, size.x, size.y);
	}
	
	/**
	 * Constructs a rectangle with a border
	 * @param location
	 * @param size
	 * @param fillColor
	 * @param strokeWidth
	 * @param borderColor
	 */
	public Rectangle(Coord location, Coord size, Color fillColor, int strokeWidth, Color borderColor) {
		super(location, size, fillColor, strokeWidth, borderColor);
		_rectangle = new java.awt.geom.Rectangle2D.Float();
		_rectangle.setFrame(location.x, location.y, size.x, size.y);
	}
	
	@Override
	public void setLocation(Coord location) {
		super.setLocation(location);
		_rectangle.setFrame(location.x, location.y, _rectangle.getWidth(), _rectangle.getHeight());
	}
	
	@Override
	public void setSize(Coord size) {
		super.setSize(size);
		_rectangle.setFrame(_rectangle.getX(), _rectangle.getY(), size.x, size.y);
	}
	
	@Override
	public void onDraw (Graphics2D g) {
		java.awt.Color savedColor = g.getColor();
		java.awt.Stroke savedStroke = g.getStroke();
		g.setColor(getFillColor());
		g.fill(_rectangle);
		g.setColor(savedColor);
		g.setStroke(new java.awt.BasicStroke(getStrokeWidth()));
		if (getStrokeWidth() != 0) {
			g.setColor(getBorderColor());
			g.draw(_rectangle);
			g.setColor(savedColor);
			g.setStroke(savedStroke);
		}
	}

}
