package oldfrontend.general;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import oldfrontend.graphicsengine.Screen;
import oldfrontend.shapes.Coord;


@SuppressWarnings("serial")
public class Application extends WorkingFrontEnd {
	
	private java.util.Stack<Screen> _screenStack; 
	private Screen _currentScreen;
	
	//to unite awt events so that they make more sense
	private boolean _keyHeld; 
	private int _keyCode; 
	private int _mouseHeld; 
	
	public Application(Coord initialSize, Coord minimumSize)  {
		super(new Dimension(initialSize.x, initialSize.y), new Dimension(minimumSize.x, minimumSize.y));
		_screenStack = new java.util.Stack<Screen>();
		_keyHeld = false;
		_mouseHeld = 0; //0 for not held, 1 for mouse first clicked, 2 for mouse being held down
		_keyCode = 0;
		_currentScreen = new DebugScreen(this);
		setVisible(true);
	}

	@Override
	protected void onDraw(Graphics2D g) {
		_currentScreen.onDraw(g);
	}

	@Override
	protected void onKeyTyped(KeyEvent e) {
		_currentScreen.onTyped(e.getKeyChar());
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
		//_currentScreen.onMouseMoved(new Coord(e.getX(), e.getY()));
	}
	
	@Override
	protected void onMouseWheelMoved(MouseWheelEvent e, Coord location) {
		if (e.getUnitsToScroll() < 0) {
			_currentScreen.onMouseWheelBackward(location);
		}
		if (e.getUnitsToScroll() > 0) {
			_currentScreen.onMouseWheelForward(location);
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