package MatrixConstruct;

import javax.swing.JFrame;

import matrixDraw.MatrixDraw;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame("Constructor");
		MConstructor mc = new MConstructor();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mc);
		frame.pack();
		frame.setVisible(true);
	}

}
