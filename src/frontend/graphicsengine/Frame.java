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
public class Frame implements Displayable, Interactable {
	
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
			_tabs.get(i).setSize(c);
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
			t.setLocation(c);
		}
	}


	@Override
	public Coord getLocation() {
		return _location;
	}
	
	public boolean Contains(Coord c){
		if (c.x > getLocation().x && c.x < getLocation().x + getSize().x && c.y > getLocation().y && c.y < getLocation().y + getSize().y) {
			return true;
		}
		return false;
	}
	
	@Override
	public void onMouseClicked(int clickCount, Coord location) {
		if (_currTab.headerContainsPoint(location)) { //if we clicked on the current header, do nothing
			return;
		}
		for (int i=0; i<_tabs.size(); i++) { //otherwise, iterate through all the headers from left to right seeing which was clicked
			Tab t = _tabs.get(i);
			if (t.headerContainsPoint(location)) {
				switchTab(t);
				return;
			}
		}
		_currTab.onMouseClicked(clickCount, location); //otherwise, we must've clicked within the tab main body itself, not the header
	}


	@Override
	public void onDown(int keycode) {
		_currTab.onDown(keycode);
	}


	@Override
	public void onUp(int keycode) {
		_currTab.onUp(keycode);
	}


	@Override
	public void onRepeated(int keycode) {
		_currTab.onRepeated(keycode);
	}


	@Override
	public void onTyped(char keychar) {
		_currTab.onTyped(keychar);
	}


	@Override
	public void onMousePressed() {
		_currTab.onMousePressed();
	}


	@Override
	public void onMouseReleased() {
		_currTab.onMouseReleased();	
	}


	@Override
	public void onMouseDragged(Coord location) {
		_currTab.onMouseDragged(location);		
	}


	@Override
	public void onMouseMoved(Coord location) {
		_currTab.onMouseMoved(location);
	}


	@Override
	public void onMouseWheelForward(Coord location) {
		_currTab.onMouseWheelForward(location);
	}


	@Override
	public void onMouseWheelBackward(Coord location) {
		_currTab.onMouseWheelBackward(location);
	}


	@Override
	public void onDragStart(Coord location) {
		_currTab.onDragStart(location);
	}


	@Override
	public void onDragEnd(Coord location) {
		_currTab.onDragEnd(location);
	}

}
