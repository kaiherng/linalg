package frontend.containers;

import java.awt.Graphics2D;

import frontend.graphicsengine.Container;
import frontend.shapes.Coord;

public class Compute extends Container {

	public Compute() {
		super(false, 1);
		_containers.add(new ComputeInstructions(1));
		_containers.add(new ComputeOps(4));
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
