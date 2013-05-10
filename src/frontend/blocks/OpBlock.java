package frontend.blocks;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.blocks.Op;
import backend.blocks.Operation;
import backend.computations.operations.Multiply;
import frontend.panels.Compute;
import frontend.swing.CurrentConstants;

/**
 * Represents an operation in the compute panel, this is created for each op enum
 * @author jypoon
 *
 */

public class OpBlock extends JPanel {
	
	private static final long serialVersionUID = 5844085158536047903L;
	Op _op;
	Compute _c;
	String _text;
	BufferedImage _bi;

	/**
	 * Created with an op enum, icon path and also the compute panel
	 * @param op
	 * @param iconPath
	 * @param c
	 */
	public OpBlock(Op op, String iconPath, Compute c) {
		Icon i = new ImageIcon(iconPath);
		_bi = new BufferedImage(i.getIconWidth(), i.getIconWidth(), BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = _bi.createGraphics();
	    i.paintIcon(null,g,0,0);
	    g.dispose();
	    
		Icon scaled = new ImageIcon(_bi.getScaledInstance(50,50, BufferedImage.SCALE_DEFAULT));
		JLabel label = new JLabel(scaled);
		this.setPreferredSize(CurrentConstants.OP_BLOCK_SIZE);
		this.setBackground(CurrentConstants.OP_BLOCK_BG);
		this.add(label);
		this.addMouseListener(new Click());
		this.setToolTipText(op.toString());
		_op = op;
		_c = c;
		_text = iconPath;
	}
	
	private class Click extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e){
			_c.addToBar(new Operation(_op), _text, _bi);
		}
		
		public void mouseEntered(MouseEvent e) {
			setBackground(CurrentConstants.OP_BLOCK_HOVER_BG);
		}
		
		public void mouseExited(MouseEvent e) {
			setBackground(CurrentConstants.OP_BLOCK_BG);
		}
		
		public void mousePressed(MouseEvent e) {
			setBackground(CurrentConstants.OP_BLOCK_PRESSED_BG);
		}
	}
}
