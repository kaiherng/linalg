package frontend.blocks;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.blocks.Bracket;
import frontend.panels.Compute;
import frontend.swing.CurrentConstants;

public class BracketBlock extends JPanel{

	boolean _isOpen;
	Compute _c;
	String _text;
	
	public BracketBlock(boolean isOpen, Compute c) {
		_isOpen = isOpen;
		_c = c;
		JLabel label = new JLabel();
		if(isOpen){
			_text = "(";
			this.setToolTipText("Open Bracket");
		} else {
			_text = ")";
			this.setToolTipText("Close Bracket");
		}
		label.setText(_text);
		label.setForeground(CurrentConstants.BRACKET_BLOCK_FG);
		this.setPreferredSize(CurrentConstants.BRACKET_BLOCK_SIZE);
		this.setBackground(CurrentConstants.BRACKET_BLOCK_BG);
		this.add(label);
		this.addMouseListener(new Click());
	}
	
	private class Click extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e){
			_c.addToBar(new Bracket(_isOpen), _text);
		}
	}

}
