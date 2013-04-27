package frontend.containers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;

import frontend.general.Constants;
import frontend.graphicsengine.Algorithms;
import frontend.graphicsengine.Container;
import frontend.shapes.Coord;
import frontend.shapes.Rectangle;
import frontend.shapes.Text;
import frontend.shapes.TextRectangle;

public class Construct extends Container {
	
	private Text _text;
	Rectangle[][] _grid;
	int _size = 40;
	List<Integer> _offset, _mSize, _selected;
	Map<String, String> _values;
	boolean _drawn;
	boolean _drawing;
	boolean _dragging;
	TextRectangle _clear, _save;
	Coord _startDrag;
	Saved _saved;
	
	public Construct(Coord location, Coord size, Saved saved) {
		super(location,size);
		System.out.println(size);
		_text = new Text(Constants.TEXT_FONTSTYLE, Constants.CONSTRUCT_INSTRUCTIONS_TEXT_STYLE, Constants.CONSTRUCT_INSTRUCTIONS_TEXT_SIZE, "Drag across this canvas to construct a matrix", Constants.CONSTRUCT_INSTRUCTIONS_TEXT_COLOR, location.plus(Constants.CONSTRUCT_INSTRUCTIONS_TEXT_OFFSET));
		_grid = new Rectangle[size.x/_size][size.y/_size];
		_drawn = false;
		for(int i=0; i < size.x/_size; i++){
			for(int j=0 ; j< size.y/_size; j++){
				_grid[i][j] = new Rectangle(location, new Coord(_size, _size), Color.white, 1, Color.decode("#CCCCCC"));
			}
		}
		_dragging = false;
		_offset = new ArrayList<>();
		_offset.add(0);
		_offset.add(0);
		_selected = new ArrayList<>();
		_mSize = new ArrayList<>();
		_mSize.add(0);
		_mSize.add(0);
		_values = new HashMap<>();
		
		//containers
		_saved = saved;
		
		//buttons
		_clear = new TextRectangle(Constants.TEXT_FONTSTYLE, "bold", 16, "Clear", Color.white, Constants.JFRAME_HEADER_BG_COLOR, getLocation().plus(getSize().x, getSize().y).minus(180, 35), new Coord(80,30), Color.black, 1);
		_save = new TextRectangle(Constants.TEXT_FONTSTYLE, "bold", 16, "Save", Color.white, Constants.JFRAME_HEADER_BG_COLOR, getLocation().plus(getSize().x, getSize().y).minus(90, 35), new Coord(80,30), Color.black, 1);
	}
	
	@Override
	public void setSize(Coord size) {
		super.setSize(size);
	}
	
	@Override
	public void setLocation(Coord location) {
		super.setLocation(location);
		_clear.setLocation(getLocation().plus(getSize().x, getSize().y).minus(180, 35));
		_save.setLocation(getLocation().plus(getSize().x, getSize().y).minus(90, 35));
	}
	
	public void saveMatrix(){
		if(_values.size() != (_mSize.get(0)+1) * (_mSize.get(1)+1)){
			System.out.println("Incomplete Matrix!!");
			return;
		}
		Double[][] vals = new Double[_mSize.get(0)+1][_mSize.get(1)+1];
		for(int i = 0; i <= _mSize.get(0); i++){
			for(int j = 0; j <= _mSize.get(1); j++){
				vals[i][j] = Double.parseDouble(_values.get("[" + i + ", " + j + "]"));
			}
		}
		_saved.addMatrix("C", new Matrix(DisplayType.DECIMAL, vals));
		clear();
	}
	
	public void clear(){
		_drawn = false;
		_drawing = false;
		_mSize.clear();
		_mSize.add(0);
		_mSize.add(0);
		_selected.clear();
		_values.clear();
		_offset.clear();
	}

	@Override
	public void onDraw(Graphics2D g) {
		_clear.onDraw(g);
		_save.onDraw(g);
		if(!_drawn && !_drawing){
			_text.onDraw(g);	
		}
		
		//draws guide grid
		if(_drawing){
			for(int i = 0; i <_grid.length; i++){
				for(int j = 0; j <_grid[0].length; j++){
					if(_grid == null){
						System.out.println("ERROR");
					}
					Coord c = new Coord((i*_size) + _offset.get(0), (j*_size) + _offset.get(1));
					_grid[i][j].setLocation(c);
					_grid[i][j].setBorderColor(Color.decode("#CCCCCC"));
					if(Algorithms.clickWithin(this, _grid[i][j].getLocation().plus(_size, _size))){
						_grid[i][j].onDraw(g);
					}
				}
			}
		}
		
		//draws actual matrix grid and highlights selected
		if(_drawn){
			for(int i = 0; i <= _mSize.get(0); i++){
				for(int j = 0; j <= _mSize.get(1); j++){
					_grid[i][j].setLocation(new Coord((i*_size) + _offset.get(0), (j*_size) + _offset.get(1)));
					//if selected, set background to gray
					if(_selected.size() == 2){
						if(i == _selected.get(0)&& j == _selected.get(1)){
							_grid[i][j].setFillColor(Color.decode("#BBBBBB"));
						} else {
							_grid[i][j].setFillColor(Color.white);
						}
					}
					_grid[i][j].setBorderColor(Color.black);
					_grid[i][j].onDraw(g);
					
					//draw values
					List<Integer> pos = new ArrayList<>();
					pos.add(i);
					pos.add(j);
					if(_values.containsKey(pos.toString())){
						String s = _values.get(pos.toString());
						Text t = new Text(Constants.TEXT_FONTSTYLE, "bold", 18, s, Color.black, _grid[i][j].getLocation().plus(0, _size/2));
						t.onDraw(g, _grid[i][j]);
					}
				}
			}
		}
	}
	@Override
	
	public void onMouseClicked(int clickCount, Coord c) {
		// TODO Auto-generated method stub
		if(_drawn){
			for(int i = 0; i <= _mSize.get(0); i++){
				for(int j = 0; j <= _mSize.get(1); j++){
					if(Algorithms.clickWithin(_grid[i][j], c)){
						_selected.clear();
						_selected.add(i);
						_selected.add(j);
					}
				}
			}
		}
		if(Algorithms.clickWithin(_clear, c)){
			clear();
		} else if(Algorithms.clickWithin(_save, c)){
			saveMatrix();
		}
	}

	@Override
	public void onDown(int keycode) {
		// TODO Auto-generated method stub
		if(_selected.size() == 2){
			//tab key
			if(keycode == 9){
				if(_selected.get(0) < _mSize.get(0)){
					_selected.set(0, _selected.get(0)+1);
				} else {
					if(_selected.get(1) < _mSize.get(1)){
						_selected.set(1, _selected.get(1)+1);
						_selected.set(0, 0);
					} else {
						_selected.set(0, 0);
						_selected.set(1, 0);
					}
				}
				return;
			}
			
			StringBuilder sb;
			if(_values.containsKey(_selected.toString())){
				sb = new StringBuilder(_values.get(_selected.toString()));
			} else {
				sb = new StringBuilder();
			}
			//numbers
			if(keycode >= 48 && keycode <= 57){
				sb.append(keycode-48);
			//period
			} else if(keycode >= 96 && keycode <= 105){
				sb.append(keycode-96);
			} else if(keycode == 110){
				sb.append(".");
			//backspace
			} else if(keycode == 8){
				if(sb.length() != 0){
					sb.setLength(sb.length()-1);
				}
			}
			if(sb.length() == 0){
				_values.remove(_selected.toString());
			} else {
				_values.put(_selected.toString(), sb.toString());
			}
		}
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
	public void onTyped(char keychar) {
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
	public void onMouseDragged(Coord location) {
		// TODO Auto-generated method stub
		if(_drawing){
			_mSize.clear();
			_mSize.add((int) Math.floor((location.x-_offset.get(0))/_size));
			_mSize.add((int) Math.floor((location.y-_offset.get(1))/_size));
			_drawn = true;
		}
		if(_drawn && !_drawing){
			_offset.set(0, _offset.get(0) + location.minus(_startDrag).x);
			_offset.set(1, _offset.get(1) + location.minus(_startDrag).y);
			_startDrag = location;
		}
	}

	@Override
	public void onMouseMoved(Coord location) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMouseWheelForward(Coord location) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMouseWheelBackward(Coord location) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDragStart(Coord location) {
		// TODO Auto-generated method stub
		if(!_drawn){
			_offset.clear();
			_offset.add(location.x);
			_offset.add(location.y);
			_drawing = true;
		}
		if(_drawn && !_drawing){
			_startDrag = location;
		}
	}

	@Override
	public void onDragEnd(Coord location) {
		// TODO Auto-generated method stub
		_drawing = false;
	}

}
