package frontend.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import frontend.containers.Saved;
import frontend.general.Constants;
import frontend.graphicsengine.Algorithms;
import frontend.graphicsengine.Interactable;
import frontend.shapes.Coord;
import frontend.shapes.TextRectangle;

public class MatrixBlock extends TextRectangle implements Interactable {
	
	Coord _location;
	Coord _size;
	String _name;
	TextRectangle _delete;
	Saved _sm;

	public MatrixBlock(String stringToDisplay, Coord location, Saved sm) {
		super(Constants.TEXT_FONTSTYLE, "bold", Constants.SM_TEXT_SIZE, stringToDisplay, Color.white, Constants.SM_COLOR,
				location, Constants.SM_SIZE, Color.black, 1);
		_location = location;
		_size = Constants.SM_SIZE;
		_name = stringToDisplay;
		_delete = new TextRectangle(Constants.TEXT_FONTSTYLE, "bold", 10, "X", Color.white, Constants.SM_COLOR, new Coord(location.x+getSize().x-10, location.y), new Coord(10,10));
		_sm = sm;
	}
	
	public boolean contains(Coord c){
		if (Algorithms.clickWithin(this, c)) {
			return true;
		}
		return false;
	}
	
	public void onDraw(Graphics2D g){
		super.onDraw(g);
		_delete.onDraw(g);
	}
	
	public void setLocation(Coord c){
		super.setLocation(c);
		_delete.setLocation(new Coord(c.x+getSize().x-10, c.y));
	}

	@Override
	public void onMouseClicked(int clickCount, Coord c) {
		// TODO Auto-generated method stub
		if(Algorithms.clickWithin(_delete, c)){
			_sm.deleteMatrix(_name, this);
		} else if(Algorithms.clickWithin(this, c)){
			System.out.println("Add matrix " + _name + " to operation bar");
		}
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
