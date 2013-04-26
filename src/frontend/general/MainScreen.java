package frontend.general;

import java.awt.Graphics2D;

import frontend.containers.Compute;
import frontend.containers.Construct;
import frontend.graphicsengine.Container;
import frontend.graphicsengine.Frame;
import frontend.graphicsengine.Screen;
import frontend.graphicsengine.Tab;
import frontend.shapes.Coord;
import frontend.shapes.Rectangle;



/**
 * Formerly called "Window" in the design specs
 * This is a wrapper around everything you see in the program
 * @author Kai
 *
 */
public class MainScreen implements Screen {
	
	private Frame _topLeftFrame;
//	private Frame _bottomLeftFrame;
//	private Frame _rightFrame;
	private Rectangle _background;
	
	/**
	 * Screen needs to maintain a reference to application because it needs to tell the application to switch to the next screen when the correct trigger event is fired
	 */
	private Application _application;
	
	public MainScreen(Application application) {
		_application = application;
		_background = new Rectangle(new Coord(0,0), new Coord(application.getSize()), Constants.SCREEN_BG_COLOR);
		
		Coord topLeftLocation = new Coord(Constants.FRAME_X_OFFSET,Constants.FRAME_Y_OFFSET);
		Coord topLeftSize = new Coord((application.getSize().width - Constants.FRAME_X_OFFSET*3)/2,(application.getSize().height - Constants.FRAME_Y_OFFSET*3)/2);

		Container compute = new Compute(topLeftLocation.plus(Constants.TABHEADER_SIZE), new Coord(400,400).minus(Constants.TABHEADER_SIZE));
		Tab tab1 = new Tab(compute, "Compute", 1, topLeftLocation, topLeftSize);
		
		Container construct = new Construct(topLeftLocation.plus(Constants.TABHEADER_SIZE), new Coord(400,400).minus(Constants.TABHEADER_SIZE));
		Tab tab2 = new Tab(construct, "Construct", 0, topLeftLocation, topLeftSize);
		
		_topLeftFrame = new Frame(topLeftLocation, topLeftSize, tab2);
		_topLeftFrame.addTab(tab1);
		
		
		
//		Tab savedTab = new Tab(new Saved(), "Saved", 0);
//		_bottomLeftFrame = new Frame(new Coord(0,0), new Coord(0,0), savedTab);
//		
//		Tab solutionTab = new Tab(new Solution(), "Solution", 0);
//		_rightFrame = new Frame(new Coord(0,0), new Coord(0,0), solutionTab);
		
	}
	

	@Override
	public void onDraw(Graphics2D g) {
		_background.onDraw(g);
		_topLeftFrame.onDraw(g);
//		_bottomLeftFrame.onDraw(g);
//		_rightFrame.onDraw(g);
	}

	@Override
	public void onDown(int keycode) {
		if (keycode == 27) {
			_application.setScreen(new DebugScreen(_application));
		}
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
		
//		//calculates the new locations and sizes for the frames and sets them to these values
		Coord topLeftLocation = new Coord(Constants.FRAME_X_OFFSET,Constants.FRAME_Y_OFFSET);
		Coord topLeftSize = new Coord((newSize.x - Constants.FRAME_X_OFFSET*3)/2,(newSize.y - Constants.FRAME_Y_OFFSET*3)/2);
//		Coord bottomLeftLocation = new Coord(topLeftLocation.x, topLeftLocation.y + topLeftSize.y + Constants.FRAME_Y_OFFSET);
//		Coord bottomLeftSize = topLeftSize;
//		Coord rightLocation = new Coord(topLeftLocation.x + topLeftSize.x + Constants.FRAME_X_OFFSET, topLeftLocation.y);
//		Coord rightSize = new Coord((newSize.x - Constants.FRAME_X_OFFSET*3)/2,newSize.y - Constants.FRAME_Y_OFFSET*2);
		
		_topLeftFrame.setLocation(topLeftLocation);
		_topLeftFrame.setSize(topLeftSize);
//		_bottomLeftFrame.setLocation(bottomLeftLocation);
//		_bottomLeftFrame.setSize(bottomLeftSize);
//		_rightFrame.setLocation(rightLocation);
//		_rightFrame.setSize(rightSize);
		
	}

	/**
	 * Checks if mouseclick location is within locations of frames contained in this screen, and passes click events to the correct frames
	 */
	@Override
	public void onMouseClicked(int clickCount, Coord location) {
		if (location.x > _topLeftFrame.getLocation().x && location.x < _topLeftFrame.getLocation().x + _topLeftFrame.getSize().x && location.y > _topLeftFrame.getLocation().y && location.y < _topLeftFrame.getLocation().y + _topLeftFrame.getSize().y) {
			_topLeftFrame.onMouseClicked(clickCount, location);
		}
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
	public void onMouseWheelForward(Coord location) {
	}

	@Override
	public void onMouseWheelBackward(Coord location) {
	}

	@Override
	public void onDragStart(Coord location) {
	}

	@Override
	public void onDragEnd(Coord location) {
	}

}
