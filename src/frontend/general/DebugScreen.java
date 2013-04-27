package frontend.general;

import java.awt.Color;
import java.awt.Graphics2D;

import frontend.containers.Compute;
import frontend.containers.Construct;
import frontend.graphicsengine.Algorithms;
import frontend.graphicsengine.Container;
import frontend.graphicsengine.Displayable;
import frontend.graphicsengine.Frame;
import frontend.graphicsengine.Screen;
import frontend.graphicsengine.ScrollPane;
import frontend.graphicsengine.Tab;
import frontend.shapes.Coord;
import frontend.shapes.Polygon;
import frontend.shapes.TextRectangle;

/**
 * Debug Screen -- test events, components and shapes here :)
 * @author kloh
 *
 */
public class DebugScreen implements Screen {
	
	private Application _application;
//	private Rectangle _rectangle;
//	private Text _text;
//	private TextRectangle _textRectangle;
//	private TabHeader _tabHeaderActive;
//	private TabHeader _tabHeaderInactive;
//	private FourSidedPolygon _polygon;
//	private Frame _frame;
//	private ScrollPane _viewport;
//	private Polygon _polygon;
	private Frame _topLeftFrame;
	
	public DebugScreen(Application application) {
		_application = application;
//		_rectangle = new Rectangle(new Coord(100,100), new Coord(100,100), Color.BLACK);
//		_text = new Text(Constants.TEXT_FONTSTYLE, "plain", 33, "Text", Color.BLACK, new Coord(200,200));
//		_textRectangle = new TextRectangle("Times New Roman", "bold", 15, "TextRectangle", Color.BLACK, Color.WHITE, new Coord(500,300), new Coord(200,100), Color.ORANGE, 5);
//		_tabHeaderActive = new TabHeader(new Coord(0,0), "TabHeaderActive", true);
//		_tabHeaderInactive = new TabHeader(new Coord(200,0), "TabHeaderInactive", false);
//		_polygon = new FourSidedPolygon(new Coord(100,100), new Coord(100,100), Color.GREEN, 5, Color.BLACK, Constants.TABHEADER_TOPLEFT_OFFSET, Constants.TABHEADER_TOPRIGHT_OFFSET, Constants.TABHEADER_BOTTOMLEFT_OFFSET, Constants.TABHEADER_BOTTOMRIGHT_OFFSET);
		
//		Container compute = new Compute(new Coord(100,100).plus(Constants.TABHEADER_SIZE), new Coord(400,400).minus(Constants.TABHEADER_SIZE));
//		Tab tab1 = new Tab(compute, "Compute", 1, new Coord(100,100), new Coord(400,400));
//		
//		Container construct = new Construct(new Coord(100,100).plus(Constants.TABHEADER_SIZE), new Coord(400,400).minus(Constants.TABHEADER_SIZE));
//		Tab tab2 = new Tab(construct, "Construct", 0, new Coord(100,100), new Coord(400,400));
//		
//		_frame = new Frame(new Coord(100,100), new Coord(400,400), tab2);
//		_frame.addTab(tab1);
		
//		Displayable textRectangle = new TextRectangle("Times New Roman", "bold", 15, "TextRectangle", Color.BLACK, Color.WHITE, new Coord(0,0), new Coord(100,50), Color.ORANGE, 5);
//		_viewport = new ScrollPane(new Coord(100,100), new Coord(300,300), new Coord(0,0), new Coord(300,100), new Coord(300,600), textRectangle);

		Container compute = new Compute(new Coord(100,100), new Coord(300,300));
		Tab computeTab = new Tab(compute, "Compute", 0, new Coord(100,80), new Coord(300,320));
		
		_topLeftFrame = new Frame(new Coord(100,80), new Coord(300,320), computeTab);
		
	}
	

	@Override
	public void onDraw(Graphics2D g) {
//		_rectangle.onDraw(g);
//		_text.onDraw(g);
//		_textRectangle.onDraw(g);
//		_tabHeaderActive.onDraw(g);
//		_tabHeaderInactive.onDraw(g);
//		_polygon.onDraw(g);
//		_frame.onDraw(g);
//		_viewport.onDraw(g);
		_topLeftFrame.onDraw(g);
	}

	@Override
	public void onDown(int keycode) {	
//		System.out.println("onkeydown");
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
	public void onTyped(int keycode) {
//		System.out.println("onTyped, keycode: " + keycode);
	}

	@Override
	public void onResize(Coord newSize) {
//		System.out.println("onResize, newsize: " + newSize);
		_topLeftFrame.setLocation(new Coord(200,200));
	}

	@Override
	public void onMouseClicked(int clickCount, Coord location) {
//		if (Algorithms.clickWithin(_frame, location)) {
//			_frame.onMouseClicked(clickCount, location);
//		}
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
