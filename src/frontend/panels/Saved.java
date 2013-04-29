package frontend.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import frontend.blocks.CountableBlock;
import frontend.blocks.CountableBlock;
import frontend.general.Constants;
import frontend.utils.WrapLayout;

import backend.blocks.Countable;
import backend.blocks.Matrix;
import backend.blocks.Countable.DisplayType;
import backend.blocks.Numerical;
import backend.blocks.Scalar;

public class Saved extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1865676655486491262L;
	Map<String, Countable> mList;
	JScrollPane _scroll;
	JPanel _content;
	int _width;
	Compute _c;
	Character id = 'A';
	JTabbedPane _topLeftPane;

	public Saved(Compute c) {
		this.setLayout(new WrapLayout(FlowLayout.LEFT));
		this.setPreferredSize(this.getSize());
		
		_c = c;
		
		mList = new HashMap<>();
		Matrix m = new Matrix(DisplayType.DECIMAL, new Double[][]{{1.0,2.0},{3.0,4.0}});
		addMatrix("A", m);
		addMatrix("B", m);
		addMatrix("C", new Scalar(4.0, DisplayType.DECIMAL));
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Matrix m = new Matrix(DisplayType.DECIMAL, new Double[][]{{1.0,2.0},{3.0,4.0}});
		CountableBlock mb = new CountableBlock("A", m, this);
		mb.setLocation(0, 0);
		mb.paint(g);
	}
	
	public void addMatrix(String name, Countable m){
		mList.put(name, m);
		//this.add(new MatrixBlock(name, m, this));
		this.add(new CountableBlock(id.toString(), m, this));
		id++;
		this.revalidate();
		this.repaint();
	}
	
	public void addToBar(Numerical n, String s){
		if(_topLeftPane.getSelectedIndex() == 1){
			_c.addToBar(n, s);
		}
	}
	
	public void deleteMatrix(String name, Component mb){
		//mList.remove(name);
		this.remove(mb);
		this.revalidate();
		this.repaint();
	}
	
	public void setTopLeftPane(JTabbedPane pane){
		_topLeftPane = pane;
	}
	
}
