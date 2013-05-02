package frontend.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
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
		setToolTipText("Double click for fullscreen or drag to move window");
		setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 7)); //for padding around the title elements
		setBackground(Constants.HEADER_BG);
		JLabel title = new JLabel("Linear Algebra Calculator");
		title.setFont(new Font("Dialog", Font.BOLD, 11));
		title.setForeground(Constants.HEADER_TITLE_COLOR);
		add(title,BorderLayout.CENTER);   
		
		JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
		eastPanel.setBackground(new Color(0,0,0,0));
		
		JLabel minButton = createMinButton();
		minButton.setBorder(BorderFactory.createEmptyBorder(0,0,6,7));
		eastPanel.add(minButton);
		
		JLabel maxButton = createMaxButton();
		maxButton.setBorder(BorderFactory.createEmptyBorder(0,0,2,7));
		eastPanel.add(maxButton);
		
		JLabel closeButton = createCloseButton();
		eastPanel.add(closeButton);
		
		add(eastPanel,BorderLayout.EAST);
	}
	
	public JLabel createMinButton() {
		JLabel maxButton = new JLabel("_");
		maxButton.setToolTipText("Minimize");
		maxButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		maxButton.setForeground(Constants.HEADER_TITLE_COLOR);
		maxButton.setFont(new Font("Dialog", Font.BOLD, 14));
		maxButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				_frame.setExtendedState(JFrame.ICONIFIED);
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
		return maxButton;
	}
	
	public JLabel createMaxButton() {
		JLabel maxButton = new JLabel("\u25FB");
		maxButton.setToolTipText("Maximize");
		maxButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		maxButton.setForeground(Constants.HEADER_TITLE_COLOR);
		maxButton.setFont(new Font("Dialog", Font.BOLD, 14));
		maxButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (_frame.getExtendedState() == JFrame.NORMAL) {
					_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				else {
					_frame.setExtendedState(JFrame.NORMAL);
				}
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
		return maxButton;
	}
	
	public JLabel createCloseButton() {
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
		return closeButton;
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
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			if (_frame.getExtendedState() == JFrame.NORMAL) {
				_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
			else {
				_frame.setExtendedState(JFrame.NORMAL);
			}
		}
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}



}
