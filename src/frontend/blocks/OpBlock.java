package frontend.blocks;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.panels.Compute;

import backend.blocks.Op;
import backend.blocks.Operation;

public class OpBlock extends JPanel {
	
	Op _op;
	Compute _c;
	String _text;

	public OpBlock(Op op, String text, Compute c) {
		JLabel label = new JLabel(text);
		label.setForeground(Color.white);
		this.setPreferredSize(new Dimension(60,60));
		this.setBackground(Color.blue);
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
