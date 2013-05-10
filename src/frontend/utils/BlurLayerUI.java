package frontend.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * For blurring the frame when a JDialog is open
 * @author Kai
 *
 */
@SuppressWarnings("serial")
public class BlurLayerUI extends CustomLayerUI {

	private BufferedImage _mOffscreenImage;
	private BufferedImageOp _mOperation;

	public BlurLayerUI(JFrame frame) {
		super(frame);
		float ninth = 1.0f / 9.0f;
		float[] blurKernel = {
				ninth, ninth, ninth,
				ninth, ninth, ninth,
				ninth, ninth, ninth
		};
		_mOperation = new ConvolveOp(
				new Kernel(3, 3, blurKernel),
				ConvolveOp.EDGE_NO_OP, null);
	}

	@Override
	public void paint (Graphics g, JComponent c) {
		super.paint(g, c);
		if (getActive()) {

			int w = c.getWidth();
			int h = c.getHeight();

			if (w == 0 || h == 0) {
				return;
			}

			// Only create the offscreen image if the one we have
			// is the wrong size.
			if (_mOffscreenImage == null ||
					_mOffscreenImage.getWidth() != w ||
					_mOffscreenImage.getHeight() != h) {
				_mOffscreenImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			}

			Graphics2D ig2 = _mOffscreenImage.createGraphics();
			ig2.setClip(g.getClip());
			super.paint(ig2, c);
			ig2.dispose();

			Graphics2D g2 = (Graphics2D)g;
			g2.drawImage(_mOffscreenImage, _mOperation, 0, 0);
		}
		getFrame().repaint();
	}
}