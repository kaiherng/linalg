package graphicsengine;

import java.awt.Graphics2D;

import swinglayer.Coord;

public interface PhysObj {

	void onResize(Coord c);
	void setSize(Coord c);
	Coord getSize();
	void setLocation(Coord c);
	Coord getLocation();
	void onDraw(Graphics2D g);
	void onMouseClick(Coord c);
	void onMouseDrag(Coord start, Coord end);
	void onKeyPress(int key);

}
