package frontend.containers;

import java.awt.Graphics2D;

import frontend.general.Constants;
import frontend.graphicsengine.Container;
import frontend.shapes.Coord;
import frontend.shapes.TextRectangle;

public class Compute extends Container {
	
	private TextRectangle _textRectangle;

	public Compute(Coord location, Coord size) {
		super(location, size);
		_textRectangle = new TextRectangle(Constants.TEXT_FONTSTYLE, Constants.COMPUTE_OPS_TEXT_STYLE, Constants.COMPUTE_OPS_TEXT_SIZE, "BASIC", Constants.COMPUTE_OPS_TEXT_COLOR, Constants.COMPUTE_OPS_FILL_COLOR, location, new Coord(100,50), Constants.COMPUTE_OPS_BORDER_COLOR, Constants.COMPUTE_OPS_BORDER_WIDTH);
	}
	
	@Override
	public void setSize(Coord size) {
		super.setSize(size);
	}
	
	@Override
	public void setLocation(Coord location) {
		super.setLocation(location);
		_textRectangle.setLocation(location);
	}

	@Override
	public void onDraw(Graphics2D g) {
		_textRectangle.onDraw(g);
	}

	@Override
	public void onMouseClicked(int clickCount, Coord c) {		
	}
	

}
