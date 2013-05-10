package frontend.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;

import frontend.utils.CustomLayerUI;
import frontend.utils.WhiteLayerUI;

/**
 * Not used in the actual program. This is for debugging if and when we need to test look and feel of swing components
 * @author Kai
 *
 */
public class Debug {
	
	public static void main(String[] args) {
		final JFrame frame = new JFrame("Hey");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(500,500));
		panel.setPreferredSize(new Dimension(400,400));
		panel.setBackground(Color.pink);
		JButton button = new JButton("CLICK HERE");
		final NewLabel label = new NewLabel("This will change to user's value");
		JButton extraButton = new JButton("Second");

		
		panel.add(button, BorderLayout.CENTER);

		panel.add(label, BorderLayout.SOUTH);
		panel.add(extraButton, BorderLayout.EAST);
		
		final CustomLayerUI customLayerUI = new WhiteLayerUI(frame);
		final JLayer<JComponent> jLayer = new JLayer<JComponent>(panel, customLayerUI);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				CustomDialog customDialog = new CustomDialog(frame, "create scalar", label, null, customLayerUI);
//				customDialog.pack();
//				customDialog.setLocationRelativeTo(frame);
//				customDialog.setVisible(true);
			}
		});
		
		frame.add(jLayer);
		
		frame.pack();
		frame.setVisible(true);
	}

}

@SuppressWarnings("serial")
class NewLabel extends JLabel implements DialogListener {
	
	public NewLabel(String title) {
		super(title);
	}
	
	@Override
	public void doDialogReturn(Double d) {
		this.setText("User typed: " + d);
	}
}
