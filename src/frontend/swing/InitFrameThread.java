package frontend.swing;


public class InitFrameThread implements Runnable {
	
	private InitFrame _initFrame;
	
	public InitFrameThread() {
		_initFrame = new InitFrame();
	}

	@Override
	public void run() {
		_initFrame.setVisible(true);
	}
	
	public void kill() {
		_initFrame.setVisible(false);
		_initFrame.dispose();
	}
	
	
}
