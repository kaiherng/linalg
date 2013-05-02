package oldfrontend.graphicsengine;

import java.awt.Color;
import java.awt.Graphics2D;

import oldfrontend.general.Constants;
import oldfrontend.shapes.Coord;
import oldfrontend.shapes.FourSidedPolygon;
import oldfrontend.shapes.Text;



/**
 *  Something that looks like this:
 *  _________
 * |  title  \
 * |__________\
 *
 * The location refers to the x (topleft of the bounding box)
 * Size refers to the size of the bounding box
 * 
 * @author Kai
 *
 */
	
public class TabHeader implements Displayable {
	private Text _title;
	private FourSidedPolygon _polygon;
	private Coord _location;
	private Coord _size;
	
	public TabHeader(Coord location, String stringToDisplay, boolean active) {
		Color tabHeaderTextColor = Constants.TABHEADER_TEXT_INACTIVE_COLOR;
		if (active) tabHeaderTextColor = Constants.TABHEADER_TEXT_ACTIVE_COLOR;
		_title = new Text(Constants.TEXT_FONTSTYLE, Constants.TABHEADER_TEXT_STYLE, Constants.TABHEADER_TEXT_SIZE, stringToDisplay, tabHeaderTextColor, location.plus(Constants.TABHEADER_TEXT_OFFSET));
		
		Color tabHeaderFillColor = Constants.TAB_HEADERBG_INACTIVE_COLOR;
		if (active) tabHeaderFillColor = Constants.TAB_HEADERBG_ACTIVE_COLOR;
		_polygon = new FourSidedPolygon(location, Constants.TABHEADER_SIZE, tabHeaderFillColor, Constants.TABHEADER_BORDER_WIDTH, Constants.TABHEADER_BORDER_COLOR, Constants.TABHEADER_TOPLEFT_OFFSET, Constants.TABHEADER_TOPRIGHT_OFFSET, Constants.TABHEADER_BOTTOMLEFT_OFFSET, Constants.TABHEADER_BOTTOMRIGHT_OFFSET);
	}
	
	public void onFocus() {
		_title.setColor(Constants.TABHEADER_TEXT_ACTIVE_COLOR); 
		_polygon.setFillColor(Constants.TAB_HEADERBG_ACTIVE_COLOR);
	}
	
	public void onBlur() {
		_title.setColor(Constants.TABHEADER_TEXT_INACTIVE_COLOR);
		_polygon.setFillColor(Constants.TAB_HEADERBG_INACTIVE_COLOR);
	}
	
	public void setLocation(Coord location) {
		_location = location;
		_title.setLocation(_location.plus(Constants.TABHEADER_TEXT_OFFSET));
		_polygon.setLocation(_location);
	}
	
	public Coord getLocation() {
		return _location;
	}
	
	public void setSize(Coord size) {
		_size = size;
		_polygon.setSize(size);
	}
	
	public Coord getSize() {
		return _size;
	}
	
	public boolean containsPoint(Coord c) {
		return _polygon.containsPoint(c);
	}
	
	@Override
	public void onDraw(Graphics2D g) {
		_polygon.onDraw(g);
		_title.onDraw(g);
	}
	

}
