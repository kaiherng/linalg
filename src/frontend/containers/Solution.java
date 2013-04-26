package frontend.containers;

import java.awt.Graphics2D;

import javax.swing.JLabel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import frontend.general.Constants;
import frontend.graphicsengine.Container;
import frontend.shapes.Coord;

public class Solution extends Container {
	
	TeXIcon _ti;
	Coord _location;

	public Solution(Coord location, Coord size) {
		super(location, size);
		TeXFormula formula = new TeXFormula("\\text{Solutions will be displayed here}");
		_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
		_location = location;
	}

	@Override
	public void onDraw(Graphics2D g) {
		// TODO Auto-generated method stub
		_ti.paintIcon(new JLabel(), g, _location.x, _location.y);
	}

	@Override
	public void onMouseClicked(int clickCount, Coord c) {
		// TODO Auto-generated method stub
	}
	
	public void setTex(String tex){
		TeXFormula formula = new TeXFormula(tex);
		_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
	}

}
