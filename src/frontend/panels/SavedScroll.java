package frontend.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import frontend.swing.CurrentConstants;

public class SavedScroll extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4958588138081813570L;
	private Saved _saved;

	public SavedScroll(Compute c) {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		this.setBorder(CurrentConstants.SAVEDSCROLL_BORDER);
		this.setBackground(CurrentConstants.SAVEDSCROLL_BG);
		_saved = new Saved(c);
//		_saved.setPreferredSize(new Dimension(500,500));
		JScrollPane scroll = new JScrollPane(_saved);
		scroll.setBorder(CurrentConstants.SAVEDSCROLL_SCROLL_BORDER);
		scroll.setBackground(CurrentConstants.SAVEDSCROLL_SCROLL_BG);
		this.add(scroll, BorderLayout.CENTER);
	}
	
	public Saved getSavedPanel(){
		return _saved;
	}

}
