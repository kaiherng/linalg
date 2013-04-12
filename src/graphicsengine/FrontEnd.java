package graphicsengine;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import shapes.Coord;


/**
 * Front end that processes drawing panel events, like uniting key presses and mouse clicks into custom drag events, etc.
 * Also contains screen 
 * @author kloh
 *
 */
public abstract class FrontEnd {

	private JPanel _drawingPanel;
	private JFrame _jFrame;
	private Coord _size; //size of drawing panel in the frame
	protected boolean _loaded; //to prevent resizing in drawing panel and subsequently passing the call down to any screens before the screen stack has been set up

	public FrontEnd(Coord size)  {
		_size = size;
		_loaded = false;
		
		_jFrame = new JFrame("-- LINALG --");
		_jFrame.setMinimumSize(new Dimension(size.x,size.y));
		_jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when frame closes, exit program
		_drawingPanel = new DrawingPanel();
		
		_jFrame.add(_drawingPanel);
		_jFrame.pack();
		_jFrame.setVisible(true);
	}
	
	/**
	 * Once everything is loaded, sets the size of the drawing panel and repaints it, because we didnt set the size earlier
	 */
	public void initialSetup() {
		if (_loaded) {
			_drawingPanel.setPreferredSize(new Dimension(_size.x,_size.y));
			_jFrame.pack();
			_drawingPanel.repaint();
		}
	}
	
	protected abstract void onDraw(Graphics2D g);

	protected abstract void onKeyTyped(KeyEvent e);

	protected abstract void onKeyPressed(KeyEvent e);

	protected abstract void onKeyReleased(KeyEvent e);

	protected abstract void onMouseClicked(MouseEvent e);
	
	protected abstract void onMousePressed(MouseEvent e);

	protected abstract void onMouseReleased(MouseEvent e);

	protected abstract void onMouseDragged(MouseEvent e);

	protected abstract void onMouseMoved(MouseEvent e);

	protected abstract void onMouseWheelMoved(MouseWheelEvent e);

	protected abstract void onResize(Coord newSize);

	@SuppressWarnings("serial")
	private class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener, ComponentListener, KeyEventDispatcher {

		private static final int MILLIS_TO_WAIT_FOR_REPEAT = 5;

		public DrawingPanel() {

			addMouseListener(this);
			addMouseMotionListener(this);
			addMouseWheelListener(this);
			addComponentListener(this);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D aBrush = (Graphics2D) g;
			onDraw(aBrush);
		}

		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			//TODO: FOCUS
			if (!e.isConsumed() && KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this) {
				switch (e.getID()) {
				case KeyEvent.KEY_PRESSED:
					keyPressed(e);
					return true;
				case KeyEvent.KEY_RELEASED:
					queueKeyReleased(e);
					return true;
				case KeyEvent.KEY_TYPED:
					keyTyped(e);
					return true;
				}
			}
			return false;
		}

		@Override
		public void componentResized(ComponentEvent e) {
			if (_loaded) {
				Coord newSize = new Coord(this.getSize().width,this.getSize().height);
				onResize(newSize);
				repaint();
			}
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			onKeyTyped(e);
			//repaint();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			onKeyPressed(e);
			//repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			onKeyReleased(e);
			//repaint();
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			onMouseWheelMoved(e);
			//repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			onMouseDragged(e);
			//repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			onMouseMoved(e);
			//repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			onMouseClicked(e);
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			onMousePressed(e);
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			onMouseReleased(e);
			//repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// no operation
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// no operation
		}

		/*
		 * Fix X being dumb and sending keyrelease on every key repeat.
		 * Optimized+generalized version of poster Ekipur's solution at:
		 * http://bugs.sun.com/view_bug.do?bug_id=4153069
		 */

		java.util.List<RealReleaseWaiter> waiters = new ArrayList<RealReleaseWaiter>();

		private void queueKeyReleased(KeyEvent e) {
			waiters.add(new RealReleaseWaiter(e));
		}

		private class RealReleaseWaiter implements ActionListener {
			private boolean cancelled = false;
			private KeyEvent evt;
			private Timer t;

			public RealReleaseWaiter(KeyEvent evt) {
				this.evt = evt;
				t = new Timer(MILLIS_TO_WAIT_FOR_REPEAT, this);
				t.start();
			}


			public void cancel() {
				cancelled = true;
				t.stop();
				waiters.remove(this);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cancelled)
					return;
				cancel();
				keyReleased(evt);
			}

		}

	}


}