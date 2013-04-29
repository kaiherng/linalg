package frontend.swing;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import frontend.panels.Compute;
import frontend.panels.Construct;
import frontend.panels.Saved;
import frontend.panels.Solution;
import frontend.panels.SolutionScroll;


public class Main {
	
	
	public static void main(String[] args) {
				
		UIManager.put("SplitPane.dividerSize", Constants.SPLITPANE_WIDTH);
		UIManager.put("SplitPane.background", Constants.SPLITPANE_BG);
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
		 
		UIManager.put("TabbedPane.tabsOverlapBorder", true);
		
		UIManager.put("TabbedPane.tabInsets", new Insets(1, 8, 1, 8));  //sets padding within the tab header
		UIManager.put("TabbedPane.tabAreaInsets", new Insets(0, 2, -1, 0));  //sets the margin of the block of tab headers
		
		JFrame frame = new JFrame("Linear Algebra Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		JPanel contentPane = new JPanel(new BorderLayout());
//		
//		Border padding = BorderFactory.createLineBorder(Constants.FRAME_BORDER_COLOR,5);
//		contentPane.setBorder(padding);
		
		frame.setContentPane(contentPane);
		
		HeaderPanel headerPanel = new HeaderPanel(frame);
		contentPane.add(headerPanel, BorderLayout.NORTH);
		
		JPanel leftPanel = new JPanel(new GridBagLayout());
		leftPanel.setBackground(Constants.PANEL_BG);
		leftPanel.setMinimumSize(new Dimension(300,300));
		JPanel rightPanel = new JPanel(new GridBagLayout());
		rightPanel.setBackground(Constants.PANEL_BG);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		splitPane.setResizeWeight(0.5);
		leftPanel.setBorder(BorderFactory.createEmptyBorder());
		
		BasicSplitPaneUI ui = (BasicSplitPaneUI) splitPane.getUI();
		splitPane.setBorder(BorderFactory.createEmptyBorder());
		ui.getDivider().setBorder(BorderFactory.createLineBorder(Constants.SPLITPANE_BG, 10));
		ui.getDivider().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(Color.LIGHT_GRAY);

		new ResizeAdapter(splitPane, frame, 20, SwingUtilities.SOUTH, SwingUtilities.EAST, SwingUtilities.WEST);

		//Create Panels
		Solution solPanel = new Solution();
		SolutionScroll stepPanel = new SolutionScroll();
		Compute computePanel = new Compute(solPanel, stepPanel.getSolPanel());
		Saved savedPanel = new Saved(computePanel);
		Construct constructPanel = new Construct(savedPanel);
		savedPanel.setConstructPanel(constructPanel);
		
		contentPane.add(splitPane);

		GridBagConstraints c = new GridBagConstraints();
		
		JTabbedPane tabbedPaneTopLeft = new JTabbedPane();
		
		tabbedPaneTopLeft.addTab("Construct", constructPanel);
		tabbedPaneTopLeft.setToolTipTextAt(0,"Create a new matrix");
		
		tabbedPaneTopLeft.addTab("Compute", computePanel);
		tabbedPaneTopLeft.setToolTipTextAt(1,"Select computations to perform");
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridheight=2;
		c.insets = new Insets(20,20,0,20);
		leftPanel.add(tabbedPaneTopLeft, c);
		
		JTabbedPane tabbedPaneBottomLeft = new JTabbedPane();
		JComponent bottomLeftPanel1 = makeTextPanel("Saved Matrices");
		bottomLeftPanel1.setBackground(Constants.TAB_PANEL_CONTENT_BG);
		//set the pane to check when adding matrices
		savedPanel.setTopLeftPane(tabbedPaneTopLeft);
		tabbedPaneBottomLeft.addTab("Saved Matrices", savedPanel);
		tabbedPaneBottomLeft.setToolTipTextAt(0,"All your saved matrices are stored here");
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0.5;
		c.gridheight=1;
		c.insets = new Insets(20,20,20,20);
		leftPanel.add(tabbedPaneBottomLeft, c);
	
		JTabbedPane tabbedPaneRight = new JTabbedPane();
		tabbedPaneRight.addTab("Solution", solPanel);
		
		tabbedPaneRight.setToolTipTextAt(0,"Quick solution for your computation");
		tabbedPaneRight.addTab("Step-By-Step", stepPanel);
		
		tabbedPaneRight.setToolTipTextAt(1,"View the steps to arrive at the solution");
		rightPanel.setMinimumSize(new Dimension(300,300));
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.BOTH;
		c2.gridx = 0;
		c2.gridy = 0;
		c2.weightx = 0.5;
		c2.weighty = 1;
		c2.gridheight=2;
		c2.insets = new Insets(20,20,20,20);
		
		rightPanel.add(tabbedPaneRight, c2);
//		rightPanel.add(stepPanel, c2);

		
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
