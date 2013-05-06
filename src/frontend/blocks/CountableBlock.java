package frontend.blocks;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import backend.blocks.Countable;
import backend.blocks.Scalar;
import frontend.panels.Saved;
import frontend.swing.CurrentConstants;

public class CountableBlock extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6148169854145080375L;
	Saved _s;
	String _name;
	Countable _countable;
	JLabel _delete, _label;
	
	/**
	 * Name is ignored for scalars
	 * @param name
	 * @param c
	 * @param saved
	 */
	public CountableBlock(String name, Countable c, Saved saved) {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(CurrentConstants.COUNTABLE_BLOCK_SIZE);
		this.addMouseListener(new Click(this));
		_s = saved;
		_name = name;
		_countable = c;
		
		this.setBackground(CurrentConstants.COUNTABLE_BLOCK_BG);
		_delete = new JLabel("X");

		_delete.setOpaque(false);
		_delete.setBorder(null);
		_delete.setForeground(CurrentConstants.COUNTABLE_BLOCK_DELETE_FG);
		_delete.setFont(CurrentConstants.COUNTABLE_BLOCK_DELETE_FONT);
		this.add(_delete);
		_delete.setBounds(CurrentConstants.COUNTABLE_BLOCK_DELETE_BOUNDS);
		
		if(c.getName().equals("MATRIX")){
			_label = new JLabel(name);
			if(name.length() > 2){
				_label.setFont(CurrentConstants.COUNTABLE_BLOCK_LABEL_MATRIX_FONT_SMALL);
			} else {
				_label.setFont(CurrentConstants.COUNTABLE_BLOCK_LABEL_MATRIX_FONT_LARGE);
			}
		} else if(c.getName().equals("SCALAR")){
			Scalar s = (Scalar) c;
			Double d = s.getValue();
			BigDecimal bd = new BigDecimal(Double.valueOf(d));
			if(bd.intValue() - bd.doubleValue() == new Double(0)){
				_name = ((Integer) bd.intValue()).toString();
			} else {
				_name = Double.toString(s.getValue());
			}
			_label = new JLabel(_name);
			_label.setFont(CurrentConstants.COUNTABLE_BLOCK_LABEL_SCALAR_FONT);
		}
		_label.setOpaque(false);
		_label.setForeground(CurrentConstants.COUNTABLE_BLOCK_LABEL_FG);
		_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.add(_label);
		_label.setBounds(CurrentConstants.COUNTABLE_BLOCK_LABEL_BOUNDS);
		this.setToolTipText(_name);
		_delete.setVisible(false);
	}
	
	public void setCountable(Countable c){
		_countable = c;
	}
	
	public Countable getCountable(){
		return _countable;
	}
	
	public void setEditing(){
		this.setBackground(CurrentConstants.COUNTABLE_BLOCK_EDITING_BG);
	}
	
	public void doneEditing(){
		this.setBackground(CurrentConstants.COUNTABLE_BLOCK_BG);
	}
	
	private class Click extends MouseAdapter{
		
		CountableBlock _c;
		
		public Click(CountableBlock c){
			_c = c;
		}
		
		@Override
		public void mousePressed(MouseEvent e){
			if(_delete.getBounds().contains(e.getPoint())){
				_s.deleteCountable(_name, _c);
			} else {
				_s.addToBar(_countable, _name, _c);
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			_delete.setVisible(true);
			setBackground(CurrentConstants.COUNTABLE_BLOCK_HOVER_BG);
	
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			_delete.setVisible(false);
			setBackground(CurrentConstants.COUNTABLE_BLOCK_BG);
		}
		
	}
}
