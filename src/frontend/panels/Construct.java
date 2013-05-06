package frontend.panels;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
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
import java.math.BigDecimal;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.blocks.Scalar;
import frontend.swing.AppFrame;
import frontend.swing.Button;
import frontend.swing.CurrentConstants;
import frontend.swing.CustomDialog;
import frontend.swing.DialogListener;

public class Construct extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -635825278434322408L;
	Rectangle[][] _grid;
	int _size = 50;
	List<Integer> _offset, _mSize, _selected, _prevSelected;
	Map<String, String> _values;
	boolean _drawn;
	boolean _drawing;
	boolean _editing;
	String _currentEdit;
	Saved _save;
	String _sizeIndicator;
	Point _mouseLocation;
	int _fontSize = 30;
	private AppFrame _frame;
	private JLabel _instructionsLabel;
	private Button _clearButton, _saveButton, _scalarButton, _iButton, _fillButton;
	
	public Construct(Saved saved, AppFrame frame) {
		_frame = frame;
		this.setLayout(new BorderLayout());
		setBackground(CurrentConstants.CONSTRUCT_BG);
		setBorder(CurrentConstants.CONSTRUCT_BORDER);
		_save = saved;
		
		_drawn = false;
		_offset = new ArrayList<>();
		_offset.add(0);
		_offset.add(0);
		_selected = new ArrayList<>();
		_prevSelected = new ArrayList<>();
		_mSize = new ArrayList<>();
		_mSize.add(0);
		_mSize.add(0);
		_values = new HashMap<>();
		
		_sizeIndicator = "";
		_mouseLocation = new Point(0,0);
		
		MouseListener ml = new MouseListener(this);
		this.addMouseListener(ml);
		this.addMouseMotionListener(ml);
		this.addKeyListener(new KListener(this));
		this.addComponentListener(new CompListener());
		this.setFocusable(true);
		this.requestFocus();
		this.setFocusTraversalKeysEnabled(false);
		
		JPanel instructionsPanel = new JPanel(new BorderLayout());
		_instructionsLabel = new JLabel(CurrentConstants.CONSTRUCT_INSTRUCTIONSLABEL_EMPTYTEXT);
		_instructionsLabel.setBorder(CurrentConstants.CONSTRUCT_INSTRUCTIONSLABEL_BORDER);
		instructionsPanel.add(_instructionsLabel, BorderLayout.WEST);
		this.add(instructionsPanel,BorderLayout.NORTH);		
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(CurrentConstants.CONSTRUCT_BUTTON_PANEL_BG);
		buttonPanel.setBorder(CurrentConstants.CONSTRUCT_BUTTON_PANEL_BORDER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		_clearButton = new Button("Clear", CurrentConstants.BUTTON_BG, CurrentConstants.BUTTON_FG, CurrentConstants.BUTTON_HOVER_BG, CurrentConstants.BUTTON_HOVER_FG, CurrentConstants.BUTTON_PRESSED_BG, CurrentConstants.BUTTON_PRESSED_FG, CurrentConstants.BUTTON_BORDER);
		_saveButton = new Button("Save", CurrentConstants.BUTTON_BG, CurrentConstants.BUTTON_FG, CurrentConstants.BUTTON_HOVER_BG, CurrentConstants.BUTTON_HOVER_FG, CurrentConstants.BUTTON_PRESSED_BG, CurrentConstants.BUTTON_PRESSED_FG, CurrentConstants.BUTTON_BORDER);
		_scalarButton = new Button("New Scalar", CurrentConstants.BUTTON_BG, CurrentConstants.BUTTON_FG, CurrentConstants.BUTTON_HOVER_BG, CurrentConstants.BUTTON_HOVER_FG, CurrentConstants.BUTTON_PRESSED_BG, CurrentConstants.BUTTON_PRESSED_FG, CurrentConstants.BUTTON_BORDER);
		_iButton = new Button("Identity", CurrentConstants.BUTTON_BG, CurrentConstants.BUTTON_FG, CurrentConstants.BUTTON_HOVER_BG, CurrentConstants.BUTTON_HOVER_FG, CurrentConstants.BUTTON_PRESSED_BG, CurrentConstants.BUTTON_PRESSED_FG, CurrentConstants.BUTTON_BORDER);
		_fillButton = new Button("Fill Matrix", CurrentConstants.BUTTON_BG, CurrentConstants.BUTTON_FG, CurrentConstants.BUTTON_HOVER_BG, CurrentConstants.BUTTON_HOVER_FG, CurrentConstants.BUTTON_PRESSED_BG, CurrentConstants.BUTTON_PRESSED_FG, CurrentConstants.BUTTON_BORDER);
		_fillButton.setToolTipText("Fill empty cells with specified value");
		_iButton.setToolTipText("Makes the matrix an identity matrix");
		_scalarButton.setToolTipText("Creates a new scalar");
		_saveButton.setToolTipText("Saves the created matrix to the panel below");
		_clearButton.setToolTipText("Clears all work in this panel");
		_clearButton.addActionListener(new ClearListener(this));
		_saveButton.addActionListener(new SaveListener(this));
		_scalarButton.addActionListener(new ScalarListener(this));
		_iButton.addActionListener(new IdentityListener(this));
		_fillButton.addActionListener(new FillListener(this));
		_clearButton.setFocusable(false);
		_saveButton.setFocusable(false);
		_scalarButton.setFocusable(false);
		_iButton.setFocusable(false);
		_fillButton.setFocusable(false);
		buttonPanel.add(_fillButton);
		buttonPanel.add(_iButton);
		buttonPanel.add(_scalarButton);
		buttonPanel.add(_clearButton);
		buttonPanel.add(_saveButton);
		buttonPanel.setOpaque(false);
		checkButtons();
	}
	
	private void checkButtons() {
		 
		if (!_drawn) {
			_clearButton.setEnabled(false);
			_iButton.setEnabled(false);
			_saveButton.setEnabled(false);
			_fillButton.setEnabled(false);
			_scalarButton.setEnabled(true);
		}
		else {
			_clearButton.setEnabled(true);
			if (_mSize.get(0) == _mSize.get(1)) {
				_iButton.setEnabled(true);
			}
			_saveButton.setEnabled(true);
			for(int i = 0; i <= _mSize.get(0); i++){
				for(int j = 0; j <= _mSize.get(1); j++){
					if(!_values.containsKey("[" + i + ", " + j + "]")){
						_saveButton.setEnabled(false);
					}
				}
			}
			
			_fillButton.setEnabled(true);
			_scalarButton.setEnabled(true);
		}
	}
	
	public void clear(){
		_editing = false;
		_drawn = false;
		_drawing = false;
		_mSize.clear();
		_mSize.add(0);
		_mSize.add(0);
		_selected.clear();
		_values.clear();
		_offset.clear();
		_save.clear();
		checkButtons();
		this.repaint();
	}
	
	public void editMatrix(Matrix m, String s){
		clear();
		Double[][] values = m.getValues();
		for(int i = 0; i < values.length; i++){
			for(int j = 0; j < values[0].length; j++){
				Double d = values[i][j];
				BigDecimal bd = new BigDecimal(Double.valueOf(d));
				String val;
				if(bd.intValue() - bd.doubleValue() == new Double(0)){
					val = ((Integer) bd.intValue()).toString();
				} else {
					val = Double.toString(d);
				}
				_values.put("[" + i + ", " + j + "]", val);
			}
		}
		_editing = true;
		_currentEdit = s;
		_mSize.clear();
		_offset.clear();
		_mSize.add(values.length-1);
		_mSize.add(values[0].length-1);
		_offset.add(15);
		_offset.add(50);
		_drawing = false;
		_drawn = true;
		checkButtons();
		this.repaint();
		_instructionsLabel.setText(CurrentConstants.CONSTRUCT_INSTRUCTIONSLABEL_EDITINGSAVEDTEXT);
	}
	
	public Saved getSavedPanel(){
		return _save;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//antialiasing text (if the prev line screwed up, use this instead)
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		if(_drawing){
			g2.setColor(CurrentConstants.CONSTRUCT_GRID_LIGHT);
			for(int i = 0; i < _grid.length; i++){
				for(int j = 0 ; j < _grid[0].length; j++){
					_grid[i][j].setLocation((i*_size) + _offset.get(0), (j*_size) + _offset.get(1));
					g2.drawRect(_grid[i][j].x, _grid[i][j].y, _grid[i][j].width, _grid[i][j].height);
				}
			}
//			g2.setColor(CurrentConstants.CONSTRUCT_GRID_DARK);
//			g2.drawString(_sizeIndicator, _mouseLocation.x + CurrentConstants.CONSTRUCT_SIZEINDICATOR_XOFFSET, _mouseLocation.y + CurrentConstants.CONSTRUCT_SIZEINDICATOR_YOFFSET);

		}
		
		if(_drawn){
			g2.setColor(CurrentConstants.CONSTRUCT_GRID_DARK);
			_fontSize = 30;
			for(int i = 0; i <= _mSize.get(0); i++){
				for(int j = 0 ; j <= _mSize.get(1); j++){
					_grid[i][j].setLocation((i*_size) + _offset.get(0), (j*_size) + _offset.get(1));
					if(_selected.size() == 2){
						if(i == _selected.get(0) && j == _selected.get(1)){
							g2.setColor(CurrentConstants.CONSTRUCT_GRID_MEDIUM);
							g2.fillRect(_grid[i][j].x, _grid[i][j].y, _grid[i][j].width, _grid[i][j].height);
						}
					}
					g2.setColor(CurrentConstants.CONSTRUCT_GRID_DARK);
					g2.drawRect(_grid[i][j].x, _grid[i][j].y, _grid[i][j].width, _grid[i][j].height);
					
					List<Integer> pos = new ArrayList<>();
					pos.add(i);
					pos.add(j);
					
					if(_values.containsKey(pos.toString())){
						String s = _values.get(pos.toString());
						AttributedString as = new AttributedString(s);
						int pt = 30;
						as.addAttribute(TextAttribute.SIZE, _fontSize);
						TextLayout tl = new TextLayout(as.getIterator(), g2.getFontRenderContext());
						
						while(tl.getBounds().getWidth() >= _size){
							pt -= 5;
							as.addAttribute(TextAttribute.SIZE, pt);
							tl = new TextLayout(as.getIterator(), g2.getFontRenderContext());
						}
						_fontSize = Math.min(pt, _fontSize);
						
					}
				}
			}
			
			
			for(int i = 0; i <= _mSize.get(0); i++){
				for(int j = 0 ; j <= _mSize.get(1); j++){
					List<Integer> pos = new ArrayList<>();
					pos.add(i);
					pos.add(j);
					if(_values.containsKey(pos.toString())){
						String s = _values.get(pos.toString());
						AttributedString as = new AttributedString(s);

						as.addAttribute(TextAttribute.SIZE, _fontSize);
						as.addAttribute(TextAttribute.FAMILY, CurrentConstants.CONSTRUCT_FONT);
						TextLayout tl = new TextLayout(as.getIterator(), g2.getFontRenderContext());
						
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
		

		if (_drawing) {
			if (_sizeIndicator.length() > 0) {
				
				Color prevColor = g2.getColor();
				Stroke prevStroke = g2.getStroke();
				Stroke newStroke = new BasicStroke(CurrentConstants.CONSTRUCT_SIZEINDICATOR_STROKESIZE);
				g2.setColor(CurrentConstants.CONSTRUCT_SIZEINDICATOR_STROKECOLOR);
				g2.setStroke(newStroke);
				g2.drawRoundRect(
						_mouseLocation.x + CurrentConstants.CONSTRUCT_SIZEINDICATOR_XOFFSET,// X position
						_mouseLocation.y + CurrentConstants.CONSTRUCT_SIZEINDICATOR_YOFFSET - CurrentConstants.CONSTRUCT_SIZEINDICATOR_SIZE,// Y position
	                    70, // width
	                    30, // height
	                    15,15);// arc Dimension
				g2.setColor(CurrentConstants.CONSTRUCT_SIZEINDICATOR_BG);
				g2.fillRoundRect(
						_mouseLocation.x + CurrentConstants.CONSTRUCT_SIZEINDICATOR_XOFFSET,// X position
						_mouseLocation.y + CurrentConstants.CONSTRUCT_SIZEINDICATOR_YOFFSET  - CurrentConstants.CONSTRUCT_SIZEINDICATOR_SIZE,// Y position
	                    70, // width
	                    30, // height
	                    15,15);// arc Dimension
				g2.setColor(prevColor);
				g2.setStroke(prevStroke);
				
				AttributedString as = new AttributedString(_sizeIndicator);
				as.addAttribute(TextAttribute.SIZE, CurrentConstants.CONSTRUCT_SIZEINDICATOR_SIZE);
				as.addAttribute(TextAttribute.FAMILY, CurrentConstants.CONSTRUCT_SIZEINDICATOR_FONT);
				as.addAttribute(TextAttribute.WEIGHT, CurrentConstants.CONSTRUCT_SIZEINDICATOR_WEIGHT);
				as.addAttribute(TextAttribute.FOREGROUND, CurrentConstants.CONSTRUCT_SIZEINDICATOR_FG);
				TextLayout tl = new TextLayout(as.getIterator(), g2.getFontRenderContext());
				tl.draw(g2, _mouseLocation.x + CurrentConstants.CONSTRUCT_SIZEINDICATOR_XOFFSET + 15, _mouseLocation.y + CurrentConstants.CONSTRUCT_SIZEINDICATOR_YOFFSET + 5);
				
				
			}
		}
		
		
	}
	
	public class MouseListener extends MouseAdapter{
		
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
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			if(_drawing = true){
				if(_mSize.get(0) > -1 && _mSize.get(1) > -1){
					if(_selected.size() != 2){
						_selected.clear();
						_selected.add(0);
						_selected.add(0);
						_instructionsLabel.setText(CurrentConstants.CONSTRUCT_INSTRUCTIONSLABEL_DRAWNTEXT);
						_p.repaint();
					}
				}
			}
			_drawing = false;
			_p.repaint();
			checkButtons();
		}
		
		public void mouseDragged(MouseEvent e){
			_p.requestFocus();
			if(_drawing){
				_mouseLocation = e.getPoint();
				int newX = (int) Math.floor((e.getX()-_offset.get(0))/_size);
				int newY = (int) Math.floor((e.getY()-_offset.get(1))/_size);
				if(newX < 0){
					newX = 0;
				}
				if(newY < 0){
					newY = 0;
				}
				if(newX < _grid.length && newY < _grid[0].length){
					_mSize.clear();
					_mSize.add(newX);
					_mSize.add(newY);
					if(newX > 7){
						_mSize.set(0, 7);
					}
					if(newY > 7){
						_mSize.set(1, 7);
					}
					_sizeIndicator = (1+_mSize.get(0)) + " x " + (1+_mSize.get(1));
					_drawn = true;
					_p.repaint(0);
				}
			} else if(_drawn && !_drawing){
				//drag matrix around
				setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				int newX = _offset.get(0) + e.getPoint().x - (_startDrag).x;
				int newY = _offset.get(1) + e.getPoint().y - (_startDrag).y;
				_offset.set(0, newX);
				_offset.set(1, newY);
				if(newX < 0){
					_offset.set(0, 0);
				} else if(newX > _p.getWidth() - (1+_mSize.get(0))*_size){
					_offset.set(0, _p.getWidth() - (1+_mSize.get(0))*_size);
				}
				if(newY < 0){
					_offset.set(1, 0);
				} else if(newY > _p.getHeight() - (1+_mSize.get(1))*_size){
					_offset.set(1, _p.getHeight() - (1+_mSize.get(1))*_size);
				}
				_startDrag = e.getPoint();
				//_p.revalidate();
				_p.repaint();
			}
		}
	}
	
	public class KListener implements KeyListener{
		
		JPanel _p;
		
		public KListener(JPanel p){
			_p = p;
		}

		@Override
		public void keyPressed(KeyEvent arg0) {}

		@Override
		public void keyReleased(KeyEvent arg0) {
			int keyCode = arg0.getKeyCode();
			if(_selected.size() == 2){
				//tab key
				if(keyCode == 9){
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
				//arrow keys
				switch(keyCode){
				case 38:
					if(_selected.get(1) > 0){
						_selected.set(1, _selected.get(1)-1);
					}
					_p.repaint();
					return;
				case 40:
					if(_selected.get(1) < _mSize.get(1)){
						_selected.set(1, _selected.get(1)+1);
						
					}
					_p.repaint();
					return;
				case 37:
					if(_selected.get(0) > 0){
						_selected.set(0, _selected.get(0)-1);
					}
					_p.repaint();
					return;
				case 39:
					if(_selected.get(0) < _mSize.get(0)){
						_selected.set(0, _selected.get(0)+1);
					}
					_p.repaint();
					return;
				}
				
				StringBuilder sb;

				if(_selected.equals(_prevSelected)){
					if(_values.containsKey(_selected.toString())){
						sb = new StringBuilder(_values.get(_selected.toString()));
					} else {
						sb = new StringBuilder();
					}
				} else {
					sb = new StringBuilder();
					_prevSelected = new ArrayList<>(_selected);
				}
				
				//numbers
				if(keyCode >= 48 && keyCode <= 57){
					sb.append(arg0.getKeyChar());
				} else if(keyCode >= 96 && keyCode <= 105){
					sb.append(arg0.getKeyChar());
				//period
				} else if(keyCode == 110 || keyCode == 46){
					if(sb.indexOf(".") == -1){
						sb.append(".");
					} 
				//backspace
				} else if(keyCode == 8){
					if(sb.length() > 0){
						sb.setLength(sb.length()-1);
					}
				//negative
				} else if(keyCode == 45){
					if(sb.length() == 0){
						sb.append("-");
					}
				}
				if(sb.length() > 8){
					sb.setLength(8);
				}
				if(sb.length() == 0){
					_values.remove(_selected.toString());
				} else {
					_values.put(_selected.toString(), sb.toString());
				}
			}
			checkButtons();
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
			if(!_drawn){
				Dimension size = arg0.getComponent().getSize();
				if(size.height < 1 || size.width < 1){
					return;
				}
				_grid = new Rectangle[size.width/_size - 1][size.height/_size - 1];
				for(int i = 0; i < _grid.length; i++){
					for(int j = 0 ; j < _grid[0].length; j++){
						_grid[i][j] = new Rectangle(i*_size, j*_size, _size, _size);
					}
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
						_instructionsLabel.setText(CurrentConstants.CONSTRUCT_INSTRUCTIONSLABEL_INCOMPLETETEXT);
						return;
					}
				}
			}
			Double[][] mValues = new Double[_mSize.get(0)+1][_mSize.get(1)+1];
			DisplayType dt = DisplayType.WHOLENUMBER;
			for(int i = 0; i <= _mSize.get(0); i++){
				for(int j = 0; j <= _mSize.get(1); j++){
					Double d = Double.parseDouble(_values.get("[" + i + ", " + j + "]"));
					mValues[i][j] = d;
					BigDecimal bd = new BigDecimal(Double.valueOf(d));
					if(!(bd.intValue() - bd.doubleValue() == new Double(0))){
						dt = DisplayType.DECIMAL;
					}
				}
			}
			if(_editing){
				_save.replaceCountable(_currentEdit, new Matrix(dt, mValues));
				_editing = false;
			} else {
				_save.addCountable(new Matrix(dt, mValues));
			}
			_instructionsLabel.setText(CurrentConstants.CONSTRUCT_INSTRUCTIONSLABEL_SAVEDTEXT);
			_c.clear();
		}
	}
	
	public class SaveNameListener implements ActionListener{

		Construct _c;
		
		public SaveNameListener(Construct c){
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
			DisplayType dt = DisplayType.WHOLENUMBER;
			for(int i = 0; i <= _mSize.get(0); i++){
				for(int j = 0; j <= _mSize.get(1); j++){
					Double d = Double.parseDouble(_values.get("[" + i + ", " + j + "]"));
					mValues[i][j] = d;
					BigDecimal bd = new BigDecimal(Double.valueOf(d));
					if(!(bd.intValue() - bd.doubleValue() == new Double(0))){
						dt = DisplayType.DECIMAL;
					}
				}
			}
			/**
			 * TODO
			 */
//			_save.addCountable(STRING NAME, new Matrix(dt, mValues));
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
			_instructionsLabel.setText(CurrentConstants.CONSTRUCT_INSTRUCTIONSLABEL_EMPTYTEXT);
		}
	}
	
	public class ScalarListener implements ActionListener, DialogListener{
		
		Construct _c;
		
		public ScalarListener(Construct c){
			_c = c;
		}
		
		@Override
		public void doDialogReturn(Double d) {
			BigDecimal bd = new BigDecimal(Double.valueOf(d));
			DisplayType dt;
			if(!(bd.intValue() - bd.doubleValue() == new Double(0))){
				dt = DisplayType.DECIMAL;
			} else {
				dt = DisplayType.WHOLENUMBER;
			}
			_save.addCountable("scalar", new Scalar(d, dt));
			if (!_drawn)	_instructionsLabel.setText(CurrentConstants.CONSTRUCT_INSTRUCTIONSLABEL_SAVEDSCALARTEXT);
			else if (_editing)	_instructionsLabel.setText(CurrentConstants.CONSTRUCT_INSTRUCTIONSLABEL_SAVEDSCALAR_EDITINGSAVEDTEXT);
			else	_instructionsLabel.setText(CurrentConstants.CONSTRUCT_INSTRUCTIONSLABEL_SAVEDSCALAR_MATRIXDRAWNTEXT);
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
				new CustomDialog(_frame, "create scalar", this, _c, _frame.getUILayer());
		}
	}
	
	public class IdentityListener implements ActionListener{
		
		Construct _c;
		
		public IdentityListener(Construct c){
			_c = c;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(_drawn){
				if(_mSize.get(0) != _mSize.get(1)){
					System.out.println("matrix is not square!");
					return;
				}
				_values.clear();
				for(int i = 0; i < _mSize.get(0)+1; i++){
					for(int j = 0; j < _mSize.get(1)+1; j++){
						if(i == j){
							_values.put("[" + i  + ", " + j + "]", "1");
						} else {
							_values.put("[" + i  + ", " + j + "]", "0");
						}
					}
				}
				_c.repaint();
				checkButtons();
			}
		}
	}
	
	public class FillListener implements ActionListener, DialogListener{
		
		Construct _c;
		
		public FillListener(Construct c){
			_c = c;
		}
		
		@Override
		public void doDialogReturn(Double value) {
			for(int i = 0; i < _mSize.get(0)+1; i++){
				for(int j = 0; j < _mSize.get(1)+1; j++){
					String val = _values.get("[" + i  + ", " + j + "]");
					if(val == null || val.length() == 0){
						BigDecimal bd = new BigDecimal(Double.valueOf(value));
						if(bd.intValue() - bd.doubleValue() == new Double(0)){
							val = ((Integer) bd.intValue()).toString();
						} else {
							val = Double.toString(value);
						}
						_values.put("[" + i  + ", " + j + "]", val);
					}
				}
			}
			_c.repaint();
			checkButtons();
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if(_drawn){
				new CustomDialog(_frame, "fill matrix", this, _c, _frame.getUILayer());
			}
		}
	}

}
