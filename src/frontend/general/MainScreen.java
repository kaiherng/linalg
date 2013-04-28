package frontend.general;

import java.awt.Graphics2D;

import frontend.graphicsengine.Algorithms;
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
	private Frame _bottomLeftFrame;
	private Frame _rightFrame;
	private Rectangle _background;
	private Frame _focus;
	
	/**
	 * Screen needs to maintain a reference to application because it needs to tell the application to switch to the next screen when the correct trigger event is fired
	 */
	private Application _application;
	
	public MainScreen(Application application) {
		_application = application;
		Coord dpsize = application.getDPSize();
		_background = new Rectangle(new Coord(0,0), dpsize, Constants.SCREEN_BG_COLOR);
		
		Coord topLeftLocation = new Coord(Constants.FRAME_X_OFFSET,Constants.FRAME_Y_OFFSET);
		Coord topLeftSize = dpsize.minus(topLeftLocation.smult(3)).sdiv(2);
		
		Coord bottomLeftLocation = new Coord(topLeftLocation.x, topLeftLocation.y+topLeftSize.y+Constants.FRAME_Y_OFFSET);
		Coord bottomLeftSize = topLeftSize;
		
		Coord rightLocation = new Coord(topLeftLocation.x + topLeftSize.x + Constants.FRAME_X_OFFSET, Constants.FRAME_Y_OFFSET);
		Coord rightSize = new Coord(topLeftSize.x, dpsize.y-Constants.FRAME_Y_OFFSET*2);
		
//		Container compute = new Compute(topLeftLocation.plus(new Coord(0,Constants.TABHEADER_SIZE.y)), topLeftSize.minus(0, Constants.TABHEADER_SIZE.y));
//		Tab computeTab = new Tab(compute, "Compute", 1, topLeftLocation, topLeftSize);
		
		//Saved saved = new Saved(bottomLeftLocation.plus(new Coord(0, Constants.TABHEADER_SIZE.y)), bottomLeftSize.minus(0, Constants.TABHEADER_SIZE.y));
		//Tab savedTab = new Tab(saved, "Saved", 0, bottomLeftLocation, bottomLeftSize);
		
		//Container construct = new Construct(topLeftLocation.plus(new Coord(0,Constants.TABHEADER_SIZE.y)), topLeftSize.minus(0, Constants.TABHEADER_SIZE.y), saved);
		//Tab constructTab = new Tab(construct, "Construct", 0, topLeftLocation, topLeftSize);
		
//		Container sols = new Solution(rightLocation.plus(new Coord(0,Constants.TABHEADER_SIZE.y)), rightSize.minus(0, Constants.TABHEADER_SIZE.y));
//		Tab solsTab = new Tab(sols, "Solution", 0, rightLocation, rightSize);
		
		//_topLeftFrame = new Frame(topLeftLocation, topLeftSize, constructTab);
//		_topLeftFrame.addTab(computeTab);
//		
//		//_bottomLeftFrame = new Frame(bottomLeftLocation, bottomLeftSize, savedTab);
//		_rightFrame = new Frame(rightLocation, rightSize, solsTab);
		
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
		if (keycode == 27) { //PRESSING ESC WILL BRING YOU TO DEBUGSCREEN
			_application.setScreen(new DebugScreen(_application));
		}
		if(_focus != null){
			if(_focus.equals(_bottomLeftFrame)){
				_bottomLeftFrame.onDown(keycode);
			} else if(_focus.equals(_topLeftFrame)){
				_topLeftFrame.onDown(keycode);
			}
		}
	}

	@Override
	public void onUp(int keycode) {
	}

	@Override
	public void onRepeated(int keycode) {
	}

	@Override
	public void onTyped(char keychar) {
	}

	@Override
	public void onResize(Coord newSize) {
		_background.setSize(newSize);
		
//		//calculates the new locations and sizes for the frames and sets them to these values
		Coord topLeftLocation = new Coord(Constants.FRAME_X_OFFSET,Constants.FRAME_Y_OFFSET);
		Coord topLeftSize = newSize.minus(topLeftLocation.smult(3)).sdiv(2);
		
		Coord bottomLeftLocation = new Coord(topLeftLocation.x, topLeftLocation.y+topLeftSize.y+Constants.FRAME_Y_OFFSET);
		Coord bottomLeftSize = topLeftSize;
		
		Coord rightLocation = new Coord(topLeftLocation.x + topLeftSize.x + Constants.FRAME_X_OFFSET, Constants.FRAME_Y_OFFSET);
		Coord rightSize = new Coord(topLeftSize.x, newSize.y-Constants.FRAME_Y_OFFSET*2);
		
		//need to always call setSize before setLocation, if not things will end up in the wrong position if they're calculated based on size
		_topLeftFrame.setSize(topLeftSize);
		_topLeftFrame.setLocation(topLeftLocation);
		_bottomLeftFrame.setSize(bottomLeftSize);
		_bottomLeftFrame.setLocation(bottomLeftLocation);
		_rightFrame.setSize(rightSize);
		_rightFrame.setLocation(rightLocation);

	}

	/**
	 * Checks if mouseclick location is within locations of frames contained in this screen, and passes click events to the correct frames
	 */
	@Override
	public void onMouseClicked(int clickCount, Coord location) {
		_focus = null;
		if (_topLeftFrame.Contains(location)) {
			_topLeftFrame.onMouseClicked(clickCount, location);
			_focus = _topLeftFrame;
		}
		if (Algorithms.clickWithin(_bottomLeftFrame, location)){
			_bottomLeftFrame.onMouseClicked(clickCount, location);
			_focus = _bottomLeftFrame;
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
		if(Algorithms.clickWithin(_topLeftFrame, location)){
			_topLeftFrame.onMouseDragged(location);
		}
	}

	@Override
	public void onMouseMoved(Coord location) {
		if (Algorithms.clickWithin(_topLeftFrame, location)) {
			_topLeftFrame.onMouseMoved(location);
		}
	}

	@Override
	public void onMouseWheelForward(Coord location) {
		if (Algorithms.clickWithin(_topLeftFrame, location)) {
			_topLeftFrame.onMouseWheelForward(location);
		}
	}

	@Override
	public void onMouseWheelBackward(Coord location) {
		if (Algorithms.clickWithin(_topLeftFrame, location)) {
			_topLeftFrame.onMouseWheelBackward(location);
		}
	}

	@Override
	public void onDragStart(Coord location) {
		if(Algorithms.clickWithin(_topLeftFrame, location)){
			_topLeftFrame.onDragStart(location);
		}
	}

	@Override
	public void onDragEnd(Coord location) {
		if(Algorithms.clickWithin(_topLeftFrame, location)){
			_topLeftFrame.onDragEnd(location);
		}
	}

}
