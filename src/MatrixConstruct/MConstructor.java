package MatrixConstruct;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedString;
import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.html.HTML.Attribute;

import org.ietf.jgss.GSSContext;

public class MConstructor extends JPanel{

	Rectangle[][] _grid;
	int _size = 50;
	List<Integer> _offset, _mSize, _selected;
	Map<String, String> _values;
	boolean _drawn;
	boolean _drawing;
	
	public MConstructor() {
		this.setPreferredSize(new Dimension(500,250));
		_grid = new Rectangle[500/_size][250/_size];
		_drawn = false;
		for(int i = 0; i < 500/_size; i++){
			for(int j = 0 ; j < 250/_size; j++){
				_grid[i][j] = new Rectangle(i*_size, j*_size, _size, _size);
			}
		}
		_offset = new ArrayList();
		_selected = new ArrayList();
		_mSize = new ArrayList();
		_mSize.add(0);
		_mSize.add(0);
		_values = new HashMap<>();
		MouseListener ml = new MouseListener(this);
		this.addMouseListener(ml);
		this.addMouseMotionListener(ml);
		this.addKeyListener(new KListener(this));
		this.setFocusable(true);
		this.requestFocus();
		this.setFocusTraversalKeysEnabled(false);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		if(_drawing){
			g2.setColor(Color.decode("#CCCCCC"));
			for(int i = 0; i < _grid.length; i++){
				for(int j = 0 ; j < _grid[0].length; j++){
					_grid[i][j].setLocation((i*_size) + _offset.get(0), (j*_size) + _offset.get(1));
					g2.draw(_grid[i][j]);
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
							g2.fill(_grid[i][j]);
							g2.setColor(Color.black);
						}
					}
					g2.draw(_grid[i][j]);
					
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
		
		public MouseListener(JPanel p){
			_p = p;
		}
		
		public void mouseClicked(MouseEvent e){
			if(_drawn){
				_selected.clear();
				_selected.add((int) Math.floor((e.getX()-_offset.get(0))/_size));
				_selected.add((int) Math.floor((e.getY()-_offset.get(1))/_size));
				_p.repaint();
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
}
