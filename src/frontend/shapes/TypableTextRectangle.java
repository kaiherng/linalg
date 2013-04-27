package frontend.shapes;

import java.awt.Color;
import java.awt.event.KeyEvent;

import frontend.graphicsengine.Algorithms;
import frontend.graphicsengine.Interactable;

public class TypableTextRectangle extends TextRectangle implements Interactable {
	
	private String _default;
	private boolean _typable = false;

	public TypableTextRectangle(String fontName, String style, int textSize,
			String stringToDisplay, Color textColor, Color rectColor,
			Coord location, Coord rectSize) {
		super(fontName, style, textSize, stringToDisplay, textColor, rectColor,
				location, rectSize);
		_default = stringToDisplay;
	}
	
	public TypableTextRectangle(String fontName, String style, int textSize, String stringToDisplay, Color textColor, Color rectColor, Coord location, Coord rectSize, Color rectBorderColor, int strokeWidth) {
		super(fontName, style, textSize, stringToDisplay, textColor, rectColor, location, rectSize, rectBorderColor, strokeWidth);
		_default = stringToDisplay;
	}

	@Override
	public void onDown(int keycode) {
		// TODO Auto-generated method stub
		
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
		if (_typable) {
			String existing = getStringToDisplay();
			if (((int)keychar) == KeyEvent.VK_BACK_SPACE) {
				if ((existing.length()) <= 1) {
					setStringToDisplay("");
				}
				else {
					setStringToDisplay(existing.substring(0,existing.length()-1));
				}
			}
			else {
				String newString = existing + keychar;
				setStringToDisplay(newString);
			}
		}
	}

	@Override
	public void onMouseClicked(int clickCount, Coord c) {
		if (_typable && !Algorithms.clickWithin(this, c)) {
			_typable = false;
		}
		else if (clickCount>= 2) {
			_typable = true;
		}
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
		
	}

	@Override
	public void onDragEnd(Coord location) {
		// TODO Auto-generated method stub
		
	}

}
