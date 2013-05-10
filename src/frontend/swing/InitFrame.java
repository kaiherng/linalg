package frontend.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Our very cool splash screen
 * @author Kai
 *
 */
@SuppressWarnings("serial")
public class InitFrame extends JFrame {
	
	
	private static Font loadFontBlack() throws FontFormatException, IOException {
		 File file = new File("fonts/Overlock-Black.ttf");
		 return Font.createFont(Font.TRUETYPE_FONT, file);
	 }
	
	private static Font loadFont() throws FontFormatException, IOException {
		 File file = new File("fonts/Overlock-Bold.ttf");
		 return Font.createFont(Font.TRUETYPE_FONT, file);
	 }
	
	public InitFrame() {
		super("Linear Algebra Calculator");
		setUndecorated(true);
		setMinimumSize(CurrentConstants.INIT_FRAME_SIZE);
		setBackground(CurrentConstants.INIT_FRAME_BG);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setBackground(new Color(0,0,0,0));
		
		Font fontBlack = new Font("Serif", Font.BOLD, 33);
		try {
			fontBlack = loadFontBlack();
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(fontBlack);
		} catch (Exception e) {
		}
		
		Font font = new Font("Serif", Font.PLAIN, 25);
		try {
			font = loadFont();
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (Exception e) {
		}
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(0,0,0,0));
		centerPanel.setBorder(new EmptyBorder(100,50,50,50));
		JLabel title = new JLabel("Linear Algebra Calculator", SwingConstants.CENTER);
		title.setFont(fontBlack.deriveFont(33.0f));
		title.setBackground(new Color(0,0,0,0));
		centerPanel.add(title, BorderLayout.CENTER);
		
		JLabel subtitle = new JLabel("Never Do It By Hand Again", SwingConstants.CENTER);
		subtitle.setFont(font.deriveFont(25.0f));
		subtitle.setBackground(new Color(0,0,0,0));
		centerPanel.add(subtitle, BorderLayout.SOUTH);

		centerPanel.setSize(new Dimension(300,100));
		centerPanel.setPreferredSize(new Dimension(300,100));
		centerPanel.setMaximumSize(new Dimension(300,100));
		
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
	
	}

}
