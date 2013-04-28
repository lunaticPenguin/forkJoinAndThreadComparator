package controllers.listeners;

import java.awt.event.ActionEvent;

import views.ExportResultsSettingsView;


/**
 * Listener used when the user has chosen the method and the filename
 * and has clicked on the "Export" button from the export window.
 * 
 * @author Corentin Legros
 */
public class ExportResultsChoosenProcessActionListener extends AbstractActionListener<ExportResultsSettingsView> {

	public ExportResultsChoosenProcessActionListener(ExportResultsSettingsView parentView) {
		super(parentView);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("I should use a textarea window with a csv string :)");
	}
}
