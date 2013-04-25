package frontend.general;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import frontend.shapes.Coord;

/**
 * Adapted with modifications and improvements from http://www.coderanch.com/t/415944/GUI/java/user-ve-undecorated-window-resizable
 * @author kloh
 *
 */
@SuppressWarnings("serial")
public abstract class WorkingFrontEnd extends JFrame implements MouseMotionListener, MouseListener, KeyListener {
	
    private DrawingPanel _drawingPanel;
    
    public WorkingFrontEnd(Dimension initialDimension, Dimension minDimension) {      
    	
    	super("Linear Algebra Calculator");
        addMouseMotionListener(this);   
        addMouseListener(this);      
        addKeyListener(this);
        
    	setMinimumSize(minDimension);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(initialDimension);    
        setUndecorated(false);
        setLocationRelativeTo(null);
        
        _drawingPanel = new DrawingPanel();       
		this.add(_drawingPanel);
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
        _drawingPanel.mouseDragged(e);
    }      
     
    @Override     
    public void mouseMoved(MouseEvent e) {      
		_drawingPanel.mouseMoved(e);
    }        
          
    @Override     
    public void mouseClicked(MouseEvent e) {    
    	_drawingPanel.mouseClicked(e);
    }     

       
    @Override     
    public void mousePressed(MouseEvent e) {      
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