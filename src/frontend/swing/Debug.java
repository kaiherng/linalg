package frontend.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Debug {
	
	public static void main(String[] args) {
		final JFrame frame = new JFrame("Hey");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(500,500));
		panel.setPreferredSize(new Dimension(500,500));
		panel.setBackground(Color.pink);
		JButton button = new JButton("CLICK HERE");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
//				CustomDialog customDialog = new CustomDialog(frame, "create scalar", this);
//				customDialog.pack();
//				customDialog.setLocationRelativeTo(frame);
//				customDialog.setVisible(true);
			}
		});
		panel.add(button, BorderLayout.CENTER);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

}
