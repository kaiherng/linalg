package frontend.swing;

import java.awt.Color;

import javax.swing.JButton;

/**
 * Tutorial to create custom button UI: http://stackoverflow.com/questions/5751311/creating-a-custom-button-in-java-with-jbutton/5755124#5755124
 * @author kloh
 *
 */
@SuppressWarnings("serial")
public class Button extends JButton {
	
	public Button(String label) {
		super(label);
		setBackground(CurrentConstants.BUTTON_BG);
		setForeground(CurrentConstants.BUTTON_FG);
		setBorder(CurrentConstants.BUTTON_BORDER);
		setFocusable(false);
	}

}
