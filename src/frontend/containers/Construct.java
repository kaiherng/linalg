package frontend.containers;

import java.awt.Graphics2D;

import frontend.general.Constants;
import frontend.graphicsengine.Container;
import frontend.shapes.Coord;
import frontend.shapes.Text;

public class Construct extends Container {
	
	private Text _text;
	
	public Construct(Coord location, Coord size) {
		super(location,size);
		_text = new Text(Constants.TEXT_FONTSTYLE, Constants.CONSTRUCT_INSTRUCTIONS_TEXT_STYLE, Constants.CONSTRUCT_INSTRUCTIONS_TEXT_SIZE, "Drag and drop in the grid below to construct a matrix.", Constants.CONSTRUCT_INSTRUCTIONS_TEXT_COLOR, location.plus(Constants.CONSTRUCT_INSTRUCTIONS_TEXT_OFFSET));
	}
	
	@Override
	public void setSize(Coord size) {
		super.setSize(size);
	}
	
	@Override
	public void setLocation(Coord location) {
		super.setLocation(location);
		_text.setLocation(location.plus(Constants.CONSTRUCT_INSTRUCTIONS_TEXT_OFFSET));
	}

	@Override
	public void onDraw(Graphics2D g) {
		_text.onDraw(g);		
	}
	@Override
	
	public void onMouseClicked(int clickCount, Coord c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDown(int keycode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUp(int keycode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRepeated(int keycode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTyped(int keycode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMousePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseReleased() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseDragged(Coord location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseMoved(Coord location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseWheelForward(Coord location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseWheelBackward(Coord location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDragStart(Coord location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDragEnd(Coord location) {
		// TODO Auto-generated method stub
		
	}

}
