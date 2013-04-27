package frontend.panels;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.blocks.MatrixBlock;
import frontend.blocks.MatrixBlock;
import frontend.general.Constants;
import frontend.utils.WrapLayout;

import backend.blocks.Matrix;
import backend.blocks.Countable.DisplayType;

public class Saved extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1865676655486491262L;
	Map<String, Matrix> mList;
	//Coord margin = new Coord(10,10);
	int numCols;

	public Saved() {
		WrapLayout layout = new WrapLayout(FlowLayout.LEFT);
		this.setLayout(layout);		
		this.setPreferredSize(this.getSize());
		
		mList = new HashMap<>();
		numCols = (int) this.getWidth() / (Constants.SM_SIZE.x + Constants.SM_MARGIN.x);
		Matrix m = new Matrix(DisplayType.DECIMAL, new Double[][]{{1.0,2.0},{3.0,4.0}});
		addMatrix("A", m);
//		addMatrix("B", m);
//		for(int i = 0 ; i < 50; i++){
//			addMatrix("C", m);
//		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
	}
	
	public void addMatrix(String name, Matrix m){
		mList.put(name, m);
		this.add(new MatrixBlock(name, m, this));
		this.revalidate();
		this.repaint();
	}
	
	public void deleteMatrix(String name, Component mb){
		//mList.remove(name);
		this.remove(mb);
		this.revalidate();
		this.repaint();
	}
	
}
