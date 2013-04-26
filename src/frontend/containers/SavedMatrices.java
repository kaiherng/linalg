package frontend.containers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JLabel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import backend.blocks.Matrix;
import backend.blocks.Countable.DisplayType;

import frontend.blocks.MatrixBlock;
import frontend.general.Constants;
import frontend.graphicsengine.Container;
import frontend.shapes.Coord;
import frontend.shapes.Rectangle;
import frontend.shapes.TextRectangle;

public class SavedMatrices extends Container {
	
	Map<String, Matrix> mList;
	List<MatrixBlock> mbList;
	Coord margin = new Coord(10,10);
	int numCols;

	public SavedMatrices(Coord location, Coord size) {
		super(location, size);
		mList = new HashMap<>();
		mbList = new CopyOnWriteArrayList<>();
		numCols = (int) (size.x)/(Constants.SM_SIZE.x + Constants.SM_MARGIN.x);
		Matrix m = new Matrix(DisplayType.DECIMAL, new Double[][]{{1.0,2.0},{3.0,4.0}});
		addMatrix("A", m);
		addMatrix("B", m);
	}

	@Override
	public void onDraw(Graphics2D g) {
		int row = 0;
		int col = 0;
		int count = 0;
		for(MatrixBlock mb : mbList){
			if(count+1 > numCols*(row+1)){
				row++;
				col = 0;
			}
			mb.setLocation(getLocation().plus(Constants.SM_MARGIN.x + (Constants.SM_MARGIN.x + Constants.SM_SIZE.x)*col, Constants.SM_MARGIN.y + (Constants.SM_MARGIN.x + Constants.SM_SIZE.x)*row));
			mb.onDraw(g);
			col++;
			count++;
		}
	}

	@Override
	public void onMouseClicked(int clickCount, Coord c) {
		for(MatrixBlock mb : mbList){
			if(mb.contains(c)){
				mb.onMouseClicked(clickCount, c);
			}
		}
	}
	
	public void addMatrix(String name, Matrix m){
		mList.put(name, m);
		mbList.add(new MatrixBlock(name, this.getLocation(), this));
	}
	
	public void deleteMatrix(String name, MatrixBlock mb){
		mList.remove(name);
		mbList.remove(mb);
	}
}
