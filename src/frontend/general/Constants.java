package frontend.general;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

import frontend.shapes.Coord;


public class Constants {
	
	/** FONTS **/
	public static final String TEXT_FONTSTYLE = "Arial";
	
	/** COLOR SCHEME **/
	public static final Color TAB_MAINBG_COLOR = new Color(0x1047A9);
	public static final Color TAB_HEADERBG_ACTIVE_COLOR = new Color(0x052A6E);
	public static final Color TAB_HEADERBG_INACTIVE_COLOR = new Color(0x29477F);
	public static final Color FRAME_BG_COLOR = new Color(0x6B90D4); //0x4577D4
	public static final Color SCREEN_BG_COLOR = new Color(0x6B90D4);
	public static final Color TABHEADER_TEXT_ACTIVE_COLOR = new Color(0x6B90D4);
	public static final Color TABHEADER_TEXT_INACTIVE_COLOR = Color.BLACK;
	public static final Color TABMAIN_BORDER_COLOR = new Color(0x052A6E);
	public static final Color CONSTRUCTCONTAINER_TEXT_COLOR = Color.WHITE;
	
	/** FRAMES **/
	public static final int FRAME_X_OFFSET = 20; //offset from the topleft corner
	public static final int FRAME_Y_OFFSET = 20; //offset from the topleft corner

	/** TABS **/
	public static final int TABHEADER_LEFT_RIGHT_OFFSET = 15;
	public static final int TABHEADER_HEIGHT = 20;
	public static final int TABHEADER_WIDTH = 100;
	public static final int TABHEADER_OVERLAP = 12;
	public static final int TAB_LEFT_OFFSET = 5;
	public static final int TAB_RIGHT_OFFSET = 5;
	public static final int TAB_TOP_OFFSET = 3;
	public static final int TAB_BOTTOM_OFFSET = 3;
	public static final int TABMAIN_BORDER_WIDTH = 3;
	
}
