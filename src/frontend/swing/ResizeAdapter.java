package frontend.swing;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ResizeAdapter extends MouseAdapter{

	private int _resizing = 0;
    private int prevX = -1;
    private int prevY = -1;
    private JFrame _frame;
    private int _threshold;
    private Rectangle _prevBounds;
    private int[] _resizeSides;

    public ResizeAdapter(Component component, JFrame frame, int threshold, int... resizeSides) {
        super();
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
        _frame = frame;
        _threshold = threshold;
        _resizeSides = resizeSides;
    }
    
    public static Dimension getScreenSize() {
    	return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static Insets getScreenInsets() {
        return Toolkit.getDefaultToolkit().getScreenInsets(new Frame().getGraphicsConfiguration());
    }
  
    @Override
    public void mousePressed(MouseEvent e) {
        if (!e.isMetaDown()) {
        	Dimension frameSize = _frame.getSize();
        	Point frameLocation = _frame.getLocationOnScreen();
        	Point currMouseLocation = e.getLocationOnScreen();
        	for (int resizeSide: _resizeSides) {
        		if (resizeSide == SwingUtilities.SOUTH) {
        			if (currMouseLocation.y >= frameLocation.y + frameSize.height - _threshold && currMouseLocation.y <= frameLocation.y + frameSize.height) {
        				_resizing = SwingUtilities.SOUTH;
        			}
        		}

        		if (resizeSide == SwingUtilities.EAST) {
        			if (currMouseLocation.x >= frameLocation.x + frameSize.width - _threshold && currMouseLocation.x <= frameLocation.x + frameSize.width) {
        				_resizing = SwingUtilities.EAST;
        			}
        		}

        		if (resizeSide == SwingUtilities.WEST) {
        			if (currMouseLocation.x >= frameLocation.x && currMouseLocation.x <= frameLocation.x + _threshold) {
        				_resizing = SwingUtilities.WEST;
        			}
        		}
        	}
        }
        prevX = e.getXOnScreen();
        prevY = e.getYOnScreen();
        _prevBounds = _frame.getBounds();
    }

    @Override
    public void mouseDragged (MouseEvent e) {
    	
        if (prevX != -1 && prevY != -1 && _resizing!=0) {
        	int xInc = e.getXOnScreen() - prevX;
            int yInc = e.getYOnScreen() - prevY;
            
            
            for (int resizeSide: _resizeSides) {

            	if (resizeSide == SwingUtilities.SOUTH && _resizing == SwingUtilities.SOUTH) {
            		if (_prevBounds.y + _prevBounds.height + yInc <= getScreenSize().height - getScreenInsets().bottom) {
            			_frame.setBounds(_prevBounds.x,_prevBounds.y, _prevBounds.width, _prevBounds.height + yInc);
            		}
            	}

            	if (resizeSide == SwingUtilities.EAST && _resizing == SwingUtilities.EAST) {
            		if (_prevBounds.x + _prevBounds.width + xInc <= getScreenSize().width - getScreenInsets().right) {
            			_frame.setBounds(_prevBounds.x,_prevBounds.y, _prevBounds.width + xInc, _prevBounds.height);
            		}
            	}


            	if (resizeSide == SwingUtilities.WEST && _resizing == SwingUtilities.WEST) {
            		if (xInc < 0 && _prevBounds.x + xInc >= 0) {
            			_frame.setBounds(_prevBounds.x + xInc, _prevBounds.y, _prevBounds.width + xInc*-1, _prevBounds.height);
            		}
            		if (xInc > 0 && _frame.getSize().width - xInc >= _frame.getMinimumSize().width) {
            			_frame.setSize(_prevBounds.width - xInc, _prevBounds.height);
            			_frame.setLocation(_prevBounds.x + xInc, _prevBounds.y);
            		}
            	}
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        _resizing = 0;
    }
    
    /**
     * DONT BOTHER - waste of time because of weird mouse release
     */
    @Override
    public void mouseMoved(MouseEvent e) {
//    	if (_resizing!=0) {
//    		return;
//    	} 
//    	Point currMouseLocation = e.getLocationOnScreen();
//    	Dimension frameSize = _frame.getSize();
//    	Point frameLocation = _frame.getLocationOnScreen();
//    	_frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//    	
//    	for (int resizeSide: _resizeSides) {
//
//    		if (resizeSide == SwingUtilities.SOUTH) {
//    			if (currMouseLocation.y >= frameLocation.y + frameSize.height - _threshold && currMouseLocation.y <= frameLocation.y + frameSize.height) {
//    				System.out.println("we are here");
//    				_frame.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
//    			} 
//    		}
//
//    	}
    	
//    	
//    	if (_resizeSide == SwingUtilities.EAST) {
//    		if (currMouseLocation.x >= frameLocation.x + frameSize.width - _threshold && currMouseLocation.x <= frameLocation.x + frameSize.width) {
//    			_frame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
//   		} else {
//    			_frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//    		}
//    	}
//    	
//    	if (_resizeSide == SwingUtilities.WEST) {
//    		if (currMouseLocation.x >= frameLocation.x && currMouseLocation.x <= frameLocation.x + _threshold) {
//    			resizing = true;
//    			_frame.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
//    		} else {
//    			_frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//    		}
//    	}
    	
    }

}
