package frontend.containers;

import java.awt.Graphics2D;

import javax.swing.JLabel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import frontend.graphicsengine.Container;
import frontend.shapes.Coord;

public class Solution extends Container {
	
	TeXIcon _ti;
	Coord _location;
	
	public Solution(Coord location, Coord size) {
		super(location,size);
		TeXFormula formula = new TeXFormula("\\text{Solutions will be displayed here}");
		_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
		_location = location;
	}
	
	@Override
	public void setSize(Coord size) {
		super.setSize(size);
	}
	
	@Override
	public void setLocation(Coord location) {
		super.setLocation(location);
	}
	
	public void setTex(String tex){
		TeXFormula formula = new TeXFormula(tex);
		_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
	}

	@Override
	public void onDraw(Graphics2D g) {
		_ti.paintIcon(new JLabel(), g, _location.x, _location.y);
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
