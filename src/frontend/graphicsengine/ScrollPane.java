package frontend.graphicsengine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import frontend.general.Constants;
import frontend.shapes.Coord;
import frontend.shapes.Polygon;
import frontend.shapes.Rectangle;

public class ScrollPane implements Displayable, Interactable { 
	
	private Coord _UITopLeft; 
	private Coord _UIDimensions;
	private Coord _viewportTopLeft; 
	private Coord _viewportDimensions; //how big the viewport is in terms of displayable coordinates
	private Coord _viewableDimensions; //size of the displayable object
	private Displayable _displayable;
	private Polygon _scrollUp;
	private Polygon _scrollDown;
	private Coord _mouseLocation = new Coord(0,0);
	private Rectangle _background;
	
	public ScrollPane(Coord UITopLeft, Coord UIDimensions, Coord viewportTopLeft, Coord viewportDimensions, Coord viewableDimensions, Displayable displayable) {
		_UIDimensions = UIDimensions;
		_UITopLeft = UITopLeft;
		_viewportTopLeft = viewportTopLeft;
		_viewportDimensions = viewportDimensions;
		_viewableDimensions = viewableDimensions;
		_displayable = displayable;
		_background = new Rectangle(UITopLeft, UIDimensions, Constants.SCROLL_PANE_FILL_COLOR, Constants.SCROLL_PANE_BORDER_WIDTH, Constants.SCROLL_PANE_BORDER_COLOR);
		
		Coord[] coordsUp = new Coord[3];
		coordsUp[0] = new Coord(16,0);
		coordsUp[1] = new Coord(32,10);
		coordsUp[2] = new Coord(0,10);
		_scrollUp = new Polygon(new Coord(_UITopLeft.x + _UIDimensions.x/2 - 16, _UITopLeft.y + 5), Constants.SCROLL_ARROW_FILL_COLOR, Constants.SCROLL_ARROW_BORDER_WIDTH, Constants.SCROLL_ARROW_BORDER_COLOR, coordsUp);
		
		Coord[] coordsDown = new Coord[3];
		coordsDown[0] = new Coord(0,0);
		coordsDown[1] = new Coord(32,0);
		coordsDown[2] = new Coord(16,10);
		_scrollDown = new Polygon(new Coord(_UITopLeft.x + _UIDimensions.x/2 - 16, _UITopLeft.y + _UIDimensions.y - 15), Constants.SCROLL_ARROW_FILL_COLOR, Constants.SCROLL_ARROW_BORDER_WIDTH, Constants.SCROLL_ARROW_BORDER_COLOR, coordsDown);

		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (_scrollUp.containsPoint(_mouseLocation)) move(new Coord(0,-2));
				else if (_scrollDown.containsPoint(_mouseLocation)) move(new Coord(0,2));
			}
		};
		
		new Timer(20, taskPerformer).start();
		
	}
	
	public Coord viewableToScreen(Coord viewableCoordinates) {
		int screenX = viewableCoordinates.x - _viewportTopLeft.x + _UITopLeft.x;
		int screenY = viewableCoordinates.y - _viewportTopLeft.y + _UITopLeft.y;
		return new Coord(screenX, screenY);
	}
	
	public void move(Coord moveVector) {
		Coord oldViewportTopLeft = _viewportTopLeft;
		_viewportTopLeft = new Coord(_viewportTopLeft.x + moveVector.x, _viewportTopLeft.y + moveVector.y);
		System.out.println("moving: " + moveVector);
		if (_viewportTopLeft.y < 0 && moveVector.y < 0) {
			_viewportTopLeft = oldViewportTopLeft;
		}
		if 	(_viewportTopLeft.y + _viewportDimensions.y > _viewableDimensions.y && moveVector.y > 0) {
			_viewportTopLeft = oldViewportTopLeft;
		}
	}
	
	@Override
	public void setLocation(Coord c) {
		_UITopLeft = c;	
		_background.setLocation(c);
		_scrollUp.setLocation(new Coord(_UITopLeft.x + _UIDimensions.x/2 - 10, _UITopLeft.y + 10));
		_scrollDown.setLocation(new Coord(_UITopLeft.x + _UIDimensions.x/2 - 10, _UITopLeft.y + _UIDimensions.y - 30));
	}

	@Override
	public Coord getLocation() {
		return _UITopLeft;
	}
	
	@Override
	public void setSize(Coord UIDimensions) { //the scale is unchanged
		_UIDimensions = UIDimensions;
		_background.setSize(UIDimensions);
		_scrollUp.setLocation(new Coord(_UITopLeft.x + _UIDimensions.x/2 - 10, _UITopLeft.y + 10));
		_scrollDown.setLocation(new Coord(_UITopLeft.x + UIDimensions.x/2 - 10, _UITopLeft.y + UIDimensions.y - 30));
	}
	
	@Override
	public Coord getSize() {
		return _UIDimensions;
	}
	
	@Override
	public void onMouseClicked(int clickCount, Coord clickLocation) {
		
	}
	
	public void onMouseMoved(Coord location) {
		_mouseLocation = location;
	}
	
	public void onMouseWheelForward(Coord location) {
		move(new Coord(0,20));
	}
	
	public void onMouseWheelBackward(Coord location) {
		move(new Coord(0,-20));
	}
	
	public void onDraw(Graphics2D g) {
		_background.onDraw(g);
		g.clipRect(_UITopLeft.x, _UITopLeft.y, _UIDimensions.x, _UIDimensions.y); //sets the clip
		Coord oldSize = _displayable.getSize();
		Coord oldLocation = _displayable.getLocation();
		_displayable.setSize(viewableToScreen(oldSize));
		_displayable.setLocation(viewableToScreen(oldLocation));
		_displayable.onDraw(g);
		_displayable.setSize(oldSize);
		_displayable.setLocation(oldLocation);
		g.setClip(null); //restore the clip
		_scrollUp.onDraw(g);
		_scrollDown.onDraw(g);
	}

	@Override
	public void onDown(int keycode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUp(int keycode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRepeated(int keycode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTyped(char keychar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMousePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseReleased() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseDragged(Coord location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDragStart(Coord location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDragEnd(Coord location) {
		// TODO Auto-generated method stub
		
	}


}
