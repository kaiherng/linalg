package frontend.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
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
	Map<String, CountableBlock> cList;
	JScrollPane _scroll;
	JPanel _content;
	int _width;
	Compute _compute;
	Construct _construct;
	String _id = "A";
	JTabbedPane _topLeftPane;
	CountableBlock _editing;

	public Saved(Compute c) {
		super();
		WrapLayout wrapLayout = new WrapLayout(FlowLayout.LEFT);
		wrapLayout.setVgap(CurrentConstants.SAVED_WRAPLAYOUT_VGAP);
		wrapLayout.setHgap(CurrentConstants.SAVED_WRAPLAYOUT_HGAP);
		this.setLayout(wrapLayout);
		//this.setPreferredSize(this.getSize());
		this.setBackground(CurrentConstants.SAVED_BG);
		this.setBorder(CurrentConstants.SAVED_BORDER);
		_compute = c;
		
		cList = new HashMap<>();
		Matrix m = new Matrix(DisplayType.WHOLENUMBER, new Double[][]{{1.0,2.0},{3.0,4.0}});
		addCountable("A", m);
		addCountable("B", m);
		addCountable("C", new Scalar(4.0, DisplayType.WHOLENUMBER));
		
		for(int i = 0 ; i < 40; i++){
			addCountable("A", m);
		}
		
	}
	
	public void setConstructPanel(Construct c){
		_construct = c;
	}
	
	public void addCountable(String name, Countable m){
		CountableBlock cblock = new CountableBlock(_id, m, this);
		cList.put(_id.toString(), cblock);
		this.add(cblock);
		_id = increment(_id);
		this.revalidate();
		this.repaint();
	}
	
	public void replaceCountable(String name, Countable m){
		cList.get(name).setCountable(m);
		cList.get(name).doneEditing();
		this.revalidate();
		this.repaint();
	}
	
	public void addToBar(Numerical n, String s, CountableBlock cb){
		if(_topLeftPane.getSelectedIndex() == 1){
			_compute.addToBar(n, s);
		} else {
			if(n.getName().equals("MATRIX")){
				if(_editing != null){
					_editing.doneEditing();
				}
				_construct.editMatrix((Matrix) n, s);
				_editing = cb;
				cb.setEditing();
			}
		}
	}
	
	public void clear(){
		if(_editing != null){
			_editing.doneEditing();
			_editing = null;
		}
	}
	
	public void hideEditing(){
		if(_editing != null){
			_editing.doneEditing();
		}
	}
	
	public void showEditing(){
		if(_editing != null){
			_editing.setEditing();
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
	
	public String increment(String s) {
		if(s.length() == 0){
			return "A";
		}
		Character lastChar = s.charAt(s.length()-1);
		if(lastChar != 'Z'){
			lastChar++;
			s = s.substring(0, s.length()-1) + lastChar.toString(); 
		} else {
			return increment(s.substring(0, s.length()-1)) + "A";
		}
		return s.toString();
	}
}
