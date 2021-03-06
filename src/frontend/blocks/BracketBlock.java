package frontend.blocks;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.blocks.Bracket;
import frontend.panels.Compute;
import frontend.swing.CurrentConstants;

/**
 * Creates a bracket icon in the compute panel
 * @author jypoon
 *
 */
public class BracketBlock extends JPanel{

	private static final long serialVersionUID = -5045109929119734971L;
	boolean _isOpen;
	Compute _c;
	String _text;
	BufferedImage _bi;
	
	public BracketBlock(boolean isOpen, Compute c) {
		_isOpen = isOpen;
		_c = c;
		JLabel label = new JLabel();
		Icon i;
		if(isOpen){
			this.setToolTipText("Open Bracket");
			i = new ImageIcon("icons/obracket.png");
		} else {
			this.setToolTipText("Close Bracket");
			i = new ImageIcon("icons/cbracket.png");
		}
		
		_bi = new BufferedImage(i.getIconWidth(), i.getIconWidth(), BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = _bi.createGraphics();
	    i.paintIcon(null,g,0,0);
	    g.dispose();
	    
		Icon scaled = new ImageIcon(_bi.getScaledInstance(50,50, BufferedImage.SCALE_DEFAULT));
		label = new JLabel(scaled);
		
		this.setPreferredSize(CurrentConstants.BRACKET_BLOCK_SIZE);
		this.setBackground(CurrentConstants.BRACKET_BLOCK_BG);
		this.add(label);
		this.addMouseListener(new Click());
	}
	
	private class Click extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e){
			_c.addToBar(new Bracket(_isOpen), _text, _bi);
		}
		
		public void mouseEntered(MouseEvent e) {
			setBackground(CurrentConstants.BRACKET_BLOCK_HOVER_BG);
		}
		
		public void mouseExited(MouseEvent e) {
			setBackground(CurrentConstants.BRACKET_BLOCK_BG);
		}
		
		public void mousePressed(MouseEvent e) {
			setBackground(CurrentConstants.BRACKET_BLOCK_PRESSED_BG);
		}
	}

}
