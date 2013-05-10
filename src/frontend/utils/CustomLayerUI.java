package frontend.utils;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.plaf.LayerUI;

/**
 * Extend to make cool-looking effects when the JDialog is open
 * @author Kai
 *
 */
@SuppressWarnings("serial")
public abstract class CustomLayerUI extends LayerUI<JComponent>{

	private boolean _active = false;
	private final JFrame _frame;
	
	public CustomLayerUI(JFrame frame) {
		_frame = frame;
	}
	
	public JFrame getFrame() {
		return _frame;
	}
	
	public final boolean getActive() {
		return _active;
	}
	
	public final void setActive(boolean active) {
		_active = active;
	}
	
}
