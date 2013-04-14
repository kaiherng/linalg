package frontend.containers;

import frontend.graphicsengine.Container;
import frontend.shapes.Coord;

public class ConstructConstructor extends Container {

	public ConstructConstructor(int weight) {
		super(true, weight);
		// TODO Auto-generated constructor stub
	}
	
	public void onMouseClicked(int clickCount, Coord c){
		super.onMouseClicked(clickCount, c);
	}

}
