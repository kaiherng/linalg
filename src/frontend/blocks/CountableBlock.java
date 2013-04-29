package frontend.blocks;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import backend.blocks.Countable;
import backend.blocks.Matrix;
import backend.blocks.Scalar;

import frontend.general.Constants;
import frontend.panels.Saved;

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
		this.setPreferredSize(new Dimension(40, 40));
		this.addMouseListener(new Click(this));
		_s = saved;
		_name = name;
		_countable = c;
		
		this.setBackground(Color.blue);
		
		_delete = new JLabel("X");
		_delete.setOpaque(false);
		_delete.setBorder(null);
		_delete.setForeground(Color.white);
		this.add(_delete);
		_delete.setBounds(Constants.SM_SIZE.x - 10,0,10,10);
		
		if(c.getName().equals("MATRIX")){
			_label = new JLabel(name);
			_label.setFont(new Font("SansSerif", Font.BOLD, 20));
		} else if(c.getName().equals("SCALAR")){
			Scalar s = (Scalar) c;
			_label = new JLabel(Double.toString(s.getValue()));
			_label.setFont(new Font("SansSerif", Font.BOLD, 18));
			_name = Double.toString(s.getValue());
		}
		_label.setOpaque(false);
		_label.setBorder(null);
		_label.setForeground(Color.white);
		_label.setHorizontalAlignment(JLabel.CENTER);
		this.add(_label);
		_label.setBounds(0, 5, 40,30);
	}
	
	private class Click extends MouseAdapter{
		
		Component _c;
		
		public Click(Component c){
			_c = c;
		}
		
		public void mouseClicked(MouseEvent e){
			if(_delete.getBounds().contains(e.getPoint())){
				_s.deleteMatrix(_name, _c);
			} else {
				_s.addToBar(_countable, _name);
			}
		}
	}
}
