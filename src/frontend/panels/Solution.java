package frontend.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class Solution extends JPanel {
	
	TeXIcon _ti;
	JLabel _label;

	public Solution(){
		this.setLayout(new BorderLayout());
		TeXFormula formula = new TeXFormula("\\text{Solutions will be displayed here}");
		_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
		_label = new JLabel();
		this.add(_label, BorderLayout.CENTER);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		_ti.paintIcon(_label, g, 5, 0);
	}
	
	public void setTex(String tex){
		TeXFormula formula = new TeXFormula(tex);
		_ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
		//_label.setSize(_ti.getIconWidth(), _ti.getIconHeight());
		//System.out.println(_ti.getIconHeight());
		this.setPreferredSize(new Dimension(_ti.getIconWidth(), _ti.getIconHeight()));
		this.repaint();
	}

}
