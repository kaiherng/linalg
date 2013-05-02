package frontend.swing;
import java.awt.Insets;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class Main {
	
	/**
	 * Sets up the design of the components to the look we want to achieve
	 */
	public static void setUpDesign() {
		UIManager.put("SplitPane.dividerSize", Constants.SPLITPANE_WIDTH);
		UIManager.put("SplitPane.background", Constants.SPLITPANE_BG);
		UIManager.put("TabbedPane.font", Constants.TAB_FONT);  		
		UIManager.put("TabbedPane.focus", Constants.TAB_FOCUS); //removes the ugly border around the header text when in focus
		UIManager.put("TabbedPane.selected", Constants.TAB_SELECTED);  
		UIManager.put("TabbedPane.selectHighlight", Constants.TAB_SELECTED_HIGHLIGHT); 
		UIManager.put("TabbedPane.selectedForeground", Constants.TAB_SELECTED_FG);  
		UIManager.put("TabbedPane.contentBorderInsets", new Insets(5, 3, 3, 3)); //gets rid of the ugly light blue line in the content pane
		UIManager.put("TabbedPane.contentAreaColor", Constants.TAB_CONTENT_AREA); //gets rid of the ugly light blue line in the content pane
		UIManager.put("TabbedPane.darkShadow", Constants.TAB_DARK_SHADOW); 
		UIManager.put("TabbedPane.foreground", Constants.TAB_FG);  
		UIManager.put("TabbedPane.background", Constants.TAB_BG);  
		UIManager.put("TabbedPane.light", Constants.TAB_LIGHT);  
		UIManager.put("TabbedPane.tabsOverlapBorder", true);
		
		UIManager.put("TabbedPane.tabInsets", new Insets(1, 8, 1, 8));  //sets padding within the tab header
		UIManager.put("TabbedPane.tabAreaInsets", new Insets(0, 2, -1, 0));  //sets the margin of the block of tab headers
	}
	
	public static void main(String[] args) {
		setUpDesign();
		SwingUtilities.invokeLater(new Runnable() { //as per best practice for concurrency in swing - see http://docs.oracle.com/javase/tutorial/uiswing/concurrency/
			@Override
			public void run() {
				final AppFrame appFrame = new AppFrame();
				appFrame.setVisible(true);
			}
		});
	}
	

}
