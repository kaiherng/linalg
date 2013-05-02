package frontend.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import frontend.panels.Compute;
import frontend.panels.Construct;
import frontend.panels.Saved;
import frontend.panels.SolutionScroll;
import frontend.utils.ResizeAdapter;

/**
 * Our JFrame
 * @author kloh
 *
 */
@SuppressWarnings("serial")
public class AppFrame extends JFrame {
	
	public void setUpDesign() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setMinimumSize(CurrentConstants.FRAME_MIN_SIZE);
		setLocationRelativeTo(null);
	}

	public AppFrame() {
		super("Linear Algebra Calculator");
		setUpDesign();
		
		//Create Panels
		SolutionScroll solPanel = new SolutionScroll();
		SolutionScroll stepPanel = new SolutionScroll();
		Compute computePanel = new Compute(solPanel.getSolPanel(), stepPanel.getSolPanel());
		Saved savedPanel = new Saved(computePanel);
		Construct constructPanel = new Construct(savedPanel);
		savedPanel.setConstructPanel(constructPanel);
		
		//create TabbedPanes and Left/Right Panels for SplitPane
		JTabbedPane tabbedPaneTopLeft = createTabbedPaneTopLeft(constructPanel, computePanel);		
		savedPanel.setTopLeftPane(tabbedPaneTopLeft); //set the pane to check when adding matrices
		JTabbedPane tabbedPaneBottomLeft = createTabbedPaneBottomLeft(savedPanel);
		JPanel leftPanel = createLeftPanel(tabbedPaneTopLeft, tabbedPaneBottomLeft);
		JTabbedPane tabbedPaneRight = createTabbedPaneRight(solPanel, stepPanel);
		JPanel rightPanel = createRightPanel(tabbedPaneRight);
		
		//create splitPane
		SplitPane splitPane = new SplitPane(leftPanel, rightPanel);

		new ResizeAdapter(splitPane, this, CurrentConstants.RESIZE_THRESHOLD, SwingUtilities.SOUTH, SwingUtilities.EAST, SwingUtilities.WEST, SwingUtilities.SOUTH_EAST, SwingUtilities.SOUTH_WEST);
		
		ContentPane contentPane = new ContentPane(this);
		setContentPane(contentPane);
		contentPane.add(splitPane);
	}
	
	public final static JTabbedPane createTabbedPaneTopLeft(Construct constructPanel, Compute computePanel) {
		JTabbedPane tabbedPaneTopLeft = new JTabbedPane();
		tabbedPaneTopLeft.addTab("Construct", constructPanel);
		tabbedPaneTopLeft.setToolTipTextAt(0,"Create a new matrix");
		
		tabbedPaneTopLeft.addTab("Compute", computePanel);
		tabbedPaneTopLeft.setToolTipTextAt(1,"Select computations to perform");
		return tabbedPaneTopLeft;
	}
	
	public final static JTabbedPane createTabbedPaneBottomLeft(Saved savedPanel) {
		JTabbedPane tabbedPaneBottomLeft = new JTabbedPane();
		tabbedPaneBottomLeft.addTab("Saved Matrices", savedPanel);
		tabbedPaneBottomLeft.setToolTipTextAt(0,"All your saved matrices are stored here");
		return tabbedPaneBottomLeft;
	}
	
	public final static JTabbedPane createTabbedPaneRight(SolutionScroll solPanel, SolutionScroll stepPanel) {
		JTabbedPane tabbedPaneRight = new JTabbedPane();
		tabbedPaneRight.addTab("Solution", solPanel);
		
		tabbedPaneRight.setToolTipTextAt(0,"Quick solution for your computation");
		tabbedPaneRight.addTab("Step-By-Step", stepPanel);
		
		tabbedPaneRight.setToolTipTextAt(1,"View the steps to arrive at the solution");
		return tabbedPaneRight;
	}
	
	public final static JPanel createLeftPanel(JTabbedPane tabbedPaneTopLeft, JTabbedPane tabbedPaneBottomLeft) {
		JPanel leftPanel = new JPanel(new GridBagLayout());
		leftPanel.setBackground(CurrentConstants.LEFT_RIGHT_PANEL_BG);
		leftPanel.setMinimumSize(CurrentConstants.LEFT_PANEL_MIN_SIZE);
		leftPanel.setBorder(BorderFactory.createEmptyBorder());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridheight=2;
		c.insets = new Insets(20,20,0,20);
		
		leftPanel.add(tabbedPaneTopLeft, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0.5;
		c.gridheight=1;
		c.insets = new Insets(20,20,20,20);
		leftPanel.add(tabbedPaneBottomLeft, c);
		
		return leftPanel;
	}
	
	public final static JPanel createRightPanel(JTabbedPane tabbedPaneRight) {
		JPanel rightPanel = new JPanel(new GridBagLayout());
		rightPanel.setBackground(CurrentConstants.LEFT_RIGHT_PANEL_BG);
		rightPanel.setMinimumSize(CurrentConstants.RIGHT_PANEL_MIN_SIZE);
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.BOTH;
		c2.gridx = 0;
		c2.gridy = 0;
		c2.weightx = 0.5;
		c2.weighty = 1;
		c2.gridheight=2;
		c2.insets = new Insets(20,20,20,20);
		rightPanel.add(tabbedPaneRight, c2);
		
		return rightPanel;
	}

}
