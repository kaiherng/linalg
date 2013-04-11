package graphicsengine;

import java.awt.Color;




public class Circle extends GenericShape { 
	
	protected java.awt.geom.Ellipse2D.Float _ellipse; 
	
	public Circle(Coord location, Coord size, Color color) {
		_ellipse = new java.awt.geom.Ellipse2D.Float();
		this.setFillColor(color);
		this.setLocation(location);
		this.setSize(size);
	}
	
	public void setLocation(Coord location) {
		_ellipse.setFrame(location.x, location.y, _ellipse.getWidth(), _ellipse.getHeight());
	}
	
	public void setSize(Coord size) {
		_ellipse.setFrame(_ellipse.getX(), _ellipse.getY(), size.x, size.y);
	}
	
	public void draw (java.awt.Graphics2D aBrush) {
		java.awt.Color savedColor = aBrush.getColor();
		java.awt.Stroke savedStroke = aBrush.getStroke();
		aBrush.setColor(_fillColor);
		aBrush.fill(_ellipse);
		aBrush.setColor(savedColor);
		aBrush.setStroke(new java.awt.BasicStroke(_strokeWidth));
		if (_strokeWidth != 0) {
			aBrush.setColor(_borderColor);
			aBrush.draw(_ellipse);
			aBrush.setColor(savedColor);
			aBrush.setStroke(savedStroke);
		}
	}
	
}