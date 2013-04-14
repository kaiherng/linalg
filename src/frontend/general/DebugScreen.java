package frontend.general;

import java.awt.Graphics2D;

import frontend.graphicsengine.Screen;
import frontend.shapes.Coord;



/**
 * Formerly called "Window" in the design specs
 * This is a wrapper around everything you see in the program
 * @author Kai
 *
 */
public class DebugScreen implements Screen {
	
	
	/**
	 * Screen needs to maintain a reference to application because it needs to tell the application to switch to the next screen when the correct trigger event is fired
	 */
	private Application _application;
	
	public DebugScreen(Application application) {
		_application = application;

		
	}
	

	@Override
	public void onDraw(Graphics2D g) {

	}

	@Override
	public void onDown(int keycode) {
		System.out.println("keycode: " + keycode);
		if (keycode == 27) {
			_application.setScreen(new MainScreen(_application));
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

		
	}

	/**
	 * Checks if mouseclick location is within locations of frames contained in this screen, and passes click events to the correct frames
	 */
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
