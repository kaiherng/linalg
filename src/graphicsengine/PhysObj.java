package graphicsengine;

import java.awt.Graphics2D;

import swinglayer.Coord;

public interface PhysObj {

	void setSize(Coord c);
	Coord getSize();
	void setLocation(Coord c);
	Coord getLocation();
	void onDraw(Graphics2D g);

}
