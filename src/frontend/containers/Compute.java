package frontend.containers;

import java.awt.Color;
import java.awt.Graphics2D;

import frontend.general.Constants;
import frontend.graphicsengine.Container;
import frontend.graphicsengine.Displayable;
import frontend.graphicsengine.ScrollPane;
import frontend.shapes.Coord;
import frontend.shapes.TextRectangle;

public class Compute extends Container {
	
	private TextRectangle _textRectangle;
	private ScrollPane _scrollPane;

	public Compute(Coord location, Coord size) {
		super(location, size);
		_textRectangle = new TextRectangle(Constants.TEXT_FONTSTYLE, Constants.COMPUTE_OPS_TEXT_STYLE, Constants.COMPUTE_OPS_TEXT_SIZE, "BASIC", Constants.COMPUTE_OPS_TEXT_COLOR, Constants.COMPUTE_OPS_FILL_COLOR, location.plus(Constants.COMPUTE_OPS_BORDER_WIDTH,Constants.COMPUTE_OPS_BORDER_WIDTH), new Coord(100,50), Constants.COMPUTE_OPS_BORDER_COLOR, Constants.COMPUTE_OPS_BORDER_WIDTH);
		
		Displayable textRectangle = new TextRectangle("Times New Roman", "bold", 15, "TextRectangle", Color.BLACK, Color.WHITE, new Coord(0,0), new Coord(100,50), Color.ORANGE, 5);
		_scrollPane = new ScrollPane(location.plus(100,100), new Coord(200,100), new Coord(0,0), new Coord(300,100), new Coord(300,600), textRectangle);

	}
	
	@Override
	public void setSize(Coord size) {
		super.setSize(size);
	}
	
	@Override
	public void setLocation(Coord location) {
		super.setLocation(location);
		_textRectangle.setLocation(location.plus(new Coord(Constants.COMPUTE_OPS_BORDER_WIDTH,Constants.COMPUTE_OPS_BORDER_WIDTH)));
		_scrollPane.setLocation(location.plus(100,100));
	}

	@Override
	public void onDraw(Graphics2D g) {
		_textRectangle.onDraw(g);
		_scrollPane.onDraw(g);
	}

	@Override
	public void onMouseClicked(int clickCount, Coord c) {		
	}
	

}
