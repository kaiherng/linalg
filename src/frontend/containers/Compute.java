package frontend.containers;

import java.awt.Graphics2D;

import frontend.graphicsengine.Container;
import frontend.graphicsengine.ScrollPane;
import frontend.shapes.Coord;

public class Compute extends Container {

	private ScrollPane _scrollPane;

	public Compute(Coord location, Coord size) {
		super(location, size);
		
//		Displayable rectangle = new Rectangle(new Coord(0,0), new Coord(200,400), new Color(0,0,0,0));
//		_scrollPane = new ScrollPane(location.plus(0,20), new Coord(size.x,size.y-20), new Coord(0,0), new Coord(size.x,400), new Coord(300,600), rectangle);

	}
	
	@Override
	public void setSize(Coord size) {
		super.setSize(size);
	}
	
	@Override
	public void setLocation(Coord location) {
		super.setLocation(location);
//		_scrollPane.setLocation(location.plus(0,20));
	}

	@Override
	public void onDraw(Graphics2D g) {
//		_scrollPane.onDraw(g);
	}

	@Override
	public void onMouseClicked(int clickCount, Coord c) {	
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
	public void onTyped(char keychar) {
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
//		if (Algorithms.clickWithin(_scrollPane, location)) {
//			_scrollPane.onMouseMoved(location);
//		}
	}

	@Override
	public void onMouseWheelForward(Coord location) {
//		if (Algorithms.clickWithin(_scrollPane, location)) {
//			_scrollPane.onMouseWheelForward(location);
//		}
	}

	@Override
	public void onMouseWheelBackward(Coord location) {
//		if (Algorithms.clickWithin(_scrollPane, location)) {
//			_scrollPane.onMouseWheelBackward(location);
//		}
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
