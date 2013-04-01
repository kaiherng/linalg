package graphicsengine;

import java.awt.Color;
import java.awt.Graphics2D;

import swinglayer.Coord;

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
	public Tab(Container c, String title, Coord location, Coord size, Color color) {
		_main = c;
		_mainBackground = new Rectangle(location.plus(0,20), size.minus(0,20), color);
		_header = new TabHeader(location, new Coord(50,20), title);
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
	public void onResize(Coord c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSize(Coord c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Coord getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocation(Coord c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Coord getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDraw(Graphics2D g) {
		_header.onDraw(g); 	
		if (_focus) {
			_mainBackground.onDraw(g);
			_main.onDraw(g);
		}
	}

	@Override
	public void onMouseClick(Coord c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseDrag(Coord start, Coord end) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(int key) {
		// TODO Auto-generated method stub
		
	}

}
