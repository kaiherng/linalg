package oldfrontend.graphicsengine;

import oldfrontend.shapes.Coord;

public interface Interactable {

	public void onDown(int keycode);
	public void onUp(int keycode);
	public void onRepeated(int keycode);
	public void onTyped(char keychar);
	public void onMouseClicked(int clickCount, Coord c);
	public void onMousePressed();
	public void onMouseReleased();
	public void onMouseDragged(Coord location);
	public void onMouseMoved(Coord location);
	public void onMouseWheelForward(Coord location);
	public void onMouseWheelBackward(Coord location);
	public void onDragStart(Coord location);
	public void onDragEnd(Coord location);
}
