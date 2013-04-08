package backend.main;

import frontend.Application;
import graphicsengine.MainScreen;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Application application = new Application();
		application.setScreen(new MainScreen(application));		
		application.startup(); // begin processing events
	}

}
