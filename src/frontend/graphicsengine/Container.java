package frontend.graphicsengine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import frontend.general.Constants;
import frontend.shapes.Coord;
import frontend.shapes.Rectangle;


public abstract class Container implements Displayable{
		
	private Coord _location;
	
	private Coord _size;
	
	/**
	 * The weight of this container, relative to sibling containers, in the container that contains it
	 */
	public final int weight;
	
	/**
	 * Whether or not the component containers in this container are arranged from left to right
	 * If true, yes, from left to right (ie a horizontal arrangement)
	 * Else, it is from up to down (a vertical arrangement)
	 */
	private boolean _leftToRight;
	
	/**
	 * Containers that are contained in this container
	 */
	protected List<Container> _containers;
	
	/**
	 * For debug purposes
	 */
	private Rectangle _background;
	
	public Container(boolean leftToRight, int weight) {
		this.weight = weight;
		_leftToRight = leftToRight;
		_containers = new ArrayList<>();
		_size = new Coord(0,0); //doesn't matter what we initialize it to, because it'll be changed when the initialSetup is called
		_location = new Coord(0,0); //doesn't matter what we initialize it to, because it'll be changed when the initialSetup is called
		_background = new Rectangle(_location, _size, Constants.TAB_MAINBG_COLOR, 1, Color.BLACK);
	}

	/**
	 * Sets the size of this container. Also sets the size of each subcontainer based on its weight relative to the cumulative total weights of all subcontainers
	 * @param c
	 */
	@Override
	public void setSize(Coord c) {
		_size = c;
		_background.setSize(c);
		if (_containers.size() > 0) {
			int totalWeight = 0;
			for (int i=0; i<_containers.size(); i++) {
				totalWeight = totalWeight + _containers.get(i).weight;
			}
			if (_leftToRight) {
				for (int i=0; i<_containers.size(); i++) {
					_containers.get(i).setSize(new Coord((_size.x/totalWeight)*_containers.get(i).weight, _size.y));
				}
			}
			else {
				for (int i=0; i<_containers.size(); i++) {
					_containers.get(i).setSize(new Coord(_size.x, (_size.y/totalWeight)*_containers.get(i).weight));
				}
			}
		}
	}

	/**
	 * Gets the size of this container
	 * @return the size of this container
	 */
	@Override
	public Coord getSize() {
		return _size;
	}

	/**
	 * Sets the location of this container. Also sets the location of each sub container based on their weight.
	 * @param c the location of this container
	 */
	@Override
	public void setLocation(Coord c) {
		_location = c;
		_background.setLocation(c);
		if (_containers.size() > 0) {
			int totalWeight = 0;
			for (int i=0; i<_containers.size(); i++) {
				totalWeight = totalWeight + _containers.get(i).weight;
			}
			
			_containers.get(0).setLocation(new Coord(_location.x, _location.y));
			
			if (_leftToRight) {
				int weightedIncrement = _size.x/totalWeight;
				for (int i=1; i<_containers.size(); i++) {
					_containers.get(i).setLocation(new Coord(_location.x + weightedIncrement*_containers.get(i-1).weight, _location.y));
				}
			}
			else {
				int weightedIncrement = _size.y/totalWeight;
				for (int i=1; i<_containers.size(); i++) {
					_containers.get(i).setLocation(new Coord(_location.x, _location.y  + weightedIncrement*_containers.get(i-1).weight));
				}
			}
		}
	}

	@Override
	public Coord getLocation() {
		return _location;
	}

	@Override
	public void onDraw(Graphics2D g) {
		_background.onDraw(g);
		for (int i=0; i<_containers.size(); i++) {
			_containers.get(i).onDraw(g);
		}
	}
	
	@Override
	public void onMouseClicked(int clickCount, Coord c) {
		boolean sent = false;
		int i = 0;
		while (i<_containers.size() && !sent) {
			if (Algorithms.clickWithin(_containers.get(i), c)) {
				_containers.get(i).onMouseClicked(clickCount, c);
				sent = true;
			}
			i++;
		}
	}

}
