package frontend.containers;

import java.awt.Color;
import java.awt.Graphics2D;

import frontend.general.Constants;
import frontend.graphicsengine.Container;
import frontend.shapes.Coord;
import frontend.shapes.Rectangle;
import frontend.shapes.Text;
import frontend.shapes.TextRectangleButton;

public class ComputeOps extends Container {
	
	private TextRectangleButton _basic;
	private TextRectangleButton _advanced;
	
	public ComputeOps(int weight) {
		super(false, weight);
		
		_basic = new TextRectangleButton(Constants.TEXT_FONTSTYLE, "plain", 12, "BASIC", Constants.COMPUTE_OPS_HEADER_TEXT_COLOR, Constants.COMPUTE_OPS_HEADER_BG_COLOR, this.getLocation(), new Coord(100, 50),3, Color.BLACK);
		_advanced = new TextRectangleButton(Constants.TEXT_FONTSTYLE, "plain", 12, "ADVANCED", Constants.COMPUTE_OPS_HEADER_TEXT_COLOR, Constants.COMPUTE_OPS_HEADER_BG_COLOR, this.getLocation().plus(new Coord(0, 50)), new Coord(100, 50),3, Color.BLACK);

	}
	
	@Override
	public void setLocation(Coord c) {
		super.setLocation(c);
		_basic.setLocation(c);
		_advanced.setLocation(c.plus(new Coord(0,50)));
	}
	
	@Override
	public void setSize(Coord c) {
		super.setSize(c);
		_basic.setSize(new Coord(100, 50));
		_advanced.setSize(new Coord(100, 50));
	}
	
	@Override
	public void onDraw(Graphics2D g) {
		super.onDraw(g);
		_basic.onDraw(g);
		_advanced.onDraw(g);
	}

}
