package frontend.swing;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * The entire content panel of the JFrame
 * @author kloh
 *
 */
@SuppressWarnings("serial")
public class ContentPane extends JPanel {

	public ContentPane(AppFrame appFrame) {
		super(new BorderLayout());
		setBorder(new LineBorder(Constants.FRAME_BORDER_COLOR, Constants.FRAME_BORDER_WIDTH));
		HeaderPanel headerPanel = new HeaderPanel(appFrame);		
		add(headerPanel, BorderLayout.NORTH);
	}

}
