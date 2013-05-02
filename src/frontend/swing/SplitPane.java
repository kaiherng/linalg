package frontend.swing;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 * The adjustable split pane in our ContentPane
 * @author kloh
 *
 */
@SuppressWarnings("serial")
public class SplitPane extends JSplitPane {

	public SplitPane(JPanel leftPanel, JPanel rightPanel) {
		super(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		
		setResizeWeight(0.5);
		BasicSplitPaneUI ui = (BasicSplitPaneUI) getUI();
		setBorder(new MatteBorder(1,0,0,0,Constants.FRAME_BORDER_COLOR));
		ui.getDivider().setBorder(BorderFactory.createLineBorder(Constants.SPLITPANE_BG, 10));
		ui.getDivider().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
	}

}
