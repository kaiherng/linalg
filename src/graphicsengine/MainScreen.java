package graphicsengine;

import java.awt.Graphics2D;

import swinglayer.Coord;
import frontend.Application;

/**
 * Formerly called "Window" in the design specs
 * This is a wrapper around everything you see in the program
 * @author Kai
 *
 */
public class MainScreen implements Screen {
	
	private Frame _topLeftFrame;
	private Frame _bottomLeftFrame;
	private Frame _rightFrame;
	
	/**
	 * Screen needs to maintain a reference to application because it needs to tell the application to switch to the next screen when the correct trigger event is fired
	 */
	private Application _application;
	
	public MainScreen(Application application) {
		_application = application;
		
		Tab constructTab = new Tab(new Coord(0,0));
		
		_topLeftFrame = new Frame(new Coord(20,20), new Coord(460,260));
		_bottomLeftFrame = new Frame(new Coord(20,320), new Coord(460,260));
		_rightFrame = new Frame(new Coord(520,20), new Coord(460,560));
	}

	@Override
	public void onDraw(Graphics2D g) {
		_topLeftFrame.onDraw(g);
		_bottomLeftFrame.onDraw(g);
		_rightFrame.onDraw(g);
	}

	@Override
	public void onDown(int keycode) {
		if (keycode == 32) { //if escape is pressed, makes a new main screen and sets the current screen to the new screen
			_application.setScreen(new MainScreen(_application));
		}
		
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
	public void onTyped(int keycode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResize(Coord newSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseClicked(int clickCount, Coord location) {
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
	public void onMouseMoved(Coord location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseWheelForward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseWheelBackward() {
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
