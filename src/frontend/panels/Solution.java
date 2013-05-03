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

	public Solution(SolutionScroll scroll){
		this.setLayout(new BorderLayout());
//		setBackground(CurrentConstants.SOLUTION_BG);
		setBorder(CurrentConstants.SOLUTION_BORDER);
		TeXFormula formula = new TeXFormula("\\text{Solutions will be displayed here}");
		_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
		_label = new JLabel();
		_scroll = scroll;
		this.add(_label, BorderLayout.CENTER);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		_ti.paintIcon(_label, g, 5, 0);
	}
	
	public void clear(){
		setTex("");
	}
	
	public void setTex(String tex){
		System.out.println(tex);
		TeXFormula formula = new TeXFormula(tex);
		_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
		_scroll.resetScroll();
		this.setPreferredSize(new Dimension(_ti.getIconWidth(), _ti.getIconHeight()));
		this.revalidate();
		this.repaint();
	}

}
