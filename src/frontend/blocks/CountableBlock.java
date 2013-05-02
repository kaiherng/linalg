package frontend.blocks;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.blocks.Countable;
import backend.blocks.Scalar;
import frontend.panels.Saved;
import frontend.swing.Constants;

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
		this.setLayout(null);
		this.setPreferredSize(Constants.COUNTABLE_BLOCK_SIZE);
		this.addMouseListener(new Click(this));
		_s = saved;
		_name = name;
		_countable = c;
		
		this.setBackground(Constants.COUNTABLE_BLOCK_BG);
		
		_delete = new JLabel("X");
		_delete.setOpaque(false);
		_delete.setBorder(null);
		_delete.setForeground(Constants.COUNTABLE_BLOCK_DELETE_FG);
		this.add(_delete);
		_delete.setBounds(Constants.COUNTABLE_BLOCK_DELETE_BOUNDS);
		
		if(c.getName().equals("MATRIX")){
			_label = new JLabel(name);
			_label.setFont(Constants.COUNTABLE_BLOCK_LABEL_MATRIX_FONT);
		} else if(c.getName().equals("SCALAR")){
			Scalar s = (Scalar) c;
			_label = new JLabel(Double.toString(s.getValue()));
			_label.setFont(Constants.COUNTABLE_BLOCK_LABEL_SCALAR_FONT);
			_name = Double.toString(s.getValue());
		}
		_label.setOpaque(false);
		_label.setBorder(null);
		_label.setForeground(Constants.COUNTABLE_BLOCK_LABEL_FG);
		_label.setHorizontalAlignment(JLabel.CENTER);
		this.add(_label);
		_label.setBounds(Constants.COUNTABLE_BLOCK_LABEL_BOUNDS);
		
		this.setToolTipText(_name);
	}
	
	private class Click extends MouseAdapter{
		
		Component _c;
		
		public Click(Component c){
			_c = c;
		}
		
		public void mouseClicked(MouseEvent e){
			if(_delete.getBounds().contains(e.getPoint())){
				_s.deleteCountable(_name, _c);
			} else {
				_s.addToBar(_countable, _name);
			}
		}
	}
}
