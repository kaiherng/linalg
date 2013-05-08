package frontend.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import frontend.panels.Compute;
import frontend.panels.Construct;
import frontend.panels.Saved;
import frontend.panels.SavedScroll;
import frontend.panels.SolutionScroll;
import frontend.panels.StepSolution;
import frontend.utils.CustomLayerUI;
import frontend.utils.FadeLayerUI;
import frontend.utils.ResizeAdapter;

/**
 * Our JFrame
 * @author kloh
 *
 */
@SuppressWarnings("serial")
public class AppFrame extends JFrame {
	
	private CustomLayerUI _customLayerUI;
	
	public CustomLayerUI getUILayer() {
		return _customLayerUI;
	}
	
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
		StepSolution stepSol = new StepSolution(this);
		SolutionScroll stepPanel = new SolutionScroll();
		Compute computePanel = new Compute(stepSol, stepPanel.getSolPanel());
		SavedScroll savedPanel = new SavedScroll(computePanel);
		Construct constructPanel = new Construct(savedPanel.getSavedPanel(), this);
		savedPanel.getSavedPanel().setConstructPanel(constructPanel);
		stepSol.setSavedPanel(savedPanel.getSavedPanel());
		
		//create TabbedPanes and Left/Right Panels for SplitPane
		JTabbedPane tabbedPaneTopLeft = createTabbedPaneTopLeft(constructPanel, computePanel);		
		savedPanel.getSavedPanel().setTopLeftPane(tabbedPaneTopLeft); //set the pane to check when adding matrices
		JTabbedPane tabbedPaneBottomLeft = createTabbedPaneBottomLeft(savedPanel);
		JPanel leftPanel = createLeftPanel(tabbedPaneTopLeft, tabbedPaneBottomLeft);
		JTabbedPane tabbedPaneRight = createTabbedPaneRight(stepSol);
		stepSol.setTabbedPane(tabbedPaneRight);
		JPanel rightPanel = createRightPanel(tabbedPaneRight);
		
		//create splitPane
		SplitPane splitPane = new SplitPane(leftPanel, rightPanel);

		new ResizeAdapter(splitPane, this, CurrentConstants.RESIZE_THRESHOLD, SwingUtilities.SOUTH, SwingUtilities.EAST, SwingUtilities.WEST, SwingUtilities.SOUTH_EAST, SwingUtilities.SOUTH_WEST);
		
		ContentPane contentPane = new ContentPane(this);
		contentPane.add(splitPane);
		
		//this is so that we can fade the rest of the program later
		//create a BlurLayerUI instead for blur
//		_customLayerUI = new WhiteLayerUI(this);
//		_customLayerUI = new BlurLayerUI(this);
		_customLayerUI = new FadeLayerUI(this);
		final JLayer<JComponent> jLayer = new JLayer<JComponent>(contentPane, _customLayerUI);
		
		this.add(jLayer);
		
		
	}
	
	public final static JTabbedPane createTabbedPaneTopLeft(Construct constructPanel, Compute computePanel) {
		JTabbedPane tabbedPaneTopLeft = new JTabbedPane();
		
		//change listeners
		tabbedPaneTopLeft.addChangeListener(new TopLeftTabListener(constructPanel.getSavedPanel()));
		
		tabbedPaneTopLeft.addTab("Construct", constructPanel);
		tabbedPaneTopLeft.setToolTipTextAt(0,"Create a new matrix");
		
		tabbedPaneTopLeft.addTab("Compute", computePanel);
		tabbedPaneTopLeft.setToolTipTextAt(1,"Select computations to perform");
		return tabbedPaneTopLeft;
	}
	
	public final static JTabbedPane createTabbedPaneBottomLeft(SavedScroll savedPanel) {
		JTabbedPane tabbedPaneBottomLeft = new JTabbedPane();
		tabbedPaneBottomLeft.addTab("Saved Matrices", savedPanel);
		tabbedPaneBottomLeft.setToolTipTextAt(0,"All your saved matrices are stored here");
		return tabbedPaneBottomLeft;
	}
	
	public final static JTabbedPane createTabbedPaneRight(StepSolution solPanel) {
		JTabbedPane tabbedPaneRight = new JTabbedPane();
		tabbedPaneRight.addTab("Solution", solPanel);
		tabbedPaneRight.setToolTipTextAt(0,"View the solution for your computation");
		
		return tabbedPaneRight;
	}
	
	public final static JPanel createLeftPanel(JTabbedPane tabbedPaneTopLeft, JTabbedPane tabbedPaneBottomLeft) {
		JPanel leftPanel = new JPanel(new GridBagLayout());
		leftPanel.setBackground(CurrentConstants.LEFT_RIGHT_PANEL_BG);
		leftPanel.setMinimumSize(CurrentConstants.LEFT_PANEL_MIN_SIZE);
		leftPanel.setPreferredSize(CurrentConstants.LEFT_PANEL_PREFERRED_SIZE);
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

	
	private static class TopLeftTabListener implements ChangeListener{
		Saved _s;
		public TopLeftTabListener(Saved s){
			_s = s;
		}
		@Override
		public void stateChanged(ChangeEvent arg0) {
			// TODO Auto-generated method stub
            JTabbedPane pane = (JTabbedPane) arg0.getSource();
			if(pane.getSelectedIndex() == 0){
				_s.showEditing();
			} else {
				_s.hideEditing();
			}
		}
	}
}
