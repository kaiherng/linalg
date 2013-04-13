package frontend.graphicsengine;

import java.awt.Color;
import java.awt.Graphics2D;


import frontend.general.Constants;
import frontend.shapes.Coord;
import frontend.shapes.Rectangle;

/**
 * A tab is a displayable object. It contains a tab heading and a container. The container holds the contents of the tab. The tab heading is the "tab" thing that appears on top of the container
 * Tabs are either in focus or out of focus (blurred)
 * @author kloh
 */
public class Tab implements Displayable {
	
	/**
	 * Whether or not this tab is in focus
	 */
	private boolean _focus;
	
	/**
	 * The container that holds the contents of the tab
	 */
	private Container _container;
	
	/**
	 * The background of the tab, excluding the header (ie the container plus the margin)
	 */
	private Rectangle _background;
	
	/**
	 * The header of the tab
	 */
	private TabHeader _header;
	
	/**
	 * The title of the tab
	 */
	private String _title;
	
	/**
	 * The size of the tab, measured from the top left corner of the bounding box (including the header) to the bottom right corner
	 */
	private Coord _size;
	
	private Coord _location;
	
	/**
	 * The priority of the tab. 0 is the leftmost tab, ie the tab that was created first
	 */
	private int _rank;
	
	/**
	 * Constructs a new tab
	 * @param c the container that the tab holds
	 * @param title the title of the tab
	 * @param color the color of the tab
	 */
	public Tab(Container c, String title, int rank) {
		_container = c;
		_background = new Rectangle(new Coord(0,0), new Coord(0,0), Constants.TAB_MAINBG_COLOR, Constants.TABMAIN_BORDER_WIDTH, Constants.TABMAIN_BORDER_COLOR); //just create an empty rectangle for now, the size will be set when the onDraw is called by frame
		_header = new TabHeader(new Coord(0,0), new Coord(Constants.TABHEADER_WIDTH, Constants.TABHEADER_HEIGHT), title);
		_title = title;
		_size = new Coord(0,0); //initialize the size to (0,0) first. We can't pass the size in as an argument because we are constructing the frame before the tab in MainScreen
		_location = new Coord(0,0);
		_rank = rank;
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
		_background.setFillColor(c);
	}
	
	/**
	 * Sets the size of the tab - this sets the size of the tab background, the header and the container
	 * @param c
	 */
	@Override
	public void setSize(Coord c) {
		_size = c;
		_background.setSize(c.minus(0, Constants.TABHEADER_HEIGHT));
		//we don't actually change the size of the header because its fixed, its a constant
		_container.setSize(c.minus(0, Constants.TABHEADER_HEIGHT));
	}

	/**
	 * Gets the size of the tab
	 * @return the size of the bounding box of the tab
	 */
	@Override
	public Coord getSize() {
		return _size;
	}

	/**
	 * Sets the location of the tab - this sets the location of the tab background, the header and the container
	 * @param c
	 */
	@Override
	public void setLocation(Coord c) {
		_location = c;
		_background.setLocation(c.plus(0,Constants.TABHEADER_HEIGHT));
		_header.setLocation(c.plus(_rank*Constants.TABHEADER_WIDTH + Math.min(_rank,1)*-Constants.TABHEADER_OVERLAP,0));
		_container.setLocation(c.plus(0,Constants.TABHEADER_HEIGHT));
	}
	
	/**
	 * Gets the location of the tab
	 * @return the location of the topleft corner of the bounding box of the tab
	 */
	@Override
	public Coord getLocation() {
		return _location;
	}
	
	/**
	 * Checks if the header of the tab contains a point -- to detect if the user has clicked on the header of the tab to possibly switch it
	 * @param c the point we want to check if the header contains
	 * @return
	 */
	public boolean headerContainsPoint(Coord c) {
		return _header.containsPoint(c);
	}
	
	/**
	 * Draws the tab. This means we draw:
	 * (1) the background of the tab
	 * (2) the header of the tab
	 * (3) the container
	 */
	@Override
	public void onDraw(Graphics2D g) {
		_header.onDraw(g, _focus); 	
		if (_focus) {
			_background.onDraw(g);
			_container.onDraw(g);
		}
	}

	@Override
	public void onMouseClicked(int clickCount, Coord c) {
		// TODO Auto-generated method stub
		_container.onMouseClicked(clickCount, c);
	}


}
