package frontend.general;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import frontend.shapes.Coord;




public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public static void main(String[] args) throws FontFormatException, IOException {
		Application application = new Application(new Coord(1000,600));
		application.initialSetup();
		application.refresh(); //strangely, sometimes the initial setup does not paint properly - might be because of threading and different race conditions. so sleep for a bit, and refresh
	}

}
