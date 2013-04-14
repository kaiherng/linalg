package frontend.containers;

import java.awt.Graphics2D;

import frontend.general.Constants;
import frontend.graphicsengine.Container;
import frontend.shapes.Coord;
import frontend.shapes.Text;

public class ConstructInstructions extends Container {
	
	private Text _instruction;

	public ConstructInstructions(int weight) {
		super(true, weight);
		_instruction = new Text(Constants.TEXT_FONTSTYLE, "plain", 12, "Drag and drop to construct a matrix.", Constants.CONTAINER_TEXT_COLOR, new Coord(0,0));
	}
	
	@Override
	
	public void setLocation(Coord c) {
		super.setLocation(c);
		_instruction.setLocation(c.plus(new Coord(5,5)));
	}
	
	@Override
	public void onDraw(Graphics2D g) {
		super.onDraw(g);
		_instruction.onDraw(g);
	}

}
