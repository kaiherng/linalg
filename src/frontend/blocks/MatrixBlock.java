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
	Rectangle2D.Double _r;
	Rectangle2D.Double _delete;
	Saved _s;
	String _name;
	Matrix _matrix;
	JLabel _t, _label;

	public MatrixBlock(String name, Matrix m, Saved saved) {
		this.setLayout(null);
		_r = new Rectangle2D.Double(0, 0, Constants.SM_SIZE.x, Constants.SM_SIZE.y);
		_delete = new Rectangle2D.Double(Constants.SM_SIZE.x - 10, 0, 10, 10);
		this.setPreferredSize(new Dimension(40, 40));
		this.addMouseListener(new Click(this));
		_s = saved;
		_name = name;
		_matrix = m;
		
		_t = new JLabel("X");
		_t.setOpaque(false);
		_t.setBorder(null);
		_t.setForeground(Color.white);
		this.add(_t);
		_t.setBounds(Constants.SM_SIZE.x - 10,0,10,10);
		
		_label = new JLabel(name);
		_label.setOpaque(false);
		_label.setBorder(null);
		_label.setForeground(Color.white);
		_label.setHorizontalAlignment(JLabel.CENTER);
		_label.setFont(new Font("SansSerif", Font.BOLD, 20));
		this.add(_label);
		_label.setBounds(0, 5, 40,30);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.blue);
		g2.fill(_r);
	}
	
	private class Click extends MouseAdapter{
		
		Component _c;
		
		public Click(Component c){
			_c = c;
		}
		
		public void mouseClicked(MouseEvent e){
			if(_t.getBounds().contains(e.getPoint())){
				_s.deleteMatrix(_name, _c);
			}
		}
	}
}
