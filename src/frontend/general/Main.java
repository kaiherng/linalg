package frontend.general;

import java.awt.FontFormatException;
import java.io.IOException;

import frontend.shapes.Coord;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public static void main(String[] args) throws FontFormatException, IOException {
        new Application(new Coord(1000,600), new Coord(1000,600));
	}

}
