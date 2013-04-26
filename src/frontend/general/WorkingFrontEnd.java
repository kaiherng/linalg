package frontend.general;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import frontend.shapes.Coord;

/**
 * @author kloh
 * A lightweight Front End till NonWorkingFrontEnd proves up to task...
 */
@SuppressWarnings("serial")
public abstract class WorkingFrontEnd extends JFrame {
	
    private DrawingPanel _drawingPanel;
    
    public WorkingFrontEnd(Dimension initialDimension, Dimension minDimension) {      
    	
    	super("Linear Algebra Calculator");
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
	
	protected abstract void onMouseWheelMoved(MouseWheelEvent e, Coord location);

	protected abstract void onResize(Coord newSize);
  
    
    private class DrawingPanel extends JPanel implements ComponentListener, MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

    	public DrawingPanel() {
    		addComponentListener(this);
            addMouseMotionListener(this);   
            addMouseWheelListener(this);
            addMouseListener(this);      
            addKeyListener(this);
    		ActionListener taskPerformer = new ActionListener() {
    			public void actionPerformed(ActionEvent evt) {
    				repaint();
    			}
    		};
    		
    		new Timer(10, taskPerformer).start();
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

		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println("key typed");
			onKeyTyped(e);
			repaint();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("key pressed");
			onKeyPressed(e);	
			repaint();	
		}

		@Override
		public void keyReleased(KeyEvent e) {
			onKeyReleased(e);
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			Coord location = new Coord(e.getX(),e.getY());
			onMouseWheelMoved(e, location);
			repaint();
		}


    }



}