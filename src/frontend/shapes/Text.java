package frontend.shapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

public class Text {
	
	private Font _font;
	
	private String _fontName;
	private int _fontSize;
	private String _stringToDisplay;
	
	private Color _color;
	private Coord _location;
	private int _type;
	
	public Text(String fontName, String style, int fontSize, String stringToDisplay, Color textColor, Coord location) {
		_fontName = fontName;
		_fontSize = fontSize;
		_stringToDisplay = stringToDisplay;
		_color = textColor;
		_location = location;
		
		_type = Font.PLAIN;
		if (style == "bold") {
			_type = Font.BOLD;
		}
		else if (style == "plain") {
			_type = Font.PLAIN;
		}
		else if (style == "italic") {
			_type = Font.ITALIC;
		}
		
		_font = new Font(_fontName, _type, _fontSize);
	}
	
	public void setLocation(Coord location) {
		_location = location;
	}
	
	public Coord getLocation() {
		return _location;
	}
	
	public void setTextSize(int size) {
		_fontSize = size;
		_font = new Font(_fontName, _type, _fontSize);
	}
	
	public int getTextSize() {
		return _fontSize;
	}
	
	public void setStringToDisplay(String stringToDisplay) {
		_stringToDisplay = stringToDisplay;
	}
	
	public String getStringToDisplay() {
		return _stringToDisplay;
	}
	
	public void setColor(Color color) {
		_color = color;
	}
	
	public Color getColor() {
		return _color;
	}
	
	public void onDraw(Graphics2D g) {
		FontRenderContext frc = g.getFontRenderContext();
		TextLayout tl = new TextLayout(_stringToDisplay, _font, frc);
		g.setColor(_color);
		
		//calculations so text location is set correctly to top left of bounding box
		double ox = _location.x - tl.getBounds().getX();
		double oy = _location.y - tl.getBounds().getY();		
		tl.draw(g, (float)ox, (float)oy);
	}
	
	/**
	 * Draws the text centered in the rectangle passed in (the rectangle is not drawn, but the text is centered there)
	 * @param g
	 * @param rect
	 */
	public void onDraw(Graphics2D g, Rectangle rect) {
		FontRenderContext frc = g.getFontRenderContext();
		TextLayout tl = new TextLayout(_stringToDisplay, _font, frc);
		g.setColor(_color);
		
		//calculations to center text in the rectangle
		double cx = rect.getLocation().x + rect.getSize().x/2 - tl.getBounds().getWidth()/2;
		double cy = rect.getLocation().y + rect.getSize().y/2 - tl.getBounds().getHeight()/2;
		double ox = cx - tl.getBounds().getX();
		double oy = cy - tl.getBounds().getY();		
		tl.draw(g, (float)ox, (float)oy);
	}
	
	

	
}
