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

import backend.blocks.Matrix;

import frontend.general.Constants;
import frontend.panels.Saved;

public class MatrixBlock extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6148169854145080375L;
	Saved _s;
	String _name;
	Matrix _matrix;
	JLabel _delete, _label;

	public MatrixBlock(String name, Matrix m, Saved saved) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(40, 40));
		this.addMouseListener(new Click(this));
		_s = saved;
		_name = name;
		_matrix = m;
		
		this.setBackground(Color.blue);
		
		_delete = new JLabel("X");
		_delete.setOpaque(false);
		_delete.setBorder(null);
		_delete.setForeground(Color.white);
		this.add(_delete);
		_delete.setBounds(Constants.SM_SIZE.x - 10,0,10,10);
		
		_label = new JLabel(name);
		_label.setOpaque(false);
		_label.setBorder(null);
		_label.setForeground(Color.white);
		_label.setHorizontalAlignment(JLabel.CENTER);
		_label.setFont(new Font("SansSerif", Font.BOLD, 20));
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
				_s.addToBar(_matrix, _name);
			}
		}
	}
}
