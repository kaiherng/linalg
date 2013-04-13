package frontend.containers;

import frontend.general.Constants;
import frontend.graphicsengine.Container;
import frontend.shapes.Coord;
import frontend.shapes.MatrixShape;
import frontend.shapes.Text;

import java.awt.Color;
import java.awt.Graphics2D;

import backend.blocks.Countable;
import backend.blocks.Matrix;

public class Solution extends Container {
	
	MatrixShape _ms;

	public Solution() {
		super(false, 1);
		Matrix m = new Matrix(Countable.DisplayType.WHOLENUMBER, 2, 2);
		m.setValueIndex(1, 0, 0);
		m.setValueIndex(2, 0, 1);
		m.setValueIndex(3, 1, 0);
		m.setValueIndex(4, 1, 1);
		_ms = new MatrixShape(m, 30, new Coord(0,0), this);
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
		_ms.onDraw(g);
	}

}
