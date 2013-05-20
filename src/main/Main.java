package main;

import javax.swing.SwingUtilities;

/**
 * ForkJoin/Thread comparison v1.2
 * 
 * Main class (entry point in application)
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 */
public final class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new App());
	}
}
