package frontend.swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.Border;

/**
 * Custom button class
 * @author kloh
 *
 */
@SuppressWarnings("serial")
public class Button extends JButton {
	
	public Button(final String label, final Color bgColor, final Color fgColor, final Color hoverBgColor, final Color hoverFgColor, final Color pressedBgColor, final Color pressedFgColor, final Border border) {
		super(label);
		setFont(CurrentConstants.BUTTON_FONT);
		setBackground(bgColor);
		setForeground(fgColor);
		setBorder(border);
		setFocusable(false);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				
			}
		});
		
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
