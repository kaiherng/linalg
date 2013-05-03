package frontend.swing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.Border;

/**
 * Tutorial to create custom button UI: http://stackoverflow.com/questions/5751311/creating-a-custom-button-in-java-with-jbutton/5755124#5755124
 * 
 * Note: At this point, the pressed color doesn't work -it's probably overriden somewhere else?
 * 
 * @author kloh
 *
 */
@SuppressWarnings("serial")
public class Button extends JButton {
	
	public Button(final String label, final Color bgColor, final Color fgColor, final Color hoverBgColor, final Color hoverFgColor, final Color pressedBgColor, final Color pressedFgColor, final Border border) {
		super(label);
		setBackground(bgColor);
		setForeground(fgColor);
		setBorder(border);
		setFocusable(false);
		addMouseListener(new MouseAdapter() {
			
			@Override
		    public void mouseEntered(MouseEvent evt) {
		        setBackground(hoverBgColor);
		        setForeground(hoverFgColor);
		    }
			@Override
		    public void mouseExited(MouseEvent evt) {
				setBackground(bgColor);
				setForeground(fgColor);
		    }
		    
			@Override
		    public void mousePressed(MouseEvent evt) {
				setBackground(pressedBgColor);
				setForeground(pressedFgColor);
		    }
			
			@Override
		    public void mouseReleased(MouseEvent evt) {
				setBackground(bgColor);
				setForeground(fgColor);
		    }
		});
	}
	


}
