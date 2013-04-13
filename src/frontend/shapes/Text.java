package frontend.shapes;


import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

public class Text {
	
	private Font _font;
	private String _fontstyle;
	private int _fontSize;
	private String _text;
	private Color _color;
	private String _string;
	private Coord _location;
	private int _type;
	private Coord _boundingBoxSize;
	
	public Text(String fontstyle, String style, int size, String text, Color color, Coord location) {
		_fontstyle = fontstyle;
		_fontSize = size;
		_text = text;
		_color = color;

		_type = Font.PLAIN;
		if (style == "bold") {
			_type = Font.BOLD;
		}
		if (style == "plain") {
			_type = Font.PLAIN;
		}
		if (style == "italic") {
			_type = Font.ITALIC;
		}
		
		_font = new Font(_fontstyle, _type, _fontSize);
		_string = new String(_text);
		this.setLocation(location);
		_boundingBoxSize = new Coord(0,0);
	}
	
	public void setLocation(Coord location) {
		_location = location;
	}
	
	public Coord getLocation() {
		return _location;
	}
	
	public Coord getBoundingBoxSize() {
		return _boundingBoxSize;
	}
	
	public void setSize(int size) {
		_fontSize = size;
		_font = new Font(_fontstyle, _type, _fontSize);
	}
	
	public void setText(String text) {
		_text = text;
		_string = new String(_text);
	}
	
	public void setColor(Color color) {
		_color = color;
	}
	
	public void draw(java.awt.Graphics2D aBrush) {
		FontRenderContext frc = aBrush.getFontRenderContext();
		TextLayout tl = new TextLayout(_string, _font, frc);
		aBrush.setColor(_color);
		tl.draw(aBrush, _location.x, _location.y + _fontSize);
		_boundingBoxSize = new Coord((int)(tl.getBounds().getWidth()), (int)(tl.getBounds().getHeight()));
	}

	
}
