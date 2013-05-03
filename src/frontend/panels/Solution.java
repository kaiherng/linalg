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
	int _size = 20;

	public Solution(){
		this.setLayout(new BorderLayout());
//		setBackground(CurrentConstants.SOLUTION_BG);
		setBorder(CurrentConstants.SOLUTION_BORDER);
		TeXFormula formula = new TeXFormula("\\text{Solutions will be displayed here}");
		_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, _size);
		_label = new JLabel();
//		_scroll = scroll;
		this.add(_label, BorderLayout.CENTER);
	}
	
	public Solution(int size){
		_size = size;
		this.setLayout(new BorderLayout());
//		setBackground(CurrentConstants.SOLUTION_BG);
		setBorder(CurrentConstants.SOLUTION_BORDER);
		TeXFormula formula = new TeXFormula("\\text{Solutions will be displayed here}");
		_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, _size);
		_label = new JLabel();
//		_scroll = scroll;
		this.add(_label, BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		_ti.paintIcon(_label, g, 5, 0);
	}
	
	public void clear(){
		setTex("");
	}
	
	public void setTex(String tex){
		TeXFormula formula = new TeXFormula(tex);
		_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, _size);
//		_scroll.resetScroll();
		this.setPreferredSize(new Dimension(_ti.getIconWidth(), _ti.getIconHeight()));
		this.revalidate();
		this.repaint();
	}

}
