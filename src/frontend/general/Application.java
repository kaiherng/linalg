package frontend.general;

import frontend.graphicsengine.FrontEnd;
import frontend.graphicsengine.Screen;
import frontend.shapes.Coord;

import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;



public class Application extends FrontEnd {
	
	private java.util.Stack<Screen> _screenStack; 
	private Screen _currentScreen;
	
	//to unite awt events so that they make more sense
	private boolean _keyHeld; 
	private int _keyCode; 
	private int _mouseHeld; 
	
	public Application(Coord initialSize)  {
		super(initialSize);
		_screenStack = new java.util.Stack<Screen>();
		_keyHeld = false;
		_mouseHeld = 0; //0 for not held, 1 for mouse first clicked, 2 for mouse being held down
		_keyCode = 0;
		_currentScreen = new MainScreen(this);
	}

	@Override
	protected void onDraw(Graphics2D g) {
		_currentScreen.onDraw(g);
	}

	@Override
	protected void onKeyTyped(KeyEvent e) {
		_currentScreen.onTyped(e.getKeyCode());
	}

	@Override
	protected void onKeyPressed(KeyEvent e) {
		if (!_keyHeld) {
			_currentScreen.onDown(e.getKeyCode());
			_keyCode = e.getKeyCode();
		}
		_keyHeld = true;
		if (e.getKeyCode() == _keyCode) {
			_currentScreen.onRepeated(e.getKeyCode());
		}
	}

	@Override
	protected void onKeyReleased(KeyEvent e) {
		if (_keyHeld) {
			_currentScreen.onUp(e.getKeyCode());
		}
		_keyHeld = false;
		_keyCode = e.getKeyCode();
	}

	@Override
	protected void onMouseClicked(MouseEvent e) {
		int clickCount = e.getClickCount();
		int xLocation = e.getX();
		int yLocation = e.getY();
		_currentScreen.onMouseClicked(clickCount, new Coord(xLocation, yLocation));
	}

	@Override
	protected void onMousePressed(MouseEvent e) {
		_currentScreen.onMousePressed();
		_mouseHeld = 1;
	}

	@Override
	protected void onMouseReleased(MouseEvent e) {
		_currentScreen.onMouseReleased();
		if (_mouseHeld == 2) {
			_mouseHeld = 0;
			_currentScreen.onDragEnd(new Coord(e.getX(), e.getY()));
		}
	}

	@Override
	protected void onMouseDragged(MouseEvent e) {
		if (_mouseHeld == 1) {
			_currentScreen.onDragStart(new Coord(e.getX(), e.getY()));
			_mouseHeld = 2;
		}
		if (_mouseHeld == 0) {
			_mouseHeld = 1;
		}
		_currentScreen.onMouseDragged(new Coord(e.getX(), e.getY()));
		_currentScreen.onMouseMoved(new Coord(e.getX(), e.getY()));
	}

	@Override
	protected void onMouseMoved(MouseEvent e) {
		_currentScreen.onMouseMoved(new Coord(e.getX(), e.getY()));
	}

	@Override
	protected void onMouseWheelMoved(MouseWheelEvent e) {
		if (e.getUnitsToScroll() < 0) {
			_currentScreen.onMouseWheelForward();
		}
		if (e.getUnitsToScroll() > 0) {
			_currentScreen.onMouseWheelBackward();
		}
		
	}

	@Override
	protected void onResize(Coord newSize) {
		if (_currentScreen != null) {
			_currentScreen.onResize(newSize);
		}
	}
	
	public void setScreen(Screen newScreen) {
		_currentScreen = _screenStack.push(newScreen);
	}
	
	public Screen popScreen() {
		return _currentScreen = _screenStack.pop();
	}

}
