package frontend.general;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import frontend.shapes.Coord;

/**
 * Adapted with modifications and improvements from http://www.coderanch.com/t/415944/GUI/java/user-ve-undecorated-window-resizable
 * @author kloh
 *
 */
@SuppressWarnings("serial")
public abstract class NonWorkingFrontEnd extends JFrame implements MouseMotionListener, MouseListener, KeyListener {
	
    private Point _startDrag;      
    private Point _startLoc; 
    private Dimension _precedentDimension;
    private Toolkit _toolkit =  Toolkit.getDefaultToolkit(); 
    private Dimension _minDimension;      
    private int _cursorArea = 5;  
    protected JPanel _viewContainer;
    private DrawingPanel _drawingPanel;
    private JLabel _closeButton;
    
    public NonWorkingFrontEnd(Dimension initialDimension, Dimension minDimension) {      
    	
    	_minDimension = minDimension;
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMouseMotionListener(this);   
        addMouseListener(this);      
        addKeyListener(this);
        setSize(initialDimension);    
        setUndecorated(true);
        setLocationRelativeTo(null);
        
        _viewContainer = (JPanel)this.getContentPane();         
        _viewContainer.setBackground(Constants.JFRAME_BG_COLOR);      
        _viewContainer.setBorder(new LineBorder(Constants.JFRAME_BORDER_COLOR));      
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(3, 6, 3, 6)); //for padding around the title elements
        JLabel title = new JLabel("Linear Algebra Calculator");
        title.setFont(new Font(Constants.JFRAME_HEADER_TEXT_FONTSTYLE, Font.BOLD, 11));
        title.setForeground(Constants.JFRAME_HEADER_TEXT_COLOR);
        
        headerPanel.add(title,BorderLayout.CENTER);   
        headerPanel.setPreferredSize(new Dimension((int)_minDimension.getWidth(), 20));      
        headerPanel.setBackground(Constants.JFRAME_HEADER_BG_COLOR);      
        headerPanel.addMouseListener(this);
        headerPanel.addMouseMotionListener(this);
        
        _closeButton = new JLabel("X");
        _closeButton.setFont(new Font(Constants.JFRAME_HEADER_TEXT_FONTSTYLE, Font.BOLD, 13));
        
//        uncomment for debug of label element locations and sizes
//        Border labelBorder = LineBorder.createGrayLineBorder();
//        title.setBorder(labelBorder);
//        _closeButton.setBorder(labelBorder);
        
        _closeButton.setForeground(Constants.JFRAME_HEADER_TEXT_COLOR);
        
        headerPanel.add(_closeButton,BorderLayout.EAST);

        _viewContainer.add(headerPanel, BorderLayout.NORTH);    

        _drawingPanel = new DrawingPanel();       
		_viewContainer.add(_drawingPanel);
		
    } 
    
	protected abstract void onDraw(Graphics2D g);

	protected abstract void onKeyTyped(KeyEvent e);

	protected abstract void onKeyPressed(KeyEvent e);

	protected abstract void onKeyReleased(KeyEvent e);

	protected abstract void onMouseClicked(MouseEvent e);
	
	protected abstract void onMousePressed(MouseEvent e);

	protected abstract void onMouseReleased(MouseEvent e);

	protected abstract void onMouseDragged(MouseEvent e);

	protected abstract void onMouseMoved(MouseEvent e);

	protected abstract void onResize(Coord newSize);

	public static Point getScreenLocation(MouseEvent e, JFrame frame) {
		Point cursor = e.getPoint();
        Point view_location = frame.getLocationOnScreen();
        return new Point((int) (view_location.getX() + cursor.getX()), (int) (view_location.getY() + cursor.getY()));
	}
    
    @Override     
    public void mouseDragged(MouseEvent e){      
    	moveOrFullResizeFrame(e);
        _drawingPanel.mouseDragged(e);
    }      
     
    @Override     
    public void mouseMoved(MouseEvent e) {      
    	Point cursorLocation = e.getPoint();         
        int xPos = cursorLocation.x;         
        int yPos = cursorLocation.y;      

        if (xPos >= _closeButton.getLocation().x && xPos <= _closeButton.getLocation().x + _closeButton.getSize().width 
        		&& yPos >= _closeButton.getLocation().y && yPos <= _closeButton.getLocation().y + _closeButton.getSize().height) {
        	setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        
        else if( xPos >= _cursorArea && xPos <= getWidth()-_cursorArea && yPos >= getHeight()-_cursorArea)      
            setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));      
        else if (xPos >= getWidth()-_cursorArea && yPos >= _cursorArea && yPos <= getHeight()-_cursorArea)      
            setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));      
        else if (xPos <= _cursorArea && yPos >= _cursorArea && yPos <= getHeight()-_cursorArea)      
            setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));      
        else if (xPos >= _cursorArea && xPos <= getWidth()-_cursorArea && yPos <= _cursorArea)      
            setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));      
        else if (xPos <= _cursorArea && yPos <= _cursorArea)      
            setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));      
        else if (xPos >= getWidth() - _cursorArea && yPos <= _cursorArea)      
            setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));      
        else if (xPos >= getWidth()-_cursorArea && yPos >= getHeight()-_cursorArea)      
            setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));      
        else if (xPos <= _cursorArea && yPos >= getHeight()-_cursorArea)      
            setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));      
        else setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));       
        
		_drawingPanel.mouseMoved(e);
    }        
          
    @Override     
    public void mouseClicked(MouseEvent e) {      
    	Point cursorLocation = e.getPoint();         
        int xPos = cursorLocation.x;         
        int yPos = cursorLocation.y;      
        
        if (xPos >= _closeButton.getLocation().x && xPos <= _closeButton.getLocation().x + _closeButton.getSize().width 
        		&& yPos >= _closeButton.getLocation().y && yPos <= _closeButton.getLocation().y + _closeButton.getSize().height) {
        	System.out.println("Thank you for using Linear Algebra Calculator. See you again soon!");
    		System.exit(0);        
    	}
        
    	Object sourceObject=e.getSource();      
    	if (sourceObject instanceof JPanel) {      
    		if (e.getClickCount() == 2) {     
    			if (getCursor().equals(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)))      
    				headerDoubleClickResize();      
    		}      
    	}      
    	
    }     
    
          
    private void moveOrFullResizeFrame(MouseEvent e) {      
        Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();  

        Object sourceObject=e.getSource();   
        Point current = getScreenLocation(e, this);   
        Point offset = new Point((int)current.getX()- (int)_startDrag.getX(), (int)current.getY()- (int)_startDrag.getY());   
           
        if(sourceObject instanceof JPanel && getCursor().equals(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))) {    
            //moving the frame
        	setLocation((int) (_startLoc.getX() + offset.getX()), (int) (_startLoc.getY() + offset.getY()));
        } else if (!(getCursor().equals(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)) || getCursor().equals(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)))) {         
            //resizing the frame
        	int oldLocationX = (int)getLocation().getX();      
            int oldLocationY = (int)getLocation().getY();      
            int newLocationX = (int) (this._startLoc.getX() + offset.getX());      
            int newLocationY = (int) (this._startLoc.getY() + offset.getY());     
            
           Insets screenInsets = _toolkit.getScreenInsets(this.getGraphicsConfiguration());

        	if (newLocationX < screenInsets.left) {
        		newLocationX = screenInsets.left;
        	}
        	
        	if (newLocationY < screenInsets.top) {
        		newLocationY = screenInsets.top;
        	}
            
            boolean N_Resize = getCursor().equals(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));      
            boolean NE_Resize = getCursor().equals(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));      
            boolean NW_Resize = getCursor().equals(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));      
            boolean E_Resize = getCursor().equals(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));      
            boolean W_Resize = getCursor().equals(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));      
            boolean S_Resize = getCursor().equals(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));      
            boolean SW_Resize = getCursor().equals(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));      
            boolean setLocation = false;      
            
            int newWidth = e.getX();      
            int newHeight = e.getY(); 
           
                  
            if (NE_Resize) {           
                newHeight = getHeight() - (newLocationY - oldLocationY);       
                newLocationX = (int) getLocation().getX();      
                setLocation = true;      
            } else if (E_Resize) {
            	newHeight = getHeight();       
            } else if (S_Resize) {
            	newWidth = getWidth();     
            } else if (N_Resize) {                    	
                newLocationX = (int) getLocation().getX();      
                newWidth = getWidth();      
                newHeight = getHeight() - (newLocationY - oldLocationY);   
                setLocation = true;      
            } else if (NW_Resize) {      
                newWidth = getWidth() - (newLocationX - oldLocationX);      
                newHeight = getHeight() - (newLocationY - oldLocationY);      
                setLocation = true;      
            } else if (NE_Resize) {           
                newHeight = getHeight() - (newLocationY - oldLocationY);      
                newLocationX = (int) getLocation().getX();        
            } else if (SW_Resize) {         
                newWidth = getWidth() - (newLocationX - oldLocationX);      
                newLocationY = (int) getLocation().getY();                     
                setLocation = true;      
            }
            
            if (W_Resize) {       
            	newWidth = getWidth() - (newLocationX - oldLocationX);      
                newLocationY = (int)getLocation().getY();         
                newHeight = getHeight();      
                setLocation = true;      
            }      
                                  
            if (newWidth >= (int)_toolkit.getScreenSize().getWidth() || newWidth <= _minDimension.getWidth()) {      
                newLocationX = oldLocationX;      
                newWidth = getWidth();      
            }      
                  
            if (newHeight >= (int)_toolkit.getScreenSize().getHeight() - 30 || newHeight <= _minDimension.getHeight()) {      
                newLocationY = oldLocationY;      
                newHeight = getHeight();
            }      
                  
            if(newWidth != getWidth() || newHeight != getHeight()) {      
            	this.setSize(newWidth, newHeight);            
                if (setLocation) this.setLocation(newLocationX, newLocationY);
            }      
            
        }      
    }      
           
    private void headerDoubleClickResize() {      
        Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();  

    	if (getWidth() < screen.getWidth() || getHeight() < screen.getHeight()) { //if not yet full screen size
    		this.setSize((int)screen.getWidth(),(int)screen.getHeight());  
    		setLocationRelativeTo(null);
    	} else { //if already full screen size
    		this.setSize(_precedentDimension.width, _precedentDimension.height); 
    		setLocationRelativeTo(null);
    	}   
    }      
       
    @Override     
    public void mousePressed(MouseEvent e) {      
        Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();  

    	this._startDrag = getScreenLocation(e, this);      
    	this._startLoc = this.getLocation();     
    	if (getWidth() < screen.getWidth() || getHeight() < screen.getHeight()) {
    		_precedentDimension = getSize();
    	}
    	_drawingPanel.mousePressed(e);
    }      
          
    @Override     
    public void mouseEntered(MouseEvent e) {}      
     
    @Override     
    public void mouseExited(MouseEvent e) {}      
          
    @Override     
    public void mouseReleased(MouseEvent e) {
    	_drawingPanel.mouseReleased(e);
    }  
    
	@Override
	public void keyTyped(KeyEvent e) {
		onKeyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		onKeyPressed(e);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		onKeyReleased(e);

	}
  
    
    private class DrawingPanel extends JPanel implements ComponentListener {

    	private static final int MILLIS_TO_WAIT_FOR_REPEAT = 5;

    	public DrawingPanel() {
    		addComponentListener(this);
    	}
    	
    	@Override
		public void componentResized(ComponentEvent e) {
			Coord newSize = new Coord(this.getSize().width,this.getSize().height);
			onResize(newSize);
			repaint();
		}
    	
		@Override
		public void componentMoved(ComponentEvent e) {
			repaint();
		}

		@Override
		public void componentShown(ComponentEvent e) {}

		@Override
		public void componentHidden(ComponentEvent e) {}

    	@Override
    	public void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		Graphics2D aBrush = (Graphics2D) g;
    		onDraw(aBrush);
    	}

    	
    	public void mouseDragged(MouseEvent e) {
    		onMouseDragged(e);
    		repaint();
    	}

    	
    	public void mouseMoved(MouseEvent e) {
    		onMouseMoved(e);
    		repaint();
    	}

    	
    	public void mouseClicked(MouseEvent e) {
    		onMouseClicked(e);
    		repaint();
    	}

    	
    	public void mousePressed(MouseEvent e) {
    		onMousePressed(e);
    		repaint();
    	}

    	
    	public void mouseReleased(MouseEvent e) {
    		onMouseReleased(e);
    		repaint();
    	}


    }



}