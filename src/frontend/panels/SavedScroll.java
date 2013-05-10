package frontend.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import frontend.swing.CurrentConstants;
import frontend.swing.ScrollPane;

/**
 * Holds a scrollable version of the saved matrices panel
 * @author jypoon
 *
 */

public class SavedScroll extends JPanel {

	private static final long serialVersionUID = 4958588138081813570L;
	private Saved _saved;

	public SavedScroll(Compute c) {
		this.setLayout(new BorderLayout());
		this.setBorder(CurrentConstants.SAVEDSCROLL_BORDER);
		this.setBackground(CurrentConstants.SAVEDSCROLL_BG);
		_saved = new Saved(c);
		ScrollPane scroll = new ScrollPane(_saved);
		scroll.setBorder(CurrentConstants.SAVEDSCROLL_SCROLL_BORDER);
		scroll.setBackground(CurrentConstants.SAVEDSCROLL_SCROLL_BG);
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		this.add(scroll, BorderLayout.CENTER);
	}
	
	public Saved getSavedPanel(){
		return _saved;
	}

}
