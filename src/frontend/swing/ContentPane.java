package frontend.swing;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ContentPane extends JPanel {

	public ContentPane(AppFrame appFrame) {
		super(new BorderLayout());
		setBorder(new LineBorder(Constants.FRAME_BORDER_COLOR, 1));
		HeaderPanel headerPanel = new HeaderPanel(appFrame);		
		add(headerPanel, BorderLayout.NORTH);
	}

}
