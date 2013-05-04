package frontend.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		final NewLabel label = new NewLabel("This will change to user's value");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CustomDialog customDialog = new CustomDialog(frame, "create scalar", label, null);
				customDialog.pack();
				customDialog.setLocationRelativeTo(frame);
				customDialog.setVisible(true);
			}
		});
		
		panel.add(button, BorderLayout.CENTER);

		panel.add(label, BorderLayout.SOUTH);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

}

class NewLabel extends JLabel implements DialogListener {
	
	public NewLabel(String title) {
		super(title);
	}
	
	@Override
	public void doDialogReturn(Double d) {
		this.setText("User typed: " + d);
	}
}
