package frontend.swing;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 * Custom scroll pane class
 * @author kloh
 *
 */
public class ScrollPane extends JScrollPane {

	public ScrollPane(Component view) {
		super(view);
	}
}
