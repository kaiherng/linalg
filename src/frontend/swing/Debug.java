package frontend.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class Debug {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UIManager.put("TabbedPane.font", new Font( "Dialog", Font.BOLD, 12 ));  
		JFrame frame = new JFrame("Hello");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(Color.LIGHT_GRAY);

		
		mainPanel.setMinimumSize(new Dimension(500,500));
		frame.setContentPane(mainPanel);
		GridBagConstraints c = new GridBagConstraints();
		
		JTabbedPane tabbedPaneTopLeft = new JTabbedPane();
		JComponent topLeftPanel1 = makeTextPanel("Top Left Panel #1");
		tabbedPaneTopLeft.addTab("Construct", topLeftPanel1);

		JComponent topLeftPanel2 = makeTextPanel("Top Left Panel #2");
		tabbedPaneTopLeft.addTab("Compute", topLeftPanel2);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridheight=2;
		c.insets = new Insets(20,20,0,0);
		mainPanel.add(tabbedPaneTopLeft, c);
		
		
		JTabbedPane tabbedPaneBottomLeft = new JTabbedPane();
		JComponent bottomLeftPanel1 = makeTextPanel("Bottom Left Panel #1");
		tabbedPaneBottomLeft.addTab("Saved Matrices", bottomLeftPanel1);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight=1;
		c.weighty = 0.5;
		c.insets = new Insets(20,20,20,0);
		mainPanel.add(tabbedPaneBottomLeft, c);
		
		frame.setMinimumSize(new Dimension(1000,700));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	protected static JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel();
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

}
