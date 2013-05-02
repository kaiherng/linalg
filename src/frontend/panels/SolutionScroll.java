package frontend.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class SolutionScroll extends JPanel {
	
	Solution _sol;
	JScrollPane _scroll;

	public SolutionScroll() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		_sol = new Solution(this);
		
		_scroll = new JScrollPane(_sol);
		this.setPreferredSize(new Dimension(100,100));
		this.add(_scroll, BorderLayout.CENTER);
		_scroll.getVerticalScrollBar().setUnitIncrement(20);
	}
	
	public Solution getSolPanel(){
		return _sol;
	}
	
	public void resetScroll(){
		_scroll.getVerticalScrollBar().setValue(0);
		_scroll.getHorizontalScrollBar().setValue(0);
	}
}
