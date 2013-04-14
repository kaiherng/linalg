package frontend.shapes;

import java.awt.Color;
import java.awt.Graphics2D;




public class Rectangle extends GenericShape {
	
	protected java.awt.geom.Rectangle2D.Float _rectangle;
	
	public Rectangle(Coord location, Coord size, Color color) {
		_rectangle = new java.awt.geom.Rectangle2D.Float();
		this.setFillColor(color);
		this.setLocation(location);
		this.setSize(size);
	}
	
	public Rectangle(Coord location, Coord size, Color color, int strokeWidth, Color borderColor) {
		_rectangle = new java.awt.geom.Rectangle2D.Float();
		this.setFillColor(color);
		this.setLocation(location);
		this.setSize(size);
		this.setStrokeWidth(strokeWidth);
		this.setBorderColor(borderColor);
	}
	
	public void setLocation(Coord location) {
		_rectangle.setFrame(location.x, location.y, _rectangle.getWidth(), _rectangle.getHeight());
	}
	
	public Coord getLocation() {
		return new Coord((int)_rectangle.getX(), (int)_rectangle.getY());
	}
	
	public void setSize(Coord size) {
		_rectangle.setFrame(_rectangle.getX(), _rectangle.getY(), size.x, size.y);
	}
	
	public Coord getSize() {
		return new Coord((int)_rectangle.getWidth(), (int)_rectangle.getHeight());
	}
	
	public void onDraw (Graphics2D aBrush) {
		java.awt.Color savedColor = aBrush.getColor();
		java.awt.Stroke savedStroke = aBrush.getStroke();
		aBrush.setColor(_fillColor);
		aBrush.fill(_rectangle);
		aBrush.setColor(savedColor);
		aBrush.setStroke(new java.awt.BasicStroke(_strokeWidth));
		if (_strokeWidth != 0) {
			aBrush.setColor(_borderColor);
			aBrush.draw(_rectangle);
			aBrush.setColor(savedColor);
			aBrush.setStroke(savedStroke);
		}
	}

}
