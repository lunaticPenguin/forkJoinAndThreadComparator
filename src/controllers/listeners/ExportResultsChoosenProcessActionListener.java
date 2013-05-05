package controllers.listeners;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;

import tools.Timer;
import views.ExportResultsCsvView;
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
		
		ExportResultsCsvView exportCsvView = new ExportResultsCsvView(null);
		exportCsvView.setParentView(parentView);
		
		// search for selected filename
		String selectedFilename = null;
		ArrayList<JComboBox<String>> objComboBoxList = parentView.getAllFilenamesStored();
		for (JComboBox<String> tmpComboBox : objComboBoxList) {
			if (tmpComboBox.isVisible()) {
				selectedFilename = (String) tmpComboBox.getSelectedItem();
			}
		}
		
		exportCsvView.init(Timer.getInstance().getDataAsString(parentView.getProcessChoice().getSelectedIndex(), selectedFilename));
	}
}
