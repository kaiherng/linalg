package frontend.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class WhiteLayerUI extends CustomLayerUI {

	public WhiteLayerUI(JFrame frame) {
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
			g2.setColor(Color.white);
			g2.fillRect(0, 0, w, h);

			g2.dispose();
		}
		getFrame().repaint();
	}
}

