package graphicsengine;

import java.awt.Color;
import java.awt.Graphics2D;

import shapes.Coord;
import shapes.Rectangle;

import frontend.Constants;



public class Tab {
	
	/**
	 * Whether or not this tab is in focus
	 */
	private boolean _focus;
	
	/**
	 * The container that holds the contents of the tab
	 */
	private Container _main;
	
	/**
	 * The background of the tab container
	 */
	private Rectangle _mainBackground;
	
	/**
	 * The header of the tab
	 */
	private TabHeader _header;
	
	private String _title;
	
	/**
	 * Constructs a new tab
	 * @param c the container that the tab holds
	 * @param title the title of the tab
	 * @param color the color of the tab
	 */
	public Tab(Container c, String title) {
		_main = c;
		_mainBackground = new Rectangle(new Coord(0,0), new Coord(0,0), Constants.TAB_MAINBG_COLOR, Constants.TABMAIN_BORDER_WIDTH, Constants.TABMAIN_BORDER_COLOR); //just create an empty rectangle for now, the size will be set when the onDraw is called by frame
		_header = new TabHeader(new Coord(0,0), new Coord(Constants.TABHEADER_WIDTH, Constants.TABHEADER_HEIGHT), title);
		_title = title;
	}
	
	public void onBlur() {
		_focus = false;
		_header.onBlur();
	}
	
	@Override
	public String toString() {
		return "Tab [_title=" + _title + ", _focus=" + _focus + "]";
	}

	public void onFocus() {
		_focus = true;
		_header.onFocus();
	}
	
	public void setColor(Color c) {
		_header.setFillColor(c);
		_mainBackground.setFillColor(c);
	}

	public void setMainSize(Coord c) {
		_mainBackground.setSize(c.minus(0, Constants.TABHEADER_HEIGHT));
	}

	public Coord getMainSize() {
		return _mainBackground.getSize();
	}

	public void setMainLocation(Coord c) {
		_mainBackground.setLocation(c);
	}
	
	public Coord getMainLocation() {
		return _mainBackground.getLocation();
	}
	
	public void setHeaderLocation(Coord c) {
		_header.setLocation(c);
	}
	
	public boolean headerContainsPoint(Coord c) {
		return _header.containsPoint(c);
	}

	public void onDraw(Graphics2D g) {
		_header.onDraw(g, _focus); 	
		if (_focus) {
			_mainBackground.onDraw(g);
			_main.onDraw(g);
		}
	}


}
