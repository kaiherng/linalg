package frontend.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import frontend.swing.CurrentConstants;
import frontend.swing.ScrollPane;

public class SolutionScroll extends JPanel {
	
	Solution _sol;
	ScrollPane _scroll;

	public SolutionScroll() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		setBackground(CurrentConstants.SOLUTIONSCROLL_BG);
		setBorder(CurrentConstants.SOLUTIONSCROLL_BORDER);
		_sol = new Solution("");
		
		_scroll = new ScrollPane(_sol);
		
		this.setPreferredSize(new Dimension(100,100));
		this.add(_scroll, BorderLayout.CENTER);
		_scroll.setBackground(CurrentConstants.SOLUTIONSCROLL_SCROLL_BG);
		_scroll.setBorder(CurrentConstants.SOLUTIONSCROLL_SCROLL_BORDER);
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
