package frontend.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.font.TextAttribute;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

//http://colorschemedesigner.com/ 
//Scheme ID: 3z41Tw0w0w0w0
//For reference only - not used, i used colorpicker.com to select individual colors
//top - left - bottom - right

public class CurrentConstants {
	
	
	public static final String SUPER_FONT_STYLE = "Dialog";
	public static final Color SUPER_CONTENT_AREA_BG = new Color(0xBBCFF2);
	
	/** INIT FRAME AND APP FRAME **/
	public static final Dimension INIT_FRAME_SIZE = new Dimension(500,300);
	public static final Color INIT_FRAME_BG = new Color(0x93B7E6);
	public static final Color FRAME_BORDER_COLOR = Color.BLACK;
	public static final int FRAME_BORDER_WIDTH = 1;
	public static final Dimension FRAME_MIN_SIZE = new Dimension(1100,800);
	public static final int RESIZE_THRESHOLD = 10;
	
	/** HEADER (ie the window bar right on top) **/
	public static final Color HEADER_BG = new Color(0x103A5E);
	public static final Color HEADER_TITLE_COLOR = Color.WHITE;
	public static final Font HEADER_TITLE_FONT = new Font(SUPER_FONT_STYLE, Font.BOLD, 11);
	public static final Font HEADER_MAX_BUTTON_FONT = new Font(SUPER_FONT_STYLE, Font.BOLD, 14);
	public static final Font HEADER_CLOSE_BUTTON_FONT = new Font(SUPER_FONT_STYLE, Font.BOLD, 14);
	public static final Font HEADER_MIN_BUTTON_FONT = new Font(SUPER_FONT_STYLE, Font.BOLD, 14);
	public static final Border HEADER_BORDER = BorderFactory.createEmptyBorder(1, 12, 0, 7);
	public static final Border HEADER_MIN_BUTTON_BORDER = BorderFactory.createEmptyBorder(0,0,6,7);
	public static final Border HEADER_MAX_BUTTON_BORDER = BorderFactory.createEmptyBorder(0,0,2,7);
	
	/** SPLIT PANES AND TABS **/
	public static final Color SPLITPANE_BG = new Color(0x1C4C75);
	public static final int SPLITPANE_WIDTH = 11;
	public static final int SPLITPANE_BORDER_WIDTH = 0;
	public static final Color LEFT_RIGHT_PANEL_BG = new Color(0x6E96B5);
	public static final Dimension LEFT_PANEL_MIN_SIZE = new Dimension(550,300); //don't change the x-value - some of the 5 buttons will disappear if its too small - 465 is just right
	public static final Dimension LEFT_PANEL_PREFERRED_SIZE = new Dimension(600,500); //this is the initial size of the left panel
	public static final Dimension RIGHT_PANEL_MIN_SIZE = new Dimension(400,300);
	
	public static final Font TAB_FONT = new Font(SUPER_FONT_STYLE, Font.BOLD, 12);	
	public static final Color TAB_SELECTED = new Color(0x22385C); //the color of the tab when its selected
	public static final Color TAB_SELECTED_HIGHLIGHT = new Color(0x22385C); //the top and left borders of the tab when selected
	public static final Color TAB_SELECTED_FG = Color.WHITE;
	public static final Color TAB_CONTENT_AREA = SUPER_CONTENT_AREA_BG;
	public static final Insets TAB_CONTENT_BORDER_INSETS = new Insets(0, 0, 0, 0); //the border of the content pane itself
	public static final Color TAB_FG = Color.WHITE;
	public static final Color TAB_BG = new Color(0x29477F);
	public static final Color TAB_LIGHT = new Color(0x29477F);
	public static final Color TAB_DARK_SHADOW = new Color(0,0,0,0); //top and right border of the unselected tab, and also the right and bottom of selected tab
	public static final Color TAB_FOCUS = new Color(0,0,0,0);  //DONT CHANGE THIS removes the ugly border around the header text when in focus
	public static final Insets TAB_INSETS = new Insets(4, 12, 3, 11); //sets padding within the tab header
	public static final Insets TAB_AREA_INSETS = new Insets(0, 0, 0, 0); //sets the margin of the block of tab headers
	public static final boolean TAB_TABS_OVERLAP_BORDER = true;
	
	/** BLOCKS **/
	
	public static final Dimension COUNTABLE_BLOCK_SIZE = new Dimension(50, 50);
	public static final Color COUNTABLE_BLOCK_BG = new Color(0x3C4C78);	
	public static final Color COUNTABLE_BLOCK_HOVER_BG = new Color(0x223057);
	public static final Color COUNTABLE_BLOCK_EDITING_BG = Color.black;	//the color to highlight the block when we're editing it
	public static final Color COUNTABLE_BLOCK_DELETE_FG = Color.white;
	public static final Font COUNTABLE_BLOCK_DELETE_FONT = new Font(SUPER_FONT_STYLE, Font.BOLD, 11);
	public static final Font COUNTABLE_BLOCK_LABEL_MATRIX_FONT_LARGE = new Font(SUPER_FONT_STYLE, Font.BOLD, 18);
	public static final Font COUNTABLE_BLOCK_LABEL_MATRIX_FONT_SMALL = new Font(SUPER_FONT_STYLE, Font.BOLD, 12);
	public static final Font COUNTABLE_BLOCK_LABEL_SCALAR_FONT = new Font(SUPER_FONT_STYLE, Font.BOLD, 18);
	public static final Color COUNTABLE_BLOCK_LABEL_FG = Color.white;
	public static final Rectangle COUNTABLE_BLOCK_DELETE_BOUNDS = new Rectangle(37,3,10,10);
	public static final Rectangle COUNTABLE_BLOCK_LABEL_BOUNDS = new Rectangle(0,5,40,30);
	
	
	public static final Color OP_BLOCK_BG = new Color(0x365D91);
	public static final Dimension OP_BLOCK_SIZE = new Dimension(60,60);
	public static final Color OP_BLOCK_HOVER_BG = new Color(0x1C3A63);
	public static final Color OP_BLOCK_PRESSED_BG = new Color(0x1C3A63);
	
	public static final Color BRACKET_BLOCK_BG = new Color(0x3C4C78);
	public static final Color BRACKET_BLOCK_FG = Color.white;
	public static final Dimension BRACKET_BLOCK_SIZE = new Dimension(60,60);
	public static final Color BRACKET_BLOCK_HOVER_BG = new Color(0x1D2B52);
	public static final Color BRACKET_BLOCK_PRESSED_BG = new Color(0x1D2B52);
	
	/** CONSTRUCT **/
	public static final Color CONSTRUCT_BG = new Color(0xF2F3F5);
	public static final Border CONSTRUCT_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color CONSTRUCT_BUTTON_PANEL_BG = new Color(0xF2F3F5);
	public static final Border CONSTRUCT_BUTTON_PANEL_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color CONSTRUCT_GRID_DARK = Color.BLACK;
	public static final Color CONSTRUCT_GRID_MEDIUM = new Color(0xD3DDEB);
	public static final Color CONSTRUCT_GRID_LIGHT = new Color(0xCCCCCC);
	public static final String CONSTRUCT_FONT = "Dialog";
	public static final int CONSTRUCT_SIZEINDICATOR_XOFFSET = 10;
	public static final int CONSTRUCT_SIZEINDICATOR_YOFFSET = 30;
	public static final int CONSTRUCT_SIZEINDICATOR_SIZE = 15;
	public static final String CONSTRUCT_SIZEINDICATOR_FONT = "Dialog";
	public static final Color CONSTRUCT_SIZEINDICATOR_FG = Color.BLACK;
	public static final float CONSTRUCT_SIZEINDICATOR_WEIGHT = TextAttribute.WEIGHT_BOLD;
	public static final Color CONSTRUCT_SIZEINDICATOR_BG = new Color(0x98B7D9);
	public static final int CONSTRUCT_SIZEINDICATOR_STROKESIZE = 2;
	public static final Color CONSTRUCT_SIZEINDICATOR_STROKECOLOR = Color.BLACK;
	
	public static final Border CONSTRUCT_INSTRUCTIONSLABEL_BORDER = new EmptyBorder(5,10,5,0);
	public static final String CONSTRUCT_INSTRUCTIONSLABEL_EMPTYTEXT = "<html>Click  and drag to create a matrix, <br>or create a scalar using the New Scalar button";
	public static final String CONSTRUCT_INSTRUCTIONSLABEL_DRAWNTEXT = "<html>Manually enter values in cells, <br>or use the Identity or Fill Matrix buttons";
	public static final String CONSTRUCT_INSTRUCTIONSLABEL_INCOMPLETETEXT = "<html><font color=red>Incomplete Matrix!</font> " + CONSTRUCT_INSTRUCTIONSLABEL_DRAWNTEXT;
	public static final String CONSTRUCT_INSTRUCTIONSLABEL_SAVEDTEXT = "<html><font color=green>Matrix Saved!</font> " + CONSTRUCT_INSTRUCTIONSLABEL_EMPTYTEXT;
	public static final String CONSTRUCT_INSTRUCTIONSLABEL_SAVEDSCALARTEXT = "<html><font color=green>Scalar Saved!</font> " + CONSTRUCT_INSTRUCTIONSLABEL_EMPTYTEXT;
	public static final String CONSTRUCT_INSTRUCTIONSLABEL_SAVEDSCALAR_MATRIXDRAWNTEXT = "<html><font color=green>Scalar Saved!</font> " + CONSTRUCT_INSTRUCTIONSLABEL_DRAWNTEXT;
	public static final String CONSTRUCT_INSTRUCTIONSLABEL_EDITINGSAVEDTEXT = "<html>Currently editing a Saved Matrix<BR>Don't forget to save after making changes";
	public static final String CONSTRUCT_INSTRUCTIONSLABEL_SAVEDSCALAR_EDITINGSAVEDTEXT = "<html><font color=green>Scalar Saved!</font> " + CONSTRUCT_INSTRUCTIONSLABEL_EDITINGSAVEDTEXT;

	
	
	/** (CONSTRUCT) CUSTOM DIALOG **/
	public static final Border CUSTOM_DIALOG_TEXTFIELD_BORDER = new EmptyBorder(0,10,0,0);
	public static final Color CUSTOM_DIALOG_TEXTFIELD_BG = new Color(0xEBEEF2);
	public static final Font CUSTOM_DIALOG_TEXTFIELD_FONT = new Font("Dialog", Font.BOLD, 16);
	
	public static final Border CUSTOM_DIALOG_CONTENTPANEL_BORDER = new EmptyBorder(10,10,10,10);
	public static final Color CUSTOM_DIALOG_CONTENTPANEL_BG = new Color(0xB7CBEB);
	
	public static final Border CUSTOM_DIALOG_NORTHPANEL_BORDER = new EmptyBorder(0,0,10,0);
	public static final Color CUSTOM_DIALOG_NORTHPANEL_BG = new Color(0xB7CBEB);
	public static final Border CUSTOM_DIALOG_MIDDLEPANEL_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color CUSTOM_DIALOG_MIDDLEPANEL_BG = new Color(0xB7CBEB);
	public static final Border CUSTOM_DIALOG_SOUTHPANEL_BORDER = new EmptyBorder(10,0,0,0);
	public static final Color CUSTOM_DIALOG_SOUTHPANEL_BG = new Color(0xB7CBEB);
	
	public static final Color CUSTOM_DIALOG_FILL_BUTTON_BG = new Color(0x264261);
	public static final Color CUSTOM_DIALOG_FILL_BUTTON_FG = Color.WHITE;
	public static final Color CUSTOM_DIALOG_FILL_BUTTON_HOVER_BG = new Color(0x264261);
	public static final Color CUSTOM_DIALOG_FILL_BUTTON_HOVER_FG = Color.WHITE;
	public static final Color CUSTOM_DIALOG_FILL_BUTTON_PRESSED_BG = new Color(0x264261);
	public static final Color CUSTOM_DIALOG_FILL_BUTTON_PRESSED_FG = Color.BLACK;
	public static final Border CUSTOM_DIALOG_FILL_BUTTON_BORDER = new EmptyBorder(10,12,10,12);
	
	public static final Color CUSTOM_DIALOG_CANCEL_BUTTON_BG = new Color(0x9AA0AB);
	public static final Color CUSTOM_DIALOG_CANCEL_BUTTON_FG = Color.WHITE;
	public static final Color CUSTOM_DIALOG_CANCEL_BUTTON_HOVER_BG = new Color(0x606773);
	public static final Color CUSTOM_DIALOG_CANCEL_BUTTON_HOVER_FG = Color.WHITE;
	public static final Color CUSTOM_DIALOG_CANCEL_BUTTON_PRESSED_BG = new Color(0x607AA3);
	public static final Color CUSTOM_DIALOG_CANCEL_BUTTON_PRESSED_FG = Color.WHITE;
	public static final Border CUSTOM_DIALOG_CANCEL_BUTTON_BORDER = new EmptyBorder(10,12,10,12);
	
	public static final Color CUSTOM_DIALOG_WARNING_FG = Color.RED;
	

	/** COMPUTE **/
	public static final Font COMPUTE_LABEL_FONT = new Font(SUPER_FONT_STYLE, Font.BOLD, 14);
	public static final Color SUPER_COMPUTE_BAR_BG = new Color(0xF2F3F5); //changing this changes the value of all the colors that affect the compute bar
	public static final Color COMPUTE_BG = SUPER_CONTENT_AREA_BG;
	
	public static final Border COMPUTE_BORDER = new EmptyBorder(0,0,0,0);	
	public static final int COMPUTE_WRAPLAYOUT_HGAP = 5;
	public static final int COMPUTE_WRAPLAYOUT_VGAP = 5;
	public static final Color COMPUTE_COMPUTEBAR_BG = SUPER_COMPUTE_BAR_BG;
	public static final Border COMPUTE_COMPUTEBAR_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color COMPUTE_OPS_BG = SUPER_CONTENT_AREA_BG;
	public static final Border COMPUTE_OPS_BORDER = new EmptyBorder(0,3,0,0);
	public static final Color COMPUTE_BUTTONPANEL_BG = SUPER_COMPUTE_BAR_BG;
	public static final Border COMPUTE_BUTTONPANEL_BORDER = new EmptyBorder(0,0,0,0);
	public static final Dimension COMPUTE_SCROLLPANEL_SIZE = new Dimension(100,70);
	public static final Border COMPUTE_SCROLLPANEL_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color COMPUTE_SCROLLPANEL_BG = SUPER_CONTENT_AREA_BG;	
	public static final Color COMPUTE_BAR_BG = SUPER_COMPUTE_BAR_BG;
	public static final Border COMPUTE_BAR_BORDER = new EmptyBorder(0,0,0,0);	
	public static final Color COMPUTE_SCROLLBAR_BG = SUPER_COMPUTE_BAR_BG;
	public static final Border COMPUTE_SCROLLBAR_BORDER = new EmptyBorder(0,0,0,0);		
	public static final Dimension COMPUTE_BAR_OBJECT_SIZE = new Dimension(45,45);
	public static final Color COMPUTE_BAR_OBJECT_FG = Color.WHITE;
	public static final Color COMPUTE_BAR_OBJECT_BG = new Color(0x3C4C78);
	public static final Color COMPUTE_BAR_OBJECT_HOVER_BG = new Color(0x26355E);
	public static final String COMPUTE_INSTRUCTIONSLABEL_TEXT = "<html>Click on saved matrices and<br> operations to construct an equation";
	public static final Border COMPUTE_INSTRUCTIONSLABEL_BORDER = new EmptyBorder(15,12,0,0);	
	
	/** SAVED **/
	public static final Color SAVED_BG = SUPER_CONTENT_AREA_BG;
	public static final Border SAVED_BORDER = new EmptyBorder(0,0,0,0);
	public static final int SAVED_WRAPLAYOUT_VGAP = 7;
	public static final int SAVED_WRAPLAYOUT_HGAP = 7;
	public static final Color SAVEDSCROLL_BG = SUPER_CONTENT_AREA_BG;
	public static final Border SAVEDSCROLL_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color SAVEDSCROLL_SCROLL_BG = SUPER_CONTENT_AREA_BG;
	public static final Border SAVEDSCROLL_SCROLL_BORDER = new EmptyBorder(0,0,0,0);
	
	public static final Color SAVE_BUTTON_BG = new Color(0x315E8F);
	public static final Color SAVE_BUTTON_FG = Color.WHITE;
	public static final Color SAVE_BUTTON_HOVER_BG = new Color(0x214873);
	public static final Color SAVE_BUTTON_HOVER_FG = Color.WHITE;
	public static final Color SAVE_BUTTON_PRESSED_BG = new Color(0x214873);
	public static final Color SAVE_BUTTON_PRESSED_FG = Color.BLACK;
	public static final Border SAVE_BUTTON_BORDER = new EmptyBorder(8,12,8,12);
	
	public static final Color SAVEAS_BUTTON_BG = new Color(0x3C5775);
	public static final Color SAVEAS_BUTTON_FG = Color.WHITE;
	public static final Color SAVEAS_BUTTON_HOVER_BG = new Color(0x284059);
	public static final Color SAVEAS_BUTTON_HOVER_FG = Color.WHITE;
	public static final Color SAVEAS_BUTTON_PRESSED_BG = new Color(0x284059);
	public static final Color SAVEAS_BUTTON_PRESSED_FG = Color.BLACK;
	public static final Border SAVEAS_BUTTON_BORDER = new EmptyBorder(8,12,8,12);
	
	public static final Color EXPORT_BUTTON_BG = new Color(0x4A5D70);
	public static final Color EXPORT_BUTTON_FG = Color.WHITE;
	public static final Color EXPORT_BUTTON_HOVER_BG = new Color(0x303F4F);
	public static final Color EXPORT_BUTTON_HOVER_FG = Color.WHITE;
	public static final Color EXPORT_BUTTON_PRESSED_BG = new Color(0x303F4F);
	public static final Color EXPORT_BUTTON_PRESSED_FG = Color.BLACK;
	public static final Border EXPORT_BUTTON_BORDER = new EmptyBorder(8,12,8,12);
	
	/** SOLUTION **/
	public static final int SOLUTION_TEX_SIZE = 20;
	
	public static final Color SOLUTIONSCROLL_BG = Color.white;
	public static final Border SOLUTIONSCROLL_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color SOLUTIONSCROLL_SCROLL_BG = Color.white;
	public static final Border SOLUTIONSCROLL_SCROLL_BORDER = new EmptyBorder(0,0,0,0);
	
	public static final Border STEPSOLUTION_BOTTOMBAR_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color STEPSOLUTION_BOTTOMBAR_BG = SUPER_CONTENT_AREA_BG;
	public static final Border STEPSOLUTION_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color STEPSOLUTION_BG = SUPER_CONTENT_AREA_BG;
	public static final Border STEPSOLUTION_SCROLL_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color STEPSOLUTION_SCROLL_BG = SUPER_CONTENT_AREA_BG;
	public static final Border STEPSOLUTION_BOTTOMBARLEFT_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color STEPSOLUTION_BOTTOMBARLEFT_BG = SUPER_CONTENT_AREA_BG;
	public static final Border STEPSOLUTION_COMPSCROLL_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color STEPSOLUTION_COMPSCROLL_BG = SUPER_CONTENT_AREA_BG;
	public static final Border STEPSOLUTION_ANSWER_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color STEPSOLUTION_ANSWER_BG = new Color(0x91B8E3);
	public static final Color STEPSOLUTION_ANSWER_HOVER_BG = new Color(0x5080B5);
	public static final Border STEPSOLUTION_COMP_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color STEPSOLUTION_COMP_BG = SUPER_CONTENT_AREA_BG;
	
	public static final Border STEPSOLUTION_BOTTOMBARLEFTUP_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color STEPSOLUTION_BOTTOMBARLEFTUP_BG = new Color(0x264261);
	public static final Border STEPSOLUTION_STEPNUMBERLABEL_BORDER = new EmptyBorder(0,7,0,7);
	public static final Color STEPSOLUTION_STEPNUMBERLABEL_BG = new Color(0x264261);
	public static final Color STEPSOLUTION_STEPNUMBERLABEL_FG = Color.WHITE;
	public static final Font STEPSOLUTION_STEPNUMBERLABEL_FONT = new Font("Dialog", Font.BOLD, 13);
	
	public static final Border STEPSOLUTION_BOTTOMBARRIGHTUP_BORDER = new EmptyBorder(5,0,5,0);
	public static final Color STEPSOLUTION_BOTTOMBARRIGHTUP_BG = new Color(0x264261);
	public static final Border STEPSOLUTION_ANSWERLABEL_BORDER = new EmptyBorder(0,0,0,0);
	public static final Color STEPSOLUTION_ANSWERLABEL_BG = new Color(0x264261);
	public static final Color STEPSOLUTION_ANSWERLABEL_HOVER_BG = new Color(0x597594);
	public static final Color STEPSOLUTION_ANSWERLABEL_FG = Color.WHITE;
	public static final Font STEPSOLUTION_ANSWERLABEL_FONT = new Font("Dialog", Font.BOLD, 13);
	
	/** BUTTONS **/
	public static final Color BUTTON_BG = new Color(0x264261);
	public static final Color BUTTON_FG = Color.WHITE;
	public static final Color BUTTON_HOVER_BG = new Color(0x264261);
	public static final Color BUTTON_HOVER_FG = Color.WHITE;
	public static final Color BUTTON_PRESSED_BG = new Color(0x264261);
	public static final Color BUTTON_PRESSED_FG = Color.BLACK;
	public static final Border BUTTON_BORDER = new EmptyBorder(10,12,10,12);
	
	public static final Color BUTTON_COMPUTE_BG = new Color(0x264261);
	public static final Color BUTTON_COMPUTE_FG = Color.WHITE;
	public static final Color BUTTON_COMPUTE_HOVER_BG = new Color(0x152D47);
	public static final Color BUTTON_COMPUTE_HOVER_FG = Color.WHITE;
	public static final Color BUTTON_COMPUTE_PRESSED_BG = new Color(0x152D47);
	public static final Color BUTTON_COMPUTE_PRESSED_FG = Color.WHITE;
	public static final Color BUTTON_CLEAR_BG = new Color(0x4F6178);
	public static final Color BUTTON_CLEAR_FG = Color.WHITE;
	public static final Color BUTTON_CLEAR_HOVER_BG = new Color(0x384454);
	public static final Color BUTTON_CLEAR_HOVER_FG = Color.WHITE;
	public static final Color BUTTON_CLEAR_PRESSED_BG = new Color(0x384454);
	public static final Color BUTTON_CLEAR_PRESSED_FG = Color.WHITE;
	public static final Border BUTTON_COMPUTE_BORDER = new EmptyBorder(0,15,0,15);
	public static final Border BUTTON_CLEAR_BORDER = new EmptyBorder(0,20,0,20);
	
	//for step by step next & prev buttons
	public static final Color BUTTON_NEXT_BG = new Color(0x264261);
	public static final Color BUTTON_NEXT_FG = new Color(0xA1BED6);
	public static final Color BUTTON_NEXT_HOVER_BG = new Color(0x264261);
	public static final Color BUTTON_NEXT_HOVER_FG = Color.WHITE;
	public static final Color BUTTON_NEXT_PRESSED_BG = new Color(0x264261);
	public static final Color BUTTON_NEXT_PRESSED_FG = Color.BLACK;
	public static final Border BUTTON_NEXT_BORDER = new EmptyBorder(-2,0,0,0);
	public static final Font BUTTON_NEXT_FONT = new Font("Dialog", Font.BOLD, 16);
	
	/** SCROLLPANE **/
	public static final Color SUPER_SCROLLPANE_THUMB_BG = new Color(0x2F5FA3);
	public static final Color SUPER_SCROLLPANE_TRACK_BG = new Color(0xB4C6E0);
	public static final Color SCROLLPANE_THUMB_COLOR = SUPER_SCROLLPANE_THUMB_BG;
	public static final Color SCROLLPANE_THUMB_DARK_SHADOW_COLOR = SUPER_SCROLLPANE_THUMB_BG;
	public static final Color SCROLLPANE_THUMB_HIGHLIGHT_COLOR = SUPER_SCROLLPANE_THUMB_BG;
	public static final Color SCROLLPANE_THUMB_LIGHT_SHADOW_COLOR = SUPER_SCROLLPANE_THUMB_BG;
	public static final Color SCROLLPANE_TRACK_COLOR = SUPER_SCROLLPANE_TRACK_BG;
	public static final Color SCROLLPANE_TRACK_HIGHLIGHT_COLOR = SUPER_SCROLLPANE_TRACK_BG;
	

}
