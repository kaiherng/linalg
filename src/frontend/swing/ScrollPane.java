package frontend.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * Custom scroll pane class
 * @author kloh
 *
 */
public class ScrollPane extends JScrollPane {

	public ScrollPane(Component view) {
		super(view);
		this.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		this.getVerticalScrollBar().setUI(new CustomScrollBarUI());
	}
	
	private class CustomScrollBarUI extends BasicScrollBarUI {

		@Override
		protected void configureScrollBarColors() {
			thumbColor = CurrentConstants.SCROLLPANE_THUMB_COLOR;
			thumbDarkShadowColor = CurrentConstants.SCROLLPANE_THUMB_DARK_SHADOW_COLOR;
			thumbHighlightColor = CurrentConstants.SCROLLPANE_THUMB_HIGHLIGHT_COLOR;
			thumbLightShadowColor = CurrentConstants.SCROLLPANE_THUMB_LIGHT_SHADOW_COLOR;
			trackColor = CurrentConstants.SCROLLPANE_TRACK_COLOR;
			trackHighlightColor = CurrentConstants.SCROLLPANE_TRACK_HIGHLIGHT_COLOR;
		}

		@Override
		protected JButton createDecreaseButton(int orientation) {
			JButton button = new JButton();
			button.setPreferredSize(new Dimension(0,0));
			button.setMaximumSize(new Dimension(0,0));
			button.setMinimumSize(new Dimension(0,0));
			return button;
		}

		@Override
		protected JButton createIncreaseButton(int orientation) {
			JButton button = new JButton();
			button.setPreferredSize(new Dimension(0,0));
			button.setMaximumSize(new Dimension(0,0));
			button.setMinimumSize(new Dimension(0,0));
			return button;
		}
		
//		@Override
//		protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
//		    //own painting if needed
//		}

//		@Override
//		protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
//		    //own painting if needed
//		}

	}

}
