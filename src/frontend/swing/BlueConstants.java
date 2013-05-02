package frontend.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class BlueConstants {
	
	public static final Color FRAME_BORDER_COLOR = Color.DARK_GRAY;
	public static final int FRAME_BORDER_WIDTH = 1;
	public static final Dimension FRAME_MIN_SIZE = new Dimension(1000,700);
	public static final int RESIZE_THRESHOLD = 10;
	
	public static final Color HEADER_BG = new Color(0x1D4673);
	public static final Color HEADER_TITLE_COLOR = Color.WHITE;
	public static final Font HEADER_TITLE_FONT = new Font("Dialog", Font.BOLD, 11);
	public static final Font HEADER_MAX_BUTTON_FONT = new Font("Dialog", Font.BOLD, 14);
	public static final Font HEADER_CLOSE_BUTTON_FONT = new Font("Dialog", Font.BOLD, 14);
	public static final Font HEADER_MIN_BUTTON_FONT = new Font("Dialog", Font.BOLD, 14);
	public static final Border HEADER_BORDER = BorderFactory.createEmptyBorder(0, 7, 0, 7);
	public static final Border HEADER_MIN_BUTTON_BORDER = BorderFactory.createEmptyBorder(0,0,6,7);
	public static final Border HEADER_MAX_BUTTON_BORDER = BorderFactory.createEmptyBorder(0,0,2,7);
	
	public static final Color SPLITPANE_BG = new Color(0x1D4673);
	public static final int SPLITPANE_WIDTH = 5;
	public static final Color LEFT_RIGHT_PANEL_BG = new Color(0x81A4E6);
	public static final Dimension LEFT_PANEL_MIN_SIZE = new Dimension(300,300);
	public static final Dimension RIGHT_PANEL_MIN_SIZE = new Dimension(300,300);
	
	public static final Font TAB_FONT = new Font("Dialog", Font.BOLD, 12);	
	public static final Color TAB_SELECTED = new Color(0x2A3A6E);
	public static final Color TAB_SELECTED_HIGHLIGHT = new Color(0x2A3A6E);
	public static final Color TAB_SELECTED_FG = Color.WHITE;
	public static final Color TAB_CONTENT_AREA = new Color(0x2A3A6E);
	public static final Insets TAB_CONTENT_BORDER_INSETS = new Insets(5, 3, 3, 3);
	public static final Color TAB_FG = Color.WHITE;
	public static final Color TAB_BG = new Color(0x29477F);
	public static final Color TAB_LIGHT = new Color(0x29477F);
	public static final Color TAB_DARK_SHADOW = new Color(0,0,0,0);
	public static final Color TAB_FOCUS = new Color(0,0,0,0);
	public static final Insets TAB_INSETS = new Insets(1, 8, 1, 8);
	public static final Insets TAB_AREA_INSETS = new Insets(0, 2, -1, 0);
	public static final boolean TAB_TABS_OVERLAP_BORDER = true;
	
	public static final Color BRACKET_BLOCK_BG = Color.blue;
	public static final Color BRACKET_BLOCK_FG = Color.white;
	public static final Dimension BRACKET_BLOCK_SIZE = new Dimension(60,60);
	
	public static final Dimension COUNTABLE_BLOCK_SIZE = new Dimension(40, 40);
	public static final Color COUNTABLE_BLOCK_BG = Color.blue;	
	public static final Color COUNTABLE_BLOCK_DELETE_FG = Color.white;
	public static final Font COUNTABLE_BLOCK_LABEL_MATRIX_FONT = new Font("SansSerif", Font.BOLD, 20);
	public static final Font COUNTABLE_BLOCK_LABEL_SCALAR_FONT = new Font("SansSerif", Font.BOLD, 16);
	public static final Color COUNTABLE_BLOCK_LABEL_FG = Color.white;
	public static final Rectangle COUNTABLE_BLOCK_DELETE_BOUNDS = new Rectangle(30,0,10,10);
	public static final Rectangle COUNTABLE_BLOCK_LABEL_BOUNDS = new Rectangle(0,5,40,30);
	
	public static final Color OP_BLOCK_FG = Color.white;
	public static final Color OP_BLOCK_BG = Color.blue;
	public static final Dimension OP_BLOCK_SIZE = new Dimension(60,60);
	
	public static final Border CONSTRUCT_BUTTON_PANEL_BORDER = BorderFactory.createLineBorder(Color.black, 1);
	
	
	
}
