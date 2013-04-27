package frontend.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class Debug {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UIManager.put("SplitPane.dividerSize", 6);
		UIManager.put("SplitPane.background", Color.ORANGE);  
		UIManager.put("SplitPane.foreground", Color.ORANGE);  
		UIManager.put("SplitPane.highlight", Color.ORANGE);  
		UIManager.put("SplitPane.darkShadow", Color.ORANGE);  
		UIManager.put("SplitPane.shadow", Color.ORANGE);  
		
		
		JFrame frame = new JFrame("Linear Algebra Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		JPanel contentPanel = new JPanel(new BorderLayout());
		frame.setContentPane(contentPanel);
		
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBorder(BorderFactory.createEmptyBorder(3, 6, 3, 6)); //for padding around the title elements
		JLabel title = new JLabel("Linear Algebra Calculator");
		title.setFont(new Font("Dialog", Font.BOLD, 11));
		headerPanel.add(title,BorderLayout.CENTER);   
		JLabel closeButton = new JLabel("X");
		headerPanel.add(closeButton,BorderLayout.EAST);
		
		contentPanel.add(headerPanel, BorderLayout.NORTH);
		
//		JPanel mainPanel = new JPanel(new GridBagLayout());
//		mainPanel.setBackground(new Color(0x81A4E6)); //background of the entire program

		
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.PINK);
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.BLUE);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		splitPane.setResizeWeight(0.5);
		
		BasicSplitPaneUI ui = (BasicSplitPaneUI) splitPane.getUI();
		ui.getDivider().setBorder(BorderFactory.createLineBorder(Color.BLUE, 10));
		ui.getDivider().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		contentPanel.add(splitPane);

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
