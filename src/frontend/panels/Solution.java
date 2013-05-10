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

/**
 * This panels draws latex strings onto a JPanel. There are helper functions
 * which help restrict the size of the drawn latex icon and also its positioning	
 * @author jypoon
 *
 */

public class Solution extends JPanel {
	
	private static final long serialVersionUID = 3677189812572651937L;
	TeXIcon _ti;
	JLabel _label;
	int _height = -1;
	int _width = -1;
	int _marginX;
	int _marginY;
	boolean _limitSize = false;
	int _upperLimit = -1;

	/**
	 * Create solution panel with no size limitation
	 * @param start latex string to start with
	 */
	public Solution(String start){
		this.setLayout(new BorderLayout());
		setTex(start);
		_label = new JLabel();
		_marginX = 10;
		_marginY = 10;
		this.add(_label, BorderLayout.CENTER);
	}
	
	/**
	 * Limit size of the drawn icon
	 * @param size
	 */
	public Solution(int height, int width){
		_height = height;
		_width = width;
		_limitSize = true;
		this.setLayout(new BorderLayout());
		setTex("");
		_label = new JLabel();
		this.add(_label, BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		_ti.paintIcon(_label, g, _marginX, _marginY);
	}
	
	/**
	 * Clears the solution panel
	 */
	public void clear(){
		setTex("");
	}
	
	/**
	 * Sets the margins and position of where the latex will be drawn
	 * @param x
	 * @param y
	 */
	public void setMargins(int x, int y){
		_marginX = x;
		_marginY = y;
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Sets max size of the latex icon
	 * @param x
	 */
	public void setUpperLimit(int x){
		_upperLimit = x;
	}
	
	/**
	 * This finds the right size to draw the latex icon which satsifies all the
	 * height, width and upper limit requirements and then draws the 
	 * icon and sets the size of the jpanel
	 * @param tex
	 */
	public void setTex(String tex){
		TeXFormula formula = new TeXFormula(tex);
		int size = CurrentConstants.SOLUTION_TEX_SIZE;
		_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, CurrentConstants.SOLUTION_TEX_SIZE);
		if(_limitSize){
			while(_ti.getIconHeight() > _height){
				size -= 1;
				_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, size);
			}
			while(_ti.getIconHeight() < _height){
				size += 1;
				_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, size);
				if(_upperLimit != -1){
					if(size > _upperLimit){
						break;
					}
				}
			}
			if(_width != -1){
				while(_ti.getIconWidth() > _width){
					size -= 1;
					_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, size);
				}
			}
		}
		this.setPreferredSize(new Dimension(_ti.getIconWidth()+_marginX, _ti.getIconHeight()+_marginX));
		this.revalidate();
		this.repaint();
	}

}
