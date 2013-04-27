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

import frontend.panels.Construct;
import frontend.panels.Saved;


public class Main {

	/**
	 * alter look and feel here
	 */
	public static void main(String[] args) {
		
		UIManager.put("TabbedPane.font", new Font( "Dialog", Font.BOLD, 12 ));  
		
		UIManager.put("TabbedPane.focus", new Color(0,0,0,0)); //removes the ugly border around the header text when in focus
		UIManager.put("TabbedPane.selected", new Color(0x2A3A6E));  
		UIManager.put("TabbedPane.selectHighlight", new Color(0x2A3A6E)); //must be same as TabbedPane.selectHighlight to get rid of ugly inset
		UIManager.put("TabbedPane.selectedForeground", Color.WHITE);  
		UIManager.put("TabbedPane.contentBorderInsets", new Insets(5, 3, 3, 3)); //gets rid of the ugly light blue line in the content pane
		UIManager.put("TabbedPane.contentAreaColor", new Color(0x2A3A6E)); //gets rid of the ugly light blue line in the content pane
		UIManager.put("TabbedPane.darkShadow", new Color(0,0,0,0)); 
		UIManager.put("TabbedPane.foreground", Color.WHITE);  
		UIManager.put("TabbedPane.background", new Color(0x29477F));  
		UIManager.put("TabbedPane.light", new Color(0x29477F));  
		 
 	
		
		UIManager.put("TabbedPane.borderHighlightColor", Color.PINK);  
		UIManager.put("TabbedPane.highlight", Color.PINK);  
		UIManager.put("TabbedPane.tabInsets", new Insets(1, 8, 1, 8));  //sets padding within the tab header
		UIManager.put("TabbedPane.tabAreaInsets", new Insets(0, 2, 0, 0));  //sets the margin of the block of tab headers

		UIManager.put("TabbedPane.shadow", Color.PINK);  
		UIManager.put("TabbedPane.tabAreaBackground", Color.PINK);  
		
		JFrame frame = new JFrame("Linear Algebra Calculator");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(Color.LIGHT_GRAY);

		//Create Panels
		Saved savedPanel = new Saved();
		Construct constructPanel = new Construct(savedPanel); 
		Compute computePanel = new Compute();
		
		
		mainPanel.setBackground(new Color(0x81A4E6)); //background of the entire program

		frame.setUndecorated(false);
		mainPanel.setMinimumSize(new Dimension(500,500));
		frame.setContentPane(mainPanel);
		GridBagConstraints c = new GridBagConstraints();
		
		JTabbedPane tabbedPaneTopLeft = new JTabbedPane();
		JComponent topLeftPanel1 = makeTextPanel("Top Left Panel #1");
		topLeftPanel1.setBackground(new Color(0xE4E7F2));
		tabbedPaneTopLeft.addTab("Construct", constructPanel);

		JComponent topLeftPanel2 = makeTextPanel("Top Left Panel #2");
		topLeftPanel2.setBackground(new Color(0xE4E7F2));
		tabbedPaneTopLeft.addTab("Compute", computePanel);
		
		
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
		bottomLeftPanel1.setBackground(new Color(0xE4E7F2));
		tabbedPaneBottomLeft.addTab("Saved Matrices", savedPanel);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0.5;
		c.gridheight=1;
		c.insets = new Insets(20,20,20,0);
		mainPanel.add(tabbedPaneBottomLeft, c);
		
		
		JTabbedPane tabbedPaneRight = new JTabbedPane();
		JComponent tabbedPaneRightPanel1 = makeTextPanel("Right Panel #1");
		tabbedPaneRightPanel1.setBackground(new Color(0xE4E7F2));
		tabbedPaneRight.addTab("Solution", tabbedPaneRightPanel1);
		JComponent tabbedPaneRightPanel2 = makeTextPanel("Right Panel #2");
		tabbedPaneRightPanel2.setBackground(new Color(0xE4E7F2));
		tabbedPaneRight.addTab("Step-By-Step", tabbedPaneRightPanel2);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight=3;
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
	
	private static JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel();
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

}
