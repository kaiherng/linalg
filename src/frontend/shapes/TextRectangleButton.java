package frontend.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A button that consists of a rectangle with text in it
 * The text is centralized in the middle of the rectangle
 * @author kloh
 *
 */
public class TextRectangleButton {
	
	private Text _text;
	private Rectangle _rectangle;
	private Coord _size; //size of the rectangle
	private Coord _location; //location of the rectangle
	
	public TextRectangleButton(String fontstyle, String style, int textSize, String text, Color textColor, Color rectangleColor, Coord location, Coord size, int borderWidth, Color borderColor) {
		_text = new Text(fontstyle, style, textSize, text, textColor, location);
		_rectangle = new Rectangle(location, size, rectangleColor, borderWidth, borderColor);
		_location = location;
		_size = size;
	}
	
	public void setSize(Coord c) {
		_size = c;
		_rectangle.setSize(c);
		Coord textBoundingBoxSize = _text.getBoundingBoxSize();
		int xoffset = (_size.x - textBoundingBoxSize.x)/2;
		int yoffset = (_size.y - textBoundingBoxSize.y)/4;
		_text.setLocation(_location.plus(xoffset,yoffset));
	}
	
	public Coord getSize() {
		return _size;
	}

	public void setLocation(Coord c) {
		_location = c;
		_rectangle.setLocation(c);
		Coord textBoundingBoxSize = _text.getBoundingBoxSize();
		int xoffset = (_size.x - textBoundingBoxSize.x)/2;
		int yoffset = (_size.y - textBoundingBoxSize.y)/4;
		_text.setLocation(c.plus(xoffset,yoffset));
	}

	public Coord getLocation() {
		return _location;
	}
	
	public void onDraw(Graphics2D g) {
		_rectangle.onDraw(g);
		_text.onDraw(g);
		
	}
}
