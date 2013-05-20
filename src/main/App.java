package main;

import models.ProcessManagerModel;
import views.MainWindowView;

/**
 * This class encapsulate the AWT thread, in order to hide it
 * from the main call.
 * 
 * @see main.Main
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 */
class App implements Runnable {
	
	MainWindowView appView;
	
	public void run() {
		 appView = new MainWindowView(new ProcessManagerModel());
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		appView = null;
	}
}