package frontend;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import shapes.Coord;



public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public static void main(String[] args) throws FontFormatException, IOException {
		new Application(new Coord(1000,600));
	}

}
