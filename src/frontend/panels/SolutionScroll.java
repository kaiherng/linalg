package frontend.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SolutionScroll extends JPanel {
	
	Solution _sol;

	public SolutionScroll() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		_sol = new Solution();
		JScrollPane scroll = new JScrollPane(_sol);
		this.setPreferredSize(new Dimension(100,100));
		this.add(scroll, BorderLayout.CENTER);
	}
	
	public Solution getSolPanel(){
		return _sol;
	}
}
