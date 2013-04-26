package frontend.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

import frontend.graphicsengine.Displayable;

public abstract class GenericShape implements Displayable {
	
	private Color _fillColor;
	private Coord _location;
	private Coord _size;
	private Color _borderColor  = Color.BLACK;;
	private int _strokeWidth = 0;;
	
	/**
	 * Constructs a shape without a border
	 * @param location
	 * @param size
	 * @param fillColor
	 */
	public GenericShape(Coord location, Coord size, Color fillColor) {
		_fillColor = fillColor;
		_location = location;
		_size = size;
	}
	
	/**
	 * Constructs a shape with a border
	 * @param location
	 * @param size
	 * @param fillColor
	 * @param strokeWidth
	 * @param borderColor
	 */
	public GenericShape(Coord location, Coord size, Color fillColor, int strokeWidth, Color borderColor) {
		_borderColor = borderColor;
		_fillColor = fillColor;
		_strokeWidth = strokeWidth;
		_location = location;
		_size = size;
	}
	
	public void setLocation(Coord location) {
		_location = location;
	}
	
	public Coord getLocation() {
		return _location;
	}
	
	public void setSize(Coord size) {
		_size = size;
	}
	
	public Coord getSize() {
		return _size;
	}
	
	public void setFillColor(Color color) {
		_fillColor = color;
	}
	
	public Color getFillColor() {
		return _fillColor;
	}
	
	public void setBorderColor(Color color) {
		_borderColor = color;
	}
	
	public Color getBorderColor() {
		return _borderColor;
	}
	
	public void setStrokeWidth(int strokeWidth) {
		_strokeWidth = strokeWidth;
	}
	
	public int getStrokeWidth() {
		return _strokeWidth;
	}
	
	public abstract void onDraw(Graphics2D g);
	
}
