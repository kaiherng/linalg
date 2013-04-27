package frontend.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import backend.blocks.Matrix;
import backend.blocks.Countable.DisplayType;

public class Construct extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -635825278434322408L;
	Rectangle[][] _grid;
	int _size = 50;
	List<Integer> _offset, _mSize, _selected;
	Map<String, String> _values;
	boolean _drawn;
	boolean _drawing;
	Saved _save;
	
	public Construct(Saved saved) {
		this.setLayout(new BorderLayout());
		_save = saved;
		
		_drawn = false;
		_offset = new ArrayList<>();
		_offset.add(0);
		_offset.add(0);
		_selected = new ArrayList<>();
		_mSize = new ArrayList<>();
		_mSize.add(0);
		_mSize.add(0);
		_values = new HashMap<>();
		
		MouseListener ml = new MouseListener(this);
		this.addMouseListener(ml);
		this.addMouseMotionListener(ml);
		this.addKeyListener(new KListener(this));
		this.addComponentListener(new CompListener());
		this.setFocusable(true);
		this.requestFocus();
		this.setFocusTraversalKeysEnabled(false);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton clearButton, saveButton;
		clearButton = new JButton("Clear");
		saveButton = new JButton("Save");
		clearButton.addActionListener(new ClearListener(this));
		saveButton.addActionListener(new SaveListener(this));
		clearButton.setFocusable(false);
		saveButton.setFocusable(false);
		buttonPanel.add(clearButton, BorderLayout.SOUTH);
		buttonPanel.add(saveButton, BorderLayout.SOUTH);
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
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if(_drawing){
			g2.setColor(Color.decode("#CCCCCC"));
			for(int i = 0; i < _grid.length; i++){
				for(int j = 0 ; j < _grid[0].length; j++){
					_grid[i][j].setLocation((i*_size) + _offset.get(0), (j*_size) + _offset.get(1));
					g2.drawRect(_grid[i][j].x, _grid[i][j].y, _grid[i][j].width, _grid[i][j].height);
				}
			}			
		}
		
		if(_drawn){
			g2.setColor(Color.black);
			for(int i = 0; i <= _mSize.get(0); i++){
				for(int j = 0 ; j <= _mSize.get(1); j++){
					_grid[i][j].setLocation((i*_size) + _offset.get(0), (j*_size) + _offset.get(1));
					if(_selected.size() == 2){
						if(i == _selected.get(0) && j == _selected.get(1)){
							g2.setColor(Color.decode("#BBBBBB"));
							g2.fillRect(_grid[i][j].x, _grid[i][j].y, _grid[i][j].width, _grid[i][j].height);
						}
					}
					g2.setColor(Color.black);
					g2.drawRect(_grid[i][j].x, _grid[i][j].y, _grid[i][j].width, _grid[i][j].height);
					
					List<Integer> pos = new ArrayList<>();
					pos.add(i);
					pos.add(j);
					
					if(_values.containsKey(pos.toString())){
						String s = _values.get(pos.toString());
						AttributedString as = new AttributedString(s);
						int pt = 30;
						as.addAttribute(TextAttribute.SIZE, pt);
						TextLayout tl = new TextLayout(as.getIterator(), g2.getFontRenderContext());
						
						while(tl.getBounds().getWidth() >= _size){
							pt -= 5;
							as.addAttribute(TextAttribute.SIZE, pt);
							tl = new TextLayout(as.getIterator(), g2.getFontRenderContext());
						}
						
						//calculate center
						double height = tl.getBounds().getHeight();
						double width = tl.getBounds().getWidth();
						float y = (float)(_grid[i][j].getLocation().y + _size/2 + height/2);
						float x = (float)(_grid[i][j].getLocation().x + _size/2 - width/2);
						tl.draw(g2, x, y);
					}
				}
			}
		}
	}
	
	private class MouseListener extends MouseAdapter{
		
		JPanel _p;
		Point _startDrag;
		
		public MouseListener(JPanel p){
			_p = p;
		}
		
		public void mouseClicked(MouseEvent e){
			_p.requestFocus();
			if(_drawn){
				for(int i = 0 ; i <= _mSize.get(0); i++){
					for(int j = 0; j <= _mSize.get(1); j++){
						if(_grid[i][j].contains(e.getPoint())){
							_selected.clear();
							_selected.add(i);
							_selected.add(j);
							_p.repaint();
							return;
						}
					}
				}
			}
		}
		
		public void mousePressed(MouseEvent e){
			if(!_drawn){
				_offset.clear();
				_offset.add(e.getX());
				_offset.add(e.getY());
				_drawing = true;
				_p.repaint();
			}
			_startDrag = e.getPoint();
		}
		
		public void mouseReleased(MouseEvent e){
			_drawing = false;
			_p.repaint();
		}
		
		public void mouseDragged(MouseEvent e){
			if(_drawing){
				_mSize.clear();
				_mSize.add((int) Math.floor((e.getX()-_offset.get(0))/_size));
				_mSize.add((int) Math.floor((e.getY()-_offset.get(1))/_size));
				_drawn = true;
				_p.repaint();
			} else if(_drawn && !_drawing){
				_offset.set(0, _offset.get(0) + e.getPoint().x - (_startDrag).x);
				_offset.set(1, _offset.get(1) + e.getPoint().y - (_startDrag).y);
				_startDrag = e.getPoint();
				_p.repaint();
			}
		}
	}
	
	private class KListener implements KeyListener{
		
		JPanel _p;
		
		public KListener(JPanel p){
			_p = p;
		}

		@Override
		public void keyPressed(KeyEvent arg0) {}

		@Override
		public void keyReleased(KeyEvent arg0) {
			if(_selected.size() == 2){
				//tab key
				if(arg0.getKeyCode() == 9){
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
					_p.repaint();
					return;
				}
				
				StringBuilder sb;
				if(_values.containsKey(_selected.toString())){
					sb = new StringBuilder(_values.get(_selected.toString()));
				} else {
					sb = new StringBuilder();
				}
				//numbers
				if(arg0.getKeyChar() >= 48 && arg0.getKeyChar() <= 57){
					sb.append(arg0.getKeyChar());
				//period
				} else if(arg0.getKeyCode() == 110){
					sb.append(".");
				//backspace
				} else if(arg0.getKeyCode() == 8){
					sb.setLength(sb.length()-1);
				}
				if(sb.length() == 0){
					_values.remove(_selected.toString());
				} else {
					_values.put(_selected.toString(), sb.toString());
				}
			}
			_p.repaint();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {}
		
	}
	
	public class CompListener implements ComponentListener {
		
		@Override
		public void componentHidden(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentMoved(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentResized(ComponentEvent arg0) {
			Dimension size = arg0.getComponent().getSize();
			_grid = new Rectangle[size.width/_size][size.height/_size];
			for(int i = 0; i < _grid.length; i++){
				for(int j = 0 ; j < _grid[0].length; j++){
					_grid[i][j] = new Rectangle(i*_size, j*_size, _size, _size);
				}
			}
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class SaveListener implements ActionListener{

		Construct _c;
		
		public SaveListener(Construct c){
			_c = c;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			for(int i = 0; i <= _mSize.get(0); i++){
				for(int j = 0; j <= _mSize.get(1); j++){
					if(!_values.containsKey("[" + i + ", " + j + "]")){
						System.out.println("incomplete matrix!");
						return;
					}
				}
			}
			Double[][] mValues = new Double[_mSize.get(0)+1][_mSize.get(1)+1];
			for(int i = 0; i <= _mSize.get(0); i++){
				for(int j = 0; j <= _mSize.get(1); j++){
					mValues[i][j] = Double.parseDouble(_values.get("[" + i + ", " + j + "]"));
				}
			}
			_save.addMatrix("A", new Matrix(DisplayType.DECIMAL, new Double[][] {{1.0}}));
			_c.clear();
		}
	}
	
	public class ClearListener implements ActionListener{
		
		Construct _c;
		
		public ClearListener(Construct c){
			_c = c;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			_c.clear();
		}
	}

}
