package frontend.blocks;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.blocks.Op;
import backend.blocks.Operation;
import frontend.panels.Compute;
import frontend.swing.Constants;

public class OpBlock extends JPanel {
	
	Op _op;
	Compute _c;
	String _text;

	public OpBlock(Op op, String text, Compute c) {
		JLabel label = new JLabel(text);
		label.setForeground(Constants.OP_BLOCK_FG);
		this.setPreferredSize(Constants.OP_BLOCK_SIZE);
		this.setBackground(Constants.OP_BLOCK_BG);
		this.add(label);
		this.addMouseListener(new Click());
		this.setToolTipText(op.toString());
		_op = op;
		_c = c;
		_text = text;
	}
	
	private class Click extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e){
			_c.addToBar(new Operation(_op), _text);
		}
	}
}
