package frontend.swing;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.Border;

import frontend.panels.Construct;
import frontend.panels.Saved;


public class Main {

	/**
	 * alter look and feel here
	 */
	public static void main(String[] args) {
		
		UIManager.put("TabbedPane.font", new Font( "Dialog", Font.BOLD, 12 ));  
		JFrame frame = new JFrame("Hello");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(Color.LIGHT_GRAY);

		//Create Panels
		Saved savedPanel = new Saved();
		Construct constructPanel = new Construct(savedPanel); 
		
		
		mainPanel.setMinimumSize(new Dimension(500,500));
		frame.setContentPane(mainPanel);
		GridBagConstraints c = new GridBagConstraints();
		
		JTabbedPane tabbedPaneTopLeft = new JTabbedPane();
		//JComponent topLeftPanel1 = makeTextPanel("Top Left Panel #1");
		tabbedPaneTopLeft.addTab("Top Left Tab 1", constructPanel);
		tabbedPaneTopLeft.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent topLeftPanel2 = makeTextPanel("Top Left Panel #2");
		tabbedPaneTopLeft.addTab("Top Left Tab 2", topLeftPanel2);
		tabbedPaneTopLeft.setMnemonicAt(0, KeyEvent.VK_1);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridheight=1;
		c.insets = new Insets(20,20,0,0);
		mainPanel.add(tabbedPaneTopLeft, c);
		
		
		JTabbedPane tabbedPaneBottomLeft = new JTabbedPane();
		JComponent bottomLeftPanel1 = makeTextPanel("Bottom Left Panel #1");
		//tabbedPaneBottomLeft.addTab("Bottom Left Tab 1", bottomLeftPanel1);
		tabbedPaneBottomLeft.addTab("Bottom Left Tab 1", savedPanel);
		tabbedPaneBottomLeft.setMnemonicAt(0, KeyEvent.VK_1);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(20,20,20,0);
		mainPanel.add(tabbedPaneBottomLeft, c);
		
		
		JTabbedPane tabbedPaneRight = new JTabbedPane();
		JComponent tabbedPaneRightPanel1 = makeTextPanel("Right Panel #1");
		tabbedPaneRight.addTab("Right Tab 1", tabbedPaneRightPanel1);
		tabbedPaneRight.setMnemonicAt(0, KeyEvent.VK_1);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight=2;
		c.insets = new Insets(20,20,20,20);
		mainPanel.add(tabbedPaneRight, c);
		
		//so the focus wont be on the tab itself but on the panel instead
//		tabbedPaneTopLeft.setFocusable(false);
//		tabbedPaneBottomLeft.setFocusable(false);
//		tabbedPaneRight.setFocusable(false);

		
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
