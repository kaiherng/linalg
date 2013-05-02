package frontend.panels;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import backend.blocks.Countable;
import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.blocks.Numerical;
import backend.blocks.Scalar;
import frontend.blocks.CountableBlock;
import frontend.swing.CurrentConstants;
import frontend.utils.WrapLayout;

public class Saved extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1865676655486491262L;
	Map<String, Countable> cList;
	JScrollPane _scroll;
	JPanel _content;
	int _width;
	Compute _compute;
	Construct _construct;
	Character id = 'A';
	JTabbedPane _topLeftPane;

	public Saved(Compute c) {
		this.setLayout(new WrapLayout(FlowLayout.LEFT));
		this.setPreferredSize(this.getSize());
		this.setBorder(CurrentConstants.SAVED_BORDER);
		this.setBackground(CurrentConstants.SAVED_BG);
		_compute = c;
		
		cList = new HashMap<>();
		Matrix m = new Matrix(DisplayType.DECIMAL, new Double[][]{{1.0,2.0},{3.0,4.0}});
		addCountable("A", m);
		addCountable("B", m);
		addCountable("C", new Scalar(4.0, DisplayType.DECIMAL));
	}
	
	public void setConstructPanel(Construct c){
		_construct = c;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Matrix m = new Matrix(DisplayType.DECIMAL, new Double[][]{{1.0,2.0},{3.0,4.0}});
		CountableBlock mb = new CountableBlock("A", m, this);
		mb.setLocation(0, 0);
		mb.paint(g);
	}
	
	public void addCountable(String name, Countable m){
		cList.put(name, m);
		//this.add(new MatrixBlock(name, m, this));
		this.add(new CountableBlock(id.toString(), m, this));
		id++;
		this.revalidate();
		this.repaint();
	}
	
	public void addToBar(Numerical n, String s){
		if(_topLeftPane.getSelectedIndex() == 1){
			_compute.addToBar(n, s);
		} else {
			if(n.getName().equals("MATRIX")){
				_construct.editMatrix((Matrix) n);
			}
		}
	}
	
	public void deleteCountable(String name, Component mb){
		//mList.remove(name);
		this.remove(mb);
		this.revalidate();
		this.repaint();
	}
	
	public void setTopLeftPane(JTabbedPane pane){
		_topLeftPane = pane;
	}
	
}
