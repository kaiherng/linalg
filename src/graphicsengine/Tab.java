package graphicsengine;

import java.awt.Color;
import java.awt.Graphics2D;

import frontend.Constants;



public class Tab implements PhysObj {
	
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
	
	/**
	 * Constructs a new tab
	 * @param c the container that the tab holds
	 * @param title the title of the tab
	 * @param color the color of the tab
	 */
	public Tab(Container c, String title) {
		_main = c;
		_mainBackground = new Rectangle(new Coord(0,0), new Coord(0,0), Constants.TAB_MAINBG_COLOR); //just create an empty rectangle for now, the size will be set when the onDraw is called by frame
		_header = new TabHeader(new Coord(0,0), new Coord(Constants.TABHEADER_WIDTH, Constants.TABHEADER_HEIGHT), title);
	}
	
	public void onBlur() {
		_focus = false;
	}
	
	public void onFocus() {
		_focus = true;
	}
	
	public void setColor(Color c) {
		_header.setFillColor(c);
		_mainBackground.setFillColor(c);
	}

	@Override
	public void setSize(Coord c) {
		_mainBackground.setSize(c.minus(0, Constants.TABHEADER_HEIGHT));
	}

	@Override
	public Coord getSize() {
		return null;
	}

	@Override
	public void setLocation(Coord c) {
		_mainBackground.setLocation(c);
	}
	
	
	public void setHeaderLocation(Coord c) {
		_header.setLocation(c);
	}

	@Override
	public Coord getLocation() {
		return null;
	}

	@Override
	public void onDraw(Graphics2D g) {
		_header.onDraw(g, _focus); 	
		if (_focus) {
			_mainBackground.onDraw(g);
			_main.onDraw(g);
		}
	}


}
