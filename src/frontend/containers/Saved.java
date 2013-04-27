package frontend.containers;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import frontend.blocks.MatrixBlock;
import frontend.general.Constants;
import frontend.graphicsengine.Container;
import frontend.shapes.Coord;

public class Saved extends Container {
	
	Map<String, Matrix> mList;
	List<MatrixBlock> mbList;
	Coord margin = new Coord(10,10);
	int numCols;

	public Saved(Coord location, Coord size) {
		super(location, size);
		mList = new HashMap<>();
		mbList = new CopyOnWriteArrayList<>();
		numCols = (int) (size.x)/(Constants.SM_SIZE.x + Constants.SM_MARGIN.x);
		Matrix m = new Matrix(DisplayType.DECIMAL, new Double[][]{{1.0,2.0},{3.0,4.0}});
		addMatrix("A", m);
		addMatrix("B", m);
	}
	
	@Override
	public void setLocation(Coord c) {
		super.setLocation(c);
	}
	
	@Override
	public void setSize(Coord c) {
		super.setSize(c);
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
