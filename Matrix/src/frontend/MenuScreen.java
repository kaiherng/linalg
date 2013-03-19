package frontend;

import graphicsengine.Application;
import graphicsengine.Rectangle;
import graphicsengine.Screen;
import graphicsengine.Text;

import java.awt.Color;
import java.awt.Graphics2D;

import cs195n.Vec2i;

public class MenuScreen implements Screen {
	
	private Text _file;
	private Text _edit;
	private Text _window;
	private Text _help;
	private Rectangle _bg;
	private Rectangle _top;
	private Rectangle _constructor;
	private Rectangle _saved;
	private Rectangle _output;
	
	public MenuScreen(Application application) {
		_file = new Text("Comic Sans MS", "bold", 14, "File", new Color(43,11,138), new Vec2i(10,10));
		_edit = new Text("Comic Sans MS", "bold", 14, "Edit", new Color(43,11,138), new Vec2i(60,10));
		_window = new Text("Comic Sans MS", "bold", 14, "Window", new Color(43,11,138), new Vec2i(110,10));
		_help = new Text("Comic Sans MS", "bold", 14, "Help", new Color(43,11,138), new Vec2i(190,10));
		_bg = new Rectangle(new Vec2i(0,0), new Vec2i(1000,600), new Color(212,178,191));
		_top = new Rectangle(new Vec2i(0,0), new Vec2i(1000,40), new Color(195,184,230));
		_top.setBorderColor(new Color(58,37,122));
		_top.setStrokeWidth(1);
		_constructor = new Rectangle(new Vec2i(10,50), new Vec2i(400,300), new Color(195,184,230));
		_constructor.setBorderColor(new Color(58,37,122));
		_constructor.setStrokeWidth(1);
		_saved = new Rectangle(new Vec2i(10,365), new Vec2i(400,220), new Color(195,184,230));
		_saved.setBorderColor(new Color(58,37,122));
		_saved.setStrokeWidth(1);
		_output = new Rectangle(new Vec2i(420,50), new Vec2i(570,535), new Color(195,184,230));
		_output.setBorderColor(new Color(58,37,122));
		_output.setStrokeWidth(1);
	}

	@Override
	public void onDraw(Graphics2D g) {
		_bg.draw(g);
		_top.draw(g);
		_constructor.draw(g);
		_saved.draw(g);
		_output.draw(g);
		_file.draw(g);
		_edit.draw(g);
		_window.draw(g);
		_help.draw(g);
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTick(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
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
	public void onTyped(int keycode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResize(Vec2i newSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseClicked(int clickCount, Vec2i location) {
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
	public void onMouseDragged(Vec2i location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseMoved(Vec2i location) {
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
	public void onDragStart(Vec2i location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDragEnd(Vec2i location) {
		// TODO Auto-generated method stub
		
	}
	

}
