package oldfrontend.graphicsengine;

import java.awt.Graphics2D;

import oldfrontend.general.Constants;
import oldfrontend.shapes.Coord;
import oldfrontend.shapes.Rectangle;


/**
 * A tab is a displayable object. It contains a tab heading and a container. The container holds the contents of the tab. The tab heading is the "tab" thing that appears on top of the container
 * Tabs are either in focus or out of focus (blurred)
 * @author kloh
 */
public class Tab implements Displayable, Interactable {
	
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
	 * @param location the location of the tab
	 * @param size the size of the tab
	 */
	public Tab(Container c, String title, int rank, Coord location, Coord size) {
		_size = size;
		_location = location;
		_container = c;
		_title = title;
		_rank = rank;
		
		_background = new Rectangle(location, size, Constants.TAB_MAINBG_COLOR, Constants.TABMAIN_BORDER_WIDTH, Constants.TABMAIN_BORDER_COLOR);
		
		_focus = false;
		if (rank == 0) {
			_focus = true;
		}
		_header = new TabHeader(location.plus(_rank*Constants.TABHEADER_SIZE.x + _rank*-Constants.TABHEADER_OVERLAP,0), title, _focus);
		
		setSize(size);
		setLocation(location);
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
	
	/**
	 * Sets the size of the tab - this sets the size of the tab background, the header and the container
	 * @param c
	 */
	@Override
	public void setSize(Coord c) {
		_size = c;
		_background.setSize(c.minus(0, Constants.TABHEADER_SIZE.y));
		_header.setSize(Constants.TABHEADER_SIZE);
		_container.setSize(c.minus(0, Constants.TABHEADER_SIZE.y));
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
		_background.setLocation(c.plus(0,Constants.TABHEADER_SIZE.y));
		_header.setLocation(c.plus(_rank*Constants.TABHEADER_SIZE.x + _rank*-Constants.TABHEADER_OVERLAP,0));
		_container.setLocation(c.plus(0,Constants.TABHEADER_SIZE.y));
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
		_header.onDraw(g); 	
		if (_focus) {
			_background.onDraw(g);
			_container.onDraw(g);
		}
	}

	@Override
	public void onMouseClicked(int clickCount, Coord c) {
		_container.onMouseClicked(clickCount, c);
	}

	@Override
	public void onDown(int keycode) {
		_container.onDown(keycode);
	}

	@Override
	public void onUp(int keycode) {
		_container.onUp(keycode);
	}

	@Override
	public void onRepeated(int keycode) {
		_container.onRepeated(keycode);
	}

	@Override
	public void onTyped(char keychar) {
		_container.onTyped(keychar);
	}

	@Override
	public void onMousePressed() {
		_container.onMousePressed();
	}

	@Override
	public void onMouseReleased() {
		_container.onMouseReleased();
	}

	@Override
	public void onMouseDragged(Coord location) {
		_container.onMouseDragged(location);
	}

	@Override
	public void onMouseMoved(Coord location) {
		_container.onMouseMoved(location);
	}

	@Override
	public void onMouseWheelForward(Coord location) {
		_container.onMouseWheelForward(location);
	}

	@Override
	public void onMouseWheelBackward(Coord location) {
		_container.onMouseWheelBackward(location);
	}

	@Override
	public void onDragStart(Coord location) {
		_container.onDragStart(location);
	}

	@Override
	public void onDragEnd(Coord location) {
		_container.onDragEnd(location);
	}


}
