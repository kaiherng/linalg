package frontend.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Fades out (to black) the rest of the frame when the JDialog is open
 * @author Kai
 *
 */
@SuppressWarnings("serial")
public class FadeLayerUI extends CustomLayerUI {

	public FadeLayerUI(JFrame frame) {
		super(frame);
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);
		if (getActive()) {

			Graphics2D g2 = (Graphics2D) g.create();

			int w = c.getWidth();
			int h = c.getHeight();
			g2.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, .5f));
			g2.fillRect(0, 0, w, h);
			
			g2.dispose();
			getFrame().repaint();
		}
	}
}

