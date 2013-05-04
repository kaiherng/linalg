package frontend.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import frontend.swing.CurrentConstants;

public class Solution extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3677189812572651937L;
	TeXIcon _ti;
	JLabel _label;
	SolutionScroll _scroll;
	int _size;
	int _height;
	int _marginX;
	int _marginY;
	boolean _limitSize = false;

	public Solution(String start){
		this.setLayout(new BorderLayout());
		setBackground(CurrentConstants.SOLUTION_BG);
		setBorder(CurrentConstants.SOLUTION_BORDER);
		setTex(start);
		_label = new JLabel();
		_marginX = 10;
		_marginY = 10;
//		_scroll = scroll;
		this.add(_label, BorderLayout.CENTER);
	}
	
	/**
	 * Limit size
	 * @param size
	 */
	public Solution(int height, int width){
		_height = height;
		_limitSize = true;
		this.setLayout(new BorderLayout());
		setBackground(CurrentConstants.SOLUTION_BG);
		setBorder(CurrentConstants.SOLUTION_BORDER);
		setTex("");
		_label = new JLabel();
//		_scroll = scroll;
		this.add(_label, BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		_ti.paintIcon(_label, g, _marginX, _marginY);
	}
	
	public void clear(){
		setTex("");
	}
	
	public void setMargins(int x, int y){
		_marginX = x;
		_marginY = y;
		this.revalidate();
		this.repaint();
	}
	
	public void setTex(String tex){
		TeXFormula formula = new TeXFormula(tex);
		int size = 20;
		_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
		
		if(_limitSize){
			while(_ti.getIconHeight() > _height){
				size -= 1;
				_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, size);
			}
			while(_ti.getIconHeight() < _height){
				size += 1;
				_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, size);
			}
		}
//		_scroll.resetScroll();
		this.setPreferredSize(new Dimension(_ti.getIconWidth()+_marginX, _ti.getIconHeight()+_marginX));
		this.revalidate();
		this.repaint();
	}

}
