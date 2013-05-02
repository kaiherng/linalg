package oldfrontend.general;

import java.awt.Color;
import java.awt.Graphics2D;

import oldfrontend.graphicsengine.Screen;
import oldfrontend.shapes.Coord;
import oldfrontend.shapes.TypableTextRectangle;


/**
 * Debug Screen -- test events, components and shapes here :)
 * @author kloh
 *
 */
public class DebugScreen implements Screen {
	
	private Application _application;
//	private ScrollPane _viewport;
	private TypableTextRectangle _typable;
	private TypableTextRectangle _typable2;
	
	public DebugScreen(Application application) {
		_application = application;
//		Displayable textRectangle = new TextRectangle("Times New Roman", "bold", 15, "TextRectangle", Color.BLACK, Color.WHITE, new Coord(0,0), new Coord(100,50), Color.ORANGE, 5);
//		_viewport = new ScrollPane(new Coord(100,100), new Coord(300,300), new Coord(0,0), new Coord(300,100), new Coord(300,600), textRectangle);
		
		_typable = new TypableTextRectangle(Constants.TEXT_FONTSTYLE, "plain", 13, "0", Color.BLACK, Color.WHITE, new Coord(100,100), new Coord(100,30), Color.gray, 3);
		_typable2 = new TypableTextRectangle(Constants.TEXT_FONTSTYLE, "plain", 13, "g", Color.BLACK, Color.WHITE, new Coord(100,100), new Coord(100,30), Color.gray, 3);

	}
	

	@Override
	public void onDraw(Graphics2D g) {
//		_viewport.onDraw(g);
		_typable.onDraw(g);
		_typable2.onDraw(g);
	}

	@Override
	public void onDown(int keycode) {	
		if (keycode == 27) { //PRESSING ESC WILL BRING YOU BACK TO MAINSCREEN
			_application.setScreen(new MainScreen(_application));
		}
	}

	@Override
	public void onUp(int keycode) {
//		System.out.println("onUp, keycode: " + keycode);
	}

	@Override
	public void onRepeated(int keycode) {
//		System.out.println("onRepeated, keycode: " + keycode);
	}

	@Override
	public void onTyped(char keychar) {
		_typable.onTyped(keychar);
	}

	@Override
	public void onResize(Coord newSize) {
//		System.out.println("onResize, newsize: " + newSize);
	}

	@Override
	public void onMouseClicked(int clickCount, Coord location) {
		_typable.onMouseClicked(clickCount, location);
	}

	@Override
	public void onMousePressed() {
//		System.out.println("onMousePressed");
	}

	@Override
	public void onMouseReleased() {
//		System.out.println("onMouseReleased");
	}

	@Override
	public void onMouseDragged(Coord location) {
//		System.out.println("onMouseDragged, location: " + location);
	}

	@Override
	public void onMouseMoved(Coord location) {
//		System.out.println("onMouseMoved, location: " + location);
//		_viewport.onMouseMoved(location);
	}

	@Override
	public void onMouseWheelForward(Coord location) {
//		System.out.println("onMouseWheelForward, location: " + location);
//		if (Algorithms.clickWithin(_viewport, location)) {
//			_viewport.onMouseWheelForward(location);
//		}
	}

	@Override
	public void onMouseWheelBackward(Coord location) {
//		System.out.println("onMouseWheelBackward, location: " + location);
//		if (Algorithms.clickWithin(_viewport, location)) {
//			_viewport.onMouseWheelBackward(location);
//		}
	}

	@Override
	public void onDragStart(Coord location) {
//		System.out.println("onDragStart, location: " + location);
	}

	@Override
	public void onDragEnd(Coord location) {
//		System.out.println("onDragEnd, location: " + location);
	}

}
