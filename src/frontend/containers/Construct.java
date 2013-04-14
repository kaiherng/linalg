package frontend.containers;

import frontend.general.Constants;
import frontend.graphicsengine.Container;
import frontend.shapes.Coord;
import frontend.shapes.Text;

import java.awt.Graphics2D;


public class Construct extends Container {
	
	public Construct() {
		super(false, 1); //nothing contains this container, so the weight argument to the superclass doesn't matter.
		_containers.add(new ConstructInstructions(1));
		_containers.add(new ConstructConstructor(7));
	}
	
	@Override
	public void setSize(Coord c) {
		super.setSize(c);
	}
	
	@Override
	public void setLocation(Coord c) {
		super.setLocation(c);
	}
	
	@Override
	public void onDraw(Graphics2D g) {
		super.onDraw(g);
	}

}
