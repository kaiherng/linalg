package frontend.swing;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HeaderPanel extends JPanel implements MouseListener, MouseMotionListener {

	private JFrame _frame;
	private Point _point = new Point(0,0);
	private Point _originalTopLeft = new Point(0,0);

	public HeaderPanel(JFrame frame) {
		super(new BorderLayout());
		_frame = frame;
		addMouseListener(this);
		addMouseMotionListener(this);

		setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));

		setBorder(BorderFactory.createEmptyBorder(3, 6, 3, 6)); //for padding around the title elements
		setBackground(Constants.HEADER_BG);
		JLabel title = new JLabel("Linear Algebra Calculator");
		title.setFont(new Font("Dialog", Font.BOLD, 11));
		title.setForeground(Constants.HEADER_TITLE_COLOR);
		add(title,BorderLayout.CENTER);   
		JLabel closeButton = new JLabel("X");
		closeButton.setForeground(Constants.HEADER_TITLE_COLOR);
		closeButton.setToolTipText("Close the program. You'll lose all existing work.");
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}

		});
		add(closeButton,BorderLayout.EAST);

	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(!e.isMetaDown()){  
			_point.x = e.getXOnScreen();  
			_point.y = e.getYOnScreen();  
			_originalTopLeft = _frame.getLocationOnScreen();
		}  
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(!e.isMetaDown()){  
			
			Point p = e.getLocationOnScreen();  
			Point translation = new Point(p.x - _point.x, p.y - _point.y);
			
			_frame.setLocation(_originalTopLeft.x + translation.x, _originalTopLeft.y + translation.y);  
		}  
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}



}
