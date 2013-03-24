package graphicsengine;

import java.awt.Graphics2D;

import swinglayer.*;

/** 
 * 
 * @author Kai
 *
 */
public interface Screen {
	public void onDraw(Graphics2D g);
	public void onTick(long nanosSincePreviousTick);
	public void onDown(int keycode);
	public void onUp(int keycode);
	public void onRepeated(int keycode);
	public void onTyped(int keycode);
	public void onResize(Coord newSize);
	public void onMouseClicked(int clickCount, Coord location);
	public void onMousePressed();
	public void onMouseReleased();
	public void onMouseDragged(Coord location);
	public void onMouseMoved(Coord location);
	public void onMouseWheelForward();
	public void onMouseWheelBackward();
	public void onDragStart(Coord location);
	public void onDragEnd(Coord location);
}
