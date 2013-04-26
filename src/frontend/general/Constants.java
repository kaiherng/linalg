package frontend.general;

import java.awt.Color;

import frontend.shapes.Coord;


public class Constants {
	
	/** FONTS **/
	public static final String TEXT_FONTSTYLE = "Dialog";
	
	/** COLOR SCHEME **/
	public static final Color JFRAME_HEADER_BG_COLOR = new Color(0x1D4673);
	public static final Color JFRAME_HEADER_TEXT_COLOR = Color.WHITE;
	public static final Color JFRAME_BG_COLOR = Color.LIGHT_GRAY;
	public static final Color JFRAME_BORDER_COLOR = Color.DARK_GRAY;
	
	public static final Color SCREEN_BG_COLOR = new Color(0x6B90D4);
	
	public static final Color FRAME_BG_COLOR = new Color(0,0,0,0); //transparent
	
	public static final Color TAB_MAINBG_COLOR = new Color(0xE4E7F2);
	public static final Color TABMAIN_BORDER_COLOR = new Color(0x052A6E);
	public static final Color TAB_HEADERBG_ACTIVE_COLOR = new Color(0x2A3A6E);
	public static final Color TABHEADER_TEXT_ACTIVE_COLOR = Color.WHITE;
	public static final Color TAB_HEADERBG_INACTIVE_COLOR = new Color(0x29477F);	
	public static final Color TABHEADER_TEXT_INACTIVE_COLOR = new Color(0x95A1C9);
	public static final Color TABHEADER_BORDER_COLOR = new Color(0x052A6E);
	
	public static final Color CONSTRUCT_INSTRUCTIONS_TEXT_COLOR = new Color(0x42538A);
	
	public static final Color COMPUTE_OPS_FILL_COLOR = new Color(0x42538A);
	public static final Color COMPUTE_OPS_BORDER_COLOR = Color.WHITE;
	public static final Color COMPUTE_OPS_TEXT_COLOR = Color.WHITE;
	
	public static final Color SCROLL_PANE_FILL_COLOR = new Color(0,0,0,0); //transparent
	public static final Color SCROLL_PANE_BORDER_COLOR = new Color(0x052A6E);
	
	public static final Color SCROLL_ARROW_FILL_COLOR = new Color(0x2A3A6E);
	public static final Color SCROLL_ARROW_BORDER_COLOR = Color.LIGHT_GRAY;
	
	
	/** FRAMES **/
	public static final int FRAME_X_OFFSET = 20; //offset from the topleft corner
	public static final int FRAME_Y_OFFSET = 20; //offset from the topleft corner

	/** TABS **/
	public static final Coord TABHEADER_TOPLEFT_OFFSET = new Coord(0,0);
	public static final Coord TABHEADER_TOPRIGHT_OFFSET = new Coord(20,0);	
	public static final Coord TABHEADER_BOTTOMLEFT_OFFSET = new Coord(0,0);	
	public static final Coord TABHEADER_BOTTOMRIGHT_OFFSET = new Coord(0,0);	
	
	public static final Coord TABHEADER_SIZE = new Coord(100,20);
	public static final int TABHEADER_OVERLAP = 12;
	public static final Coord TABHEADER_TEXT_OFFSET = new Coord(10,6);
	public static final String TABHEADER_TEXT_STYLE = "bold";
	public static final int TABHEADER_TEXT_SIZE = 10;
	public static final int TABHEADER_BORDER_WIDTH = 3;
	
	public static final int TAB_LEFT_OFFSET = 5;
	public static final int TAB_RIGHT_OFFSET = 5;
	public static final int TAB_TOP_OFFSET = 3;
	public static final int TAB_BOTTOM_OFFSET = 3;
	public static final int TABMAIN_BORDER_WIDTH = 3;
	
	/** CONTAINERS **/
	public static final Coord CONTAINER_TOP_LEFT = new Coord(10, 25);
	
	/** COMPUTE **/
	public static final int COMPUTE_OPS_TEXT_SIZE = 12;
	public static final String COMPUTE_OPS_TEXT_STYLE = "bold";
	public static final int COMPUTE_OPS_BORDER_WIDTH = 5;
	public static final int COMPUTE_SCROLL_BORDER_WIDTH = 2;
	
	public static final int SCROLL_ARROW_BORDER_WIDTH = 1;
	public static final int SCROLL_PANE_BORDER_WIDTH = 2;
	
	/** CONSTRUCT **/
	public static final int CONSTRUCT_INSTRUCTIONS_TEXT_SIZE = 12;
	public static final String CONSTRUCT_INSTRUCTIONS_TEXT_STYLE = "bold";
	public static final Coord CONSTRUCT_INSTRUCTIONS_TEXT_OFFSET = new Coord(10,10);
	
	/** SAVED MATRICES **/
	public static final Coord SM_MARGIN = new Coord(10,10);
	public static final Coord SM_SIZE = new Coord(40,40);
	public static final int SM_TEXT_SIZE = 18;
	public static final Color SM_COLOR = SCREEN_BG_COLOR;
	
}
