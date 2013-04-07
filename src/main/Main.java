package main;

import javax.swing.SwingUtilities;

/**
 * Classe principale (point d'entrée dans l'application)
 * @author Corentin Legros
 *
 */
public final class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new App());
	}
}
