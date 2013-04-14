package frontend.graphicsengine;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


import frontend.general.Constants;
import frontend.shapes.Coord;
import frontend.shapes.Rectangle;

/**
 * A frame is something that contains tabs - it represents a space on the screen that contains tabs, and it is the tab manager for that space
 * @author Kai
 *
 */
public class Frame implements Displayable {
	
	private Coord _location;
	
	private Coord _size;
	
	private Rectangle _background;
	
	/**
	 * A list of all the tabs that are contained in the frame
	 * Tabs are arranged from left to right on the screen, with the oldest tab on the leftmost position
	 * This order is maintained using a list, where the oldest tab is placed into the list first at its head
	 */
	private List<Tab> _tabs;
	
	/**
	 * The current tab that is brought to the front of the other tabs and "in focus"
	 * Note that we do not change the ordering of the tabs from left to right, we merely bring the "in focus" tab to the front so it overlaps other tabs
	 */
	private Tab _currTab;
	
	/**
	 * Creates a new frame
	 * @param location the location of the frame
	 * @param size the size of the frame
	 * @param t the initial tab in the frame -- each frame must contain at least 1 tab!
	 */
	public Frame(Coord location, Coord size, Tab t) {
		_location = location;
		_size = size;
		_tabs = new ArrayList<Tab>();
		_tabs.add(t);
		_currTab = t;
		_currTab.onFocus();
		_background = new Rectangle(location, size, Constants.FRAME_BG_COLOR);
	}

	
	/**
	 * Adds a new tab to the frame, goes automatically to the right of all the existing tabs
	 * @param t the tab to be added
	 */
	public void addTab(Tab t) {
		_tabs.add(t);
	}
	
	/**
	 * Brings the tab the user wants to the front of all the other tabs
	 * @param t the tab the user wants
	 */
	public void switchTab(Tab t) {
		if (!_tabs.contains(t)) {
			throw new IllegalArgumentException("Tab requested does not exist in the frame");
		}
		_currTab.onBlur(); //blurs out the previous currTab
		_currTab = t; //updates the currTab 
		_currTab.onFocus(); //focuses this new currTab
	}
	
	/**
	 * Draws:
	 * (1) the background of the frame
	 * (2) all the tabs contained in the frame, in the correct order
	 * @param g the brush to draw with
	 */
	public void onDraw(Graphics2D g) {
		_background.onDraw(g);
		for (int i=_tabs.size()-1; i>-1; i--) {
			_tabs.get(i).onDraw(g); //draws all the tabs, where the rightmost tab is drawn first
		}
		_currTab.onDraw(g); //draw the current tab over all the other tabs
	}

	/**
	 * Sets the size of the background, and sets the size of all the tabs in this frame
	 */
	@Override
	public void setSize(Coord c) {
		_size = c;
		_background.setSize(c);
		for (int i=0; i<_tabs.size(); i++) {
			_tabs.get(i).setSize(_size.minus(new Coord(Constants.TAB_LEFT_OFFSET+Constants.TAB_RIGHT_OFFSET, Constants.TAB_TOP_OFFSET+Constants.TAB_BOTTOM_OFFSET)));
		}
	}

	/**
	 * Gets the size of this frame
	 * @return the size of the frame
	 */
	@Override
	public Coord getSize() {
		return _size;
	}


	@Override
	public void setLocation(Coord c) {
		_location = c;
		_background.setLocation(c);
		for (int i=0; i<_tabs.size(); i++) {
			Tab t = _tabs.get(i);
			t.setLocation(c.plus(Constants.TAB_LEFT_OFFSET, Constants.TAB_TOP_OFFSET));
		}
	}


	@Override
	public Coord getLocation() {
		return _location;
	}
	
	@Override
	public void onMouseClicked(int clickCount, Coord location) {
		for (int i=0; i<_tabs.size(); i++) {
			Tab t = _tabs.get(i);
			if (t.headerContainsPoint(location)) {
				switchTab(t);
				return;
			}
		}
		_currTab.onMouseClicked(clickCount, location);		
	}

}