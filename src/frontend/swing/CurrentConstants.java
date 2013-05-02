package frontend.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

//http://colorschemedesigner.com/ 
//Scheme ID: 3z41Tw0w0w0w0

//top - left - bottom - right

public class CurrentConstants {
	
	public static final Dimension INIT_FRAME_SIZE = new Dimension(500,300);
	public static final Color INIT_FRAME_BG = new Color(0x85A8D6);
	
	public static final String FONT_STYLE = "Dialog";
	
	public static final Color FRAME_BORDER_COLOR = Color.BLACK;
	public static final int FRAME_BORDER_WIDTH = 1;
	public static final Dimension FRAME_MIN_SIZE = new Dimension(1000,700);
	public static final int RESIZE_THRESHOLD = 10;
	
	public static final Color HEADER_BG = new Color(0x1C4C75);
	public static final Color HEADER_TITLE_COLOR = Color.WHITE;
	public static final Font HEADER_TITLE_FONT = new Font(FONT_STYLE, Font.BOLD, 11);
	public static final Font HEADER_MAX_BUTTON_FONT = new Font(FONT_STYLE, Font.BOLD, 14);
	public static final Font HEADER_CLOSE_BUTTON_FONT = new Font(FONT_STYLE, Font.BOLD, 14);
	public static final Font HEADER_MIN_BUTTON_FONT = new Font(FONT_STYLE, Font.BOLD, 14);
	public static final Border HEADER_BORDER = BorderFactory.createEmptyBorder(0, 7, 0, 7);
	public static final Border HEADER_MIN_BUTTON_BORDER = BorderFactory.createEmptyBorder(0,0,6,7);
	public static final Border HEADER_MAX_BUTTON_BORDER = BorderFactory.createEmptyBorder(0,0,2,7);
	
	public static final Color SPLITPANE_BG = new Color(0x1C4C75);
	public static final int SPLITPANE_WIDTH = 6;
	public static final Color LEFT_RIGHT_PANEL_BG = new Color(0x5881A3);
	public static final Dimension LEFT_PANEL_MIN_SIZE = new Dimension(300,300);
	public static final Dimension RIGHT_PANEL_MIN_SIZE = new Dimension(300,300);
	
	public static final Font TAB_FONT = new Font(FONT_STYLE, Font.BOLD, 11);	
	public static final Color TAB_SELECTED = new Color(0x22385C); //the color of the tab when its selected
	public static final Color TAB_SELECTED_HIGHLIGHT = new Color(0x5279A3); //the top and left borders of the tab when selected
	public static final Color TAB_SELECTED_FG = Color.WHITE;
	public static final Color TAB_CONTENT_AREA = new Color(0xBBCFF2);
	public static final Insets TAB_CONTENT_BORDER_INSETS = new Insets(0, 0, 0, 0); //the border of the content pane itself
	public static final Color TAB_FG = Color.WHITE;
	public static final Color TAB_BG = new Color(0x29477F);
	public static final Color TAB_LIGHT = new Color(0x1C376B);
	public static final Color TAB_DARK_SHADOW = new Color(0,0,0,0); //top and right border of the unselected tab, and also the right and bottom of selected tab
	public static final Color TAB_FOCUS = new Color(0,0,0,0);  //DONT CHANGE THIS removes the ugly border around the header text when in focus
	public static final Insets TAB_INSETS = new Insets(2, 10, 2, 10); //sets padding within the tab header
	public static final Insets TAB_AREA_INSETS = new Insets(0, 0, 0, 0); //sets the margin of the block of tab headers
	public static final boolean TAB_TABS_OVERLAP_BORDER = true;
	
	public static final Color BRACKET_BLOCK_BG = new Color(0x3C4C78);
	public static final Color BRACKET_BLOCK_FG = Color.white;
	public static final Dimension BRACKET_BLOCK_SIZE = new Dimension(60,60);
	
	public static final Dimension COUNTABLE_BLOCK_SIZE = new Dimension(40, 40);
	public static final Color COUNTABLE_BLOCK_BG = new Color(0x3C4C78);	
	public static final Color COUNTABLE_BLOCK_DELETE_FG = Color.white;
	public static final Font COUNTABLE_BLOCK_LABEL_MATRIX_FONT = new Font(FONT_STYLE, Font.BOLD, 20);
	public static final Font COUNTABLE_BLOCK_LABEL_SCALAR_FONT = new Font(FONT_STYLE, Font.BOLD, 16);
	public static final Color COUNTABLE_BLOCK_LABEL_FG = Color.white;
	public static final Rectangle COUNTABLE_BLOCK_DELETE_BOUNDS = new Rectangle(30,0,10,10);
	public static final Rectangle COUNTABLE_BLOCK_LABEL_BOUNDS = new Rectangle(0,5,40,30);
	
	public static final Color OP_BLOCK_FG = Color.white;
	public static final Color OP_BLOCK_BG = new Color(0x365D91);
	public static final Dimension OP_BLOCK_SIZE = new Dimension(60,60);
	
	public static final Color CONSTRUCT_BG = new Color(0,0,0,0);
	public static final Border CONSTRUCT_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color CONSTRUCT_BUTTON_PANEL_BG = new Color(0,0,0,0);
	public static final Border CONSTRUCT_BUTTON_PANEL_BORDER = new EmptyBorder(0,0,0,0);
	
	public static final Color CONSTRUCT_GRID_DARK = Color.BLACK;
	public static final Color CONSTRUCT_GRID_MEDIUM = new Color(0xBBBBBB);
	public static final Color CONSTRUCT_GRID_LIGHT = new Color(0xCCCCCC);
	
	public static final Color COMPUTE_BAR_BG = new Color(0,0,0,0);
	public static final Border COMPUTE_BAR_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color COMPUTE_OPS_BG = new Color(0xBBCFF2);
	public static final Border COMPUTE_OPS_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color COMPUTE_PAGES_BG = new Color(0x85A8D6);
	public static final Border COMPUTE_PAGES_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color COMPUTE_BUTTON_PANEL_BG = new Color(0x4B7DBF);
	public static final Border COMPUTE_BUTTON_PANEL_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color COMPUTE_BAR_SCROLLPANE_BAR_BG = new Color(0,0,0,0);
	public static final Border COMPUTE_BAR_SCROLLPANE_BAR_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color COMPUTE_BAR_SCROLLPANEL_BG = new Color(0,0,0,0);
	public static final Border COMPUTE_BAR_SCROLLPANEL_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color COMPUTE_BAR_SCROLL_PANE_BG = new Color(0,0,0,0);
	public static final Border COMPUTE_BAR_SCROLL_PANE_BORDER = new EmptyBorder(0,0,0,0);	
	
	public static final Dimension COMPUTE_BAR_OBJECT_SIZE = new Dimension(40,30);
	public static final Color COMPUTE_BAR_OBJECT_FG = Color.WHITE;
	public static final Color COMPUTE_BAR_OBJECT_BG = new Color(0x446896);
	
	public static final Color SAVED_BG = new Color(0,0,0,0);
	public static final Border SAVED_BORDER = new EmptyBorder(0,0,0,0);
	
	public static final Color SOLUTION_BG = new Color(0xBBCFF2);
	public static final Border SOLUTION_BORDER = new EmptyBorder(0,0,0,0);
	
	public static final Color SOLUTIONSCROLL_BG = new Color(0,0,0,0);
	public static final Border SOLUTIONSCROLL_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color SOLUTIONSCROLL_SCROLLPANE_BG = new Color(0,0,0,0);
	public static final Border SOLUTIONSCROLL_SCROLLPANE_BORDER = new EmptyBorder(0,0,0,0);
	
	
}