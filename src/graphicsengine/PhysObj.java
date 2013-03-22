package graphicsengine;

import java.awt.Graphics2D;

import swinglayer.Vec2i;

public interface PhysObj {

	void onResize(Vec2i c);
	void setSize(Vec2i c);
	Vec2i getSize();
	void setLocation(Vec2i c);
	Vec2i getLocation();
	void onDraw(Graphics2D g);
	void onMouseClick(Vec2i c);
	void onMouseDrag(Vec2i start, Vec2i end);
	void onKeyPress(int key);

}
