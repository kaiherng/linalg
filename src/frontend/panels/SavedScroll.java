package frontend.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SavedScroll extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4958588138081813570L;
	private Saved _saved;

	public SavedScroll(Compute c) {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		_saved = new Saved(c);
//		_saved.setPreferredSize(new Dimension(500,500));
		JScrollPane scroll = new JScrollPane(_saved);
		this.add(scroll, BorderLayout.CENTER);
	}
	
	public Saved getSavedPanel(){
		return _saved;
	}

}
