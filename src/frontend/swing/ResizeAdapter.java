package frontend.swing;

import java.awt.Component;
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
    private int _prevX = -1;
    private int _prevY = -1;
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

        		else if (resizeSide == SwingUtilities.EAST) {
        			if (currMouseLocation.x >= frameLocation.x + frameSize.width - _threshold && currMouseLocation.x <= frameLocation.x + frameSize.width) {
        				_resizing = SwingUtilities.EAST;
        			}
        		}

        		else if (resizeSide == SwingUtilities.WEST) {
        			if (currMouseLocation.x >= frameLocation.x && currMouseLocation.x <= frameLocation.x + _threshold) {
        				_resizing = SwingUtilities.WEST;
        			}
        		}
        		
        		else if (resizeSide == SwingUtilities.SOUTH_EAST) {
        			if (currMouseLocation.x >= frameLocation.x + frameSize.width - _threshold && currMouseLocation.x <= frameLocation.x + frameSize.width
        					&& currMouseLocation.y >= frameLocation.y + frameSize.height - _threshold && currMouseLocation.y <= frameLocation.y + frameSize.height) {
        				_resizing = SwingUtilities.SOUTH_EAST;
        			}
        		}
        		
        		else if (resizeSide == SwingUtilities.SOUTH_WEST) {
        			
        			if (currMouseLocation.y >= frameLocation.y + frameSize.height - _threshold && currMouseLocation.y <= frameLocation.y + frameSize.height
        				&& currMouseLocation.x >= frameLocation.x && currMouseLocation.x <= frameLocation.x + _threshold) {
        				_resizing = SwingUtilities.SOUTH_WEST;
        			}
        			
        		}
        		
        	}
        }
        _prevX = e.getXOnScreen();
        _prevY = e.getYOnScreen();
        _prevBounds = _frame.getBounds();
    }

    @Override
    public void mouseDragged (MouseEvent e) {
    	
        if (_prevX != -1 && _prevY != -1 && _resizing!=0) {
        	int xInc = e.getXOnScreen() - _prevX;
            int yInc = e.getYOnScreen() - _prevY;
            
            
            for (int resizeSide: _resizeSides) {

            	if (resizeSide == SwingUtilities.SOUTH && _resizing == SwingUtilities.SOUTH) {
            		int diff = getScreenSize().height - getScreenInsets().bottom - _prevBounds.height - _prevBounds.y;
            		int diffInHeight = Math.min(_prevBounds.height + yInc, _prevBounds.height + diff);
            		_frame.setBounds(_prevBounds.x,_prevBounds.y, _prevBounds.width, diffInHeight);
            	}

            	else if (resizeSide == SwingUtilities.EAST && _resizing == SwingUtilities.EAST) {
            		int diff = getScreenSize().width - getScreenInsets().right - _prevBounds.width - _prevBounds.x;
            		int diffInWidth = Math.min(_prevBounds.width + xInc, _prevBounds.width + diff);
            		_frame.setBounds(_prevBounds.x,_prevBounds.y, diffInWidth, _prevBounds.height);
            	}

            	else if (resizeSide == SwingUtilities.WEST && _resizing == SwingUtilities.WEST) {
            		if (xInc < 0) {
            			int diffInWidth = Math.max(_prevBounds.width + xInc*-1, getScreenInsets().left);
            			_frame.setBounds(_prevBounds.x + xInc, _prevBounds.y, diffInWidth, _prevBounds.height);            			
            		}
            		
            		if (xInc > 0) {
            			int diffInLocation = Math.min(_prevBounds.x + xInc, _prevBounds.x);
            			_frame.setLocation(diffInLocation, _prevBounds.y);
            			if (_prevBounds.x != diffInLocation) {
            				int diffInWidth = Math.max(_prevBounds.width - xInc, _frame.getMinimumSize().width);
            				_frame.setSize(diffInWidth, _prevBounds.height);
            			}
            		}
            	}
            	
            	else if (resizeSide == SwingUtilities.SOUTH_EAST && _resizing == SwingUtilities.SOUTH_EAST) {
            		int diff1 = getScreenSize().height - getScreenInsets().bottom - _prevBounds.height - _prevBounds.y;
            		int diffInHeight = Math.min(_prevBounds.height + yInc, _prevBounds.height + diff1);
            		int diff2 = getScreenSize().width - getScreenInsets().right - _prevBounds.width - _prevBounds.x;
            		int diffInWidth = Math.min(_prevBounds.width + xInc, _prevBounds.width + diff2);
            		_frame.setBounds(_prevBounds.x,_prevBounds.y, diffInWidth, diffInHeight);
            	}
            	
            	else if (resizeSide == SwingUtilities.SOUTH_WEST && _resizing == SwingUtilities.SOUTH_WEST) {
            		int diff1 = getScreenSize().height - getScreenInsets().bottom - _prevBounds.height - _prevBounds.y;
            		int diffInHeight = Math.min(_prevBounds.height + yInc, _prevBounds.height + diff1);
            		int xBounds = _prevBounds.x;
            		int width = _prevBounds.width;
            		
            		if (xInc < 0) {
            			int diffInWidth = Math.max(_prevBounds.width + xInc*-1, getScreenInsets().left);
            			xBounds += xInc;
            			width = diffInWidth;      			
            		}
            		
            		if (xInc > 0) {
            			int diffInLocation = Math.min(_prevBounds.x + xInc, _prevBounds.x);
            			xBounds = diffInLocation;
            			if (_prevBounds.x != diffInLocation) {
            				int diffInWidth = Math.max(_prevBounds.width - xInc, _frame.getMinimumSize().width);
            				width = diffInWidth;
            			}
            		}
            		_frame.setBounds(xBounds, _prevBounds.y, width, diffInHeight);
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
