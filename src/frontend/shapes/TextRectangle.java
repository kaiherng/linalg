package frontend.shapes;


import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

import frontend.graphicsengine.Displayable;

/**
 * Text centered in the middle of a rectangle
 * @author kloh
 *
 */
public class TextRectangle implements Displayable {
	
	private Text _text;
	private Rectangle _rectangle;
		
	public TextRectangle(String fontName, String style, int textSize, String stringToDisplay, Color textColor, Color rectColor, Coord location, Coord rectSize) {
		_text = new Text(fontName, style, textSize, stringToDisplay, textColor, location);
		_rectangle = new Rectangle(location, rectSize, rectColor);
	}
	
	public TextRectangle(String fontName, String style, int textSize, String stringToDisplay, Color textColor, Color rectColor, Coord location, Coord rectSize, Color rectBorderColor, int strokeWidth) {
		_text = new Text(fontName, style, textSize, stringToDisplay, textColor, location);
		_rectangle = new Rectangle(location, rectSize, rectColor, strokeWidth, rectBorderColor);
	}
	
	/** WRAPPER METHODS FOR TEXT **/
	
	public void setTextSize(int size) {
		_text.setTextSize(size);
	}
	
	public void setStringToDisplay(String stringToDisplay) {
		_text.setStringToDisplay(stringToDisplay);
	}
	
	public String getStringToDisplay() {
		return _text.getStringToDisplay();
	}
	
	public void setTextColor(Color textColor) {
		_text.setColor(textColor);
	}
	
	public Color getTextColor() {
		return _text.getColor();
	}
	
	@Override
	public void setLocation(Coord location) {
		_text.setLocation(location);
		_rectangle.setLocation(location);
	}
	
	@Override
	public Coord getLocation() {
		return _rectangle.getLocation();
	}
	
	@Override
	public void setSize(Coord size) {
		_rectangle.setSize(size);
	}
	
	@Override
	public Coord getSize() {
		return _rectangle.getSize();
	}
	
	public void setFillColor(Color fillColor) {
		_rectangle.setFillColor(fillColor);
	}
	
	public Color getFillColor() {
		return _rectangle.getFillColor();
	}
	
	public void setBorderColor(Color borderColor) {
		_rectangle.setBorderColor(borderColor);
	}
	
	public Color getBorderColor() {
		return _rectangle.getBorderColor();
	}
	
	public void setStrokeWidth(int strokeWidth) {
		_rectangle.setStrokeWidth(strokeWidth);
	}
	
	public int getStrokeWidth() {
		return _rectangle.getStrokeWidth();
	}
	
	public void onDraw(java.awt.Graphics2D g) {
		_rectangle.onDraw(g);
		_text.onDraw(g, _rectangle);
	}
	
	
}
