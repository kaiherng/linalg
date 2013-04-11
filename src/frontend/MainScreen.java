package frontend;

import graphicsengine.Container;
import graphicsengine.Coord;
import graphicsengine.Frame;
import graphicsengine.Rectangle;
import graphicsengine.Screen;
import graphicsengine.Tab;

import java.awt.Graphics2D;

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
	private Rectangle _background;
	
	/**
	 * Screen needs to maintain a reference to application because it needs to tell the application to switch to the next screen when the correct trigger event is fired
	 */
	private Application _application;
	
	public MainScreen(Application application) {
		_application = application;
		
		_background = new Rectangle(new Coord(0,0), new Coord(0,0), Constants.SCREEN_BG_COLOR);
		
		Tab constructTab = new Tab(new Container(), "Construct");
		Tab savedTab = new Tab(new Container(), "Saved");
		Tab solutionTab = new Tab(new Container(), "Solution");
		
		_topLeftFrame = new Frame(new Coord(0,0), new Coord(0,0), constructTab);
		_bottomLeftFrame = new Frame(new Coord(0,0), new Coord(0,0), savedTab);
		_rightFrame = new Frame(new Coord(0,0), new Coord(0,0), solutionTab);
		
				
	}
	

	@Override
	public void onDraw(Graphics2D g) {
		_background.onDraw(g);
		_topLeftFrame.onDraw(g);
		_bottomLeftFrame.onDraw(g);
		_rightFrame.onDraw(g);
	}

	@Override
	public void onDown(int keycode) {
	}

	@Override
	public void onUp(int keycode) {
	}

	@Override
	public void onRepeated(int keycode) {
	}

	@Override
	public void onTyped(int keycode) {
	}

	@Override
	public void onResize(Coord newSize) {
		_background.setSize(newSize);
		
		Coord topLeftLocation = new Coord(Constants.FRAME_X_OFFSET,Constants.FRAME_Y_OFFSET);
		Coord topLeftSize = new Coord((newSize.x - Constants.FRAME_X_OFFSET*3)/2,(newSize.y - Constants.FRAME_Y_OFFSET*3)/2);
		Coord bottomLeftLocation = new Coord(topLeftLocation.x, topLeftLocation.y + topLeftSize.y + Constants.FRAME_Y_OFFSET);
		Coord bottomLeftSize = topLeftSize;
		Coord rightLocation = new Coord(topLeftLocation.x + topLeftSize.x + Constants.FRAME_X_OFFSET, topLeftLocation.y);
		Coord rightSize = new Coord((newSize.x - Constants.FRAME_X_OFFSET*3)/2,newSize.y - Constants.FRAME_Y_OFFSET*2);
		
		_topLeftFrame.setLocation(topLeftLocation);
		_topLeftFrame.setSize(topLeftSize);
		_bottomLeftFrame.setLocation(bottomLeftLocation);
		_bottomLeftFrame.setSize(bottomLeftSize);
		_rightFrame.setLocation(rightLocation);
		_rightFrame.setSize(rightSize);
		
	}

	@Override
	public void onMouseClicked(int clickCount, Coord location) {
	}

	@Override
	public void onMousePressed() {
	}

	@Override
	public void onMouseReleased() {
	}

	@Override
	public void onMouseDragged(Coord location) {
	}

	@Override
	public void onMouseMoved(Coord location) {
	}

	@Override
	public void onMouseWheelForward() {
	}

	@Override
	public void onMouseWheelBackward() {
	}

	@Override
	public void onDragStart(Coord location) {
	}

	@Override
	public void onDragEnd(Coord location) {
	}

}
