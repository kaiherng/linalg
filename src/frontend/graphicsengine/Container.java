package frontend.graphicsengine;

import java.awt.Graphics2D;

import frontend.shapes.Coord;

public abstract class Container implements Displayable, Interactable {
		
	private Coord _location;
	private Coord _size;
	
	public Container(Coord location, Coord size) {
		_location = location;
		_size = size;
	}

	@Override
	public void setSize(Coord c) {
		_size = c;
	}

	@Override
	public Coord getSize() {
		return _size;
	}

	@Override
	public void setLocation(Coord c) {
		_location = c;
	}

	@Override
	public Coord getLocation() {
		return _location;
	}

	@Override
	public abstract void onDraw(Graphics2D g);
	
	@Override
	public abstract void onMouseClicked(int clickCount, Coord c);
	
}
